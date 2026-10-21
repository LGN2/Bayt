# Maintenance AI

## Enable

In .env (Compose) or IntelliJ server environment, set:

```
AI_ENABLED=true
AI_API_KEY=<your-own-provider-key>
AI_MODEL=gpt-4.1-mini
AI_TIMEOUT_SECONDS=12
```

The committed API key is blank. Restart with `docker compose up -d --force-recreate app` after changing
configuration. Leave AI_ENABLED=false to disable. Missing key always disables the client. All non-AI
features and application startup remain available without a key. The UI reports setup required.
The model must be accessible to your provider account. No live provider call is verified without a key.

## Compatibility

Spring AI BOM 1.1.8 manages OpenAI and ChatClient. Boot 3.5.16 manages Jackson 2 and Spring dependencies;
Java 17 and 21 are checked in CI. This deliberately uses the compatible 3.5/1.1 line rather than mixing
Boot 4/Jackson 3 with Jackson 2 source imports. Official references checked:
- https://github.com/spring-projects/spring-ai (1.1.x supports Boot 3.5.x)
- https://docs.spring.io/spring-boot/3.5/system-requirements.html
- https://spring.io/blog/2025/10/07/introducing-jackson-3-support-in-spring
- https://spring.io/blog/2026/06/12/spring-ai-1-1-8-1-0-9-avaialble-now/

## Behavior and boundaries

Owner/manager authorization is checked before provider I/O. Only the maintenance description is sent;
no tenant records, account details or financial data are attached. Common email/phone identifiers in
free text are redacted, but this is not a universal personal-data detector. Avoid putting unrelated
personal data in descriptions. The committed prompt treats descriptions as untrusted data and asks
for Arabic/English JSON with only summary and category. Strict parsing rejects unknown fields,
duplicate fields, trailing content, oversized responses and invalid categories.

Two workers and an eight-request queue bound provider concurrency; each actor is limited to ten
requests per minute per application instance. Timeouts are clamped to 1–30 seconds, network reads
are bounded, output is limited to 512 tokens, and automatic retries are disabled. BUSY, TIMEOUT,
RATE_LIMITED, ERROR and UNAVAILABLE are explicit outcomes. Scale-out deployments need a shared
rate-limit store. No secrets or provider payloads are written to audit history.

The response is a proposal only. Staff review/edit/approve or reject it. Approval updates the summary
and category; it never automatically changes urgency, assigns vendors or records financial events.
Manual summaries work without AI. Requests and decisions are audited. Tests mock Spring AI for
success, malformed content, timeout and provider errors; they do not call a live provider.
