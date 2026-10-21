# Bayt | بيت frontend

The reference frontend is reused because it already covers the domain and uses browser ES modules,
real backend APIs, session cookies and CSRF. It adds no separate authentication or duplicated mock
state. Bayt serves static assets from the same Spring Boot process and root URL.

Development: run the backend with the database configured (`./mvnw spring-boot:run`), then open
http://localhost:8080. Refresh after editing src/main/resources/static. A separate dev proxy or CORS
exception is unnecessary. Production: `./mvnw clean verify` packages all frontend assets into
`target/Bayt-1.0.0-SNAPSHOT.jar`; deploy behind HTTPS and set COOKIE_SECURE=true.

The UI provides Arabic/English and RTL/LTR, role navigation, search/pagination, empty/error states,
confirmation dialogs, keyboard-accessible forms and mobile navigation. Modules include accounts,
building assignments, properties, units, tenants, leads, leases, finance/receipts, documents,
maintenance/vendors, reminders, reports, operational records and audit history. External integrations
show setup-required states. Issued lease terms are immutable; use renewal/termination and municipality
updates. AI remains unavailable on this branch.

Browser acceptance: `cd tests/browser && npm ci && npx playwright install chromium && npm test`.
Start a disposable dev database with DEMO_PASSWORD set and export the same DEMO_PASSWORD for tests.
The browser tests perform real writes, so never run them against production. CI runs these workflows
against a fresh MySQL database and uploads desktop/mobile Arabic/English screenshots plus app logs.
No separate frontend bundler is needed; the Maven production package includes the ES modules directly.
