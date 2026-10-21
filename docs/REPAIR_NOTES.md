# Backend repair

Baseline: master 9489d8e. Reference: demo-example e64b616, feat/oman-property-management.
No repository AGENTS.md was present. Commit messages follow type(scope): description.

The reviewed reference finance service provides lease-level locking, idempotent payments,
allocation order, reversal accounting and three-decimal money. Its DTO/repository contracts,
tax policies, document renderer and missing exception handling are adapted to Bayt's package root.
Bayt retains separate TenantService and LeaseService. Tenant search uses name/phone/kind.
Lead operations use the scoped OperationsService. Lease terms cannot be silently rewritten after
issuing financial schedules; renewal and termination are explicit. Deletion cannot orphan the ledger.

Due is the authoritative ledger. Existing /api/billing/rent-dues and arrears endpoints remain as
scoped compatibility adapters. Legacy rent_due tables are not dropped or read by this application;
follow database migration documentation before bringing existing balances into the new ledger.

Boot 3.5.16 and Jackson 2 match the existing code. Java 17 is the minimum; Java 21 is supported.
AI is explicitly unavailable until the provider branch and never blocks non-AI startup.

Local Maven resolution failed before compilation due to DNS restrictions on Maven Central.
GitHub Actions is the build gate. Do not interpret restored code as proof of runtime acceptance.
Database schema, integration verification and frontend follow in dependent branches.
