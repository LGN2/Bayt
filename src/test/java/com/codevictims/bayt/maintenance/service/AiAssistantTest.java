package com.codevictims.bayt.maintenance.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.springframework.ai.chat.client.ChatClient;

class AiAssistantTest {
  @Test
  void missingCredentialsReturnUnavailable() {
    try (var ai = new AiAssistant(null, 1)) {
      assertEquals("UNAVAILABLE", ai.suggest("المكيف لا يبرد", "ar").status());
    }
  }

  @Test
  void rejectsMalformedAndUnapprovedCategories() throws Exception {
    var ai = new AiAssistant(null, 1);
    assertThrows(Exception.class, () -> ai.parse("not json"));
    assertThrows(Exception.class, () -> ai.parse("{\"summary\":\"Issue\",\"category\":\"FIRE\"}"));
    assertThrows(
        Exception.class, () -> ai.parse("{\"summary\":\"Issue\",\"category\":\"AC\",\"cost\":5}"));
    assertEquals("AC", ai.parse("{\"summary\":\"تبريد ضعيف\",\"category\":\"AC\"}").category());
    ai.close();
  }

  @Test
  void serviceErrorsBecomeManualFallback() {
    ChatClient client = mock(ChatClient.class);
    when(client.prompt()).thenThrow(new IllegalStateException("Provider unavailable"));
    var ai = new AiAssistant(client, 1);
    assertEquals("ERROR", ai.suggest("The AC is not cooling", "en").status());
    ai.close();
  }

  @Test
  void timeoutIsBounded() {
    ChatClient client = mock(ChatClient.class);
    when(client.prompt())
        .thenAnswer(
            invocation -> {
              Thread.sleep(3000);
              throw new IllegalStateException();
            });
    var ai = new AiAssistant(client, 1);
    assertEquals("TIMEOUT", ai.suggest("لا يوجد تبريد", "ar").status());
    ai.close();
  }
  @Test void mockedSuccessRequiresValidStructuredResponse() {
    ChatClient client=mock(ChatClient.class,RETURNS_DEEP_STUBS);
    when(client.prompt().system(anyString()).user(anyString()).call().content())
      .thenReturn("{\"summary\":\"Cooling is weak\",\"category\":\"AC\"}");
    try(var ai=new AiAssistant(client,1)) {
      var result=ai.suggest("Cooling is weak","en");
      assertEquals("PROPOSED",result.status());
      assertEquals("AC",result.suggestion().category());
    }
  }
  @Test void rejectsTrailingAndDuplicateFields() {
    try(var ai=new AiAssistant(null,1)) {
      assertThrows(Exception.class,()->ai.parse("{\"summary\":\"a\",\"category\":\"AC\"} {}"));
      assertThrows(Exception.class,()->ai.parse("{\"summary\":\"a\",\"summary\":\"b\",\"category\":\"AC\"}"));
    }
  }
  @Test void disabledFlagNeverCreatesProviderClient() {
    try(var ai=new AiAssistant(false,"not-a-real-key","unused",1)){assertFalse(ai.available());}
  }
  @Test void stripsCommonContactIdentifiers() {
    String safe=AiAssistant.sanitize("AC issue, email owner@example.com or call +968 9999 1111");
    assertFalse(safe.contains("owner@example.com"));assertFalse(safe.contains("9999"));
  }
  @Test void enforcesPerActorLimits() {
    var limiter=new AiRequestLimiter(java.time.Clock.fixed(java.time.Instant.EPOCH,java.time.ZoneOffset.UTC));
    for(int i=0;i<10;i++)assertTrue(limiter.allow(1L));
    assertFalse(limiter.allow(1L));assertTrue(limiter.allow(2L));
  }
  @Test void providerRateLimitIsExplicit() {
    ChatClient client=mock(ChatClient.class);
    when(client.prompt()).thenThrow(org.springframework.web.client.HttpClientErrorException.create(
      org.springframework.http.HttpStatus.TOO_MANY_REQUESTS,"Too many requests",org.springframework.http.HttpHeaders.EMPTY,new byte[0],java.nio.charset.StandardCharsets.UTF_8));
    try(var ai=new AiAssistant(client,1)){assertEquals("RATE_LIMITED",ai.suggest("AC issue","en").status());}
  }
}
