# Bayt_v2 database setup

Requirements: Docker Desktop with Compose, or MySQL 8.4 and JDK 21.

## Docker (recommended)

```sh
cp .env.example .env
# Set DB_PASSWORD, MYSQL_ROOT_PASSWORD, and BOOTSTRAP_ADMIN_PASSWORD (12–72 characters).
docker compose up --build -d
docker compose logs -f app
```

Open http://localhost:8080 after startup. MySQL is published only on localhost:3307 to avoid
conflicting with a native MySQL on 3306. Its image creates Bayt_v2 on first volume initialization.
Flyway creates tables, indexes, foreign keys and monetary DECIMAL(15,3) columns. Hibernate validates.
The mysql-data volume preserves data across `docker compose restart` and `docker compose down`.
Do not run `down -v` against data you want to retain. Changing MYSQL_DATABASE does not initialize
an already populated volume; create the database explicitly on that server instead.

## Native MySQL

Run as a database administrator, replacing the password placeholder:

```sql
CREATE DATABASE Bayt_v2 CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;
CREATE USER 'bayt'@'localhost' IDENTIFIED BY '<choose-a-password>';
GRANT ALL PRIVILEGES ON Bayt_v2.* TO 'bayt'@'localhost';
```

Set DB_HOST, DB_PORT, DB_USER, DB_PASSWORD and COOKIE_SECURE=false in IntelliJ's run configuration.
Set BOOTSTRAP_ADMIN_USERNAME and BOOTSTRAP_ADMIN_PASSWORD for the initial account.
Run BaytApplication or `./mvnw spring-boot:run` (`.\mvnw.cmd spring-boot:run` on Windows).
Native runs do not automatically load .env. Java defaults to 17-compatible bytecode; JDK 21 is recommended.

## Accounts

Normal startup creates only a PLATFORM_ADMIN when the database is empty and bootstrap variables
are supplied. Sign in, create an OWNER through account management, then create that owner's staff,
tenants and vendors. Remove bootstrap environment variables after initialization. No default
password is committed. For disposable local demos explicitly set SPRING_PROFILES_ACTIVE=dev
and DEMO_PASSWORD. Demo accounts: owner, owner2, manager, tenant, vendor, guard, admin.
Development seeding is disabled for every other profile.

## Tests

Create a separate empty Bayt_v2_test and test user. Set TEST_DB_URL, TEST_DB_USER,
TEST_DB_PASSWORD and run `./mvnw clean verify`. Integration tests write test records and must never
point to a live database. CI supplies a fresh real MySQL database for Java 17 and 21.

## Existing data / legacy rent_due

Never baseline an unreviewed production schema, set ddl-auto=create, or drop old billing tables.
V1 is for a fresh Bayt_v2 database, and Flyway refuses a nonempty unversioned schema by default.
Keep the original database read-only and retain a verified backup. Restore a copy for analysis;
compare IDs, relationships and balances before importing into a fresh migrated Bayt_v2.
Due plus Payment/Allocation is the sole authoritative ledger. Legacy rent_due.paid_amount does not
contain settlement dates, methods, references or reversal history, so it cannot safely become real
payment events automatically. Reconcile it against source receipts; import verified payments with
stable legacy idempotency keys through the financial service. Match dues by lease/date, never sum
both tables. Report duplicate dates, orphaned leases, overpayments and conflicting totals for human
resolution. Preserve legacy tables and reconciliation exports until totals and audit trails are signed
off. No lossy automatic conversion is supplied or claimed.
