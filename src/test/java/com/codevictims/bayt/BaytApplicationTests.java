package com.codevictims.bayt;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.codevictims.bayt.maintenance.service.AiAssistant;
class BaytApplicationTests {
 @Test void aiIsExplicitlyUnavailableWithoutProvider() { assertFalse(new AiAssistant(false,"","unused",1).available()); }
}
