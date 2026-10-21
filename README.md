# Bayt | بيت

Oman property management in Java/Spring Boot, MySQL and a bilingual browser interface.

## Start locally

```sh
cp .env.example .env
# Fill DB_PASSWORD, MYSQL_ROOT_PASSWORD, BOOTSTRAP_ADMIN_PASSWORD.
docker compose up --build -d
```

Open http://localhost:8080. Initial username is the BOOTSTRAP_ADMIN_USERNAME you set (example: admin).
Use your own bootstrap password. Create an owner account from platform account management.
For disposable sample data explicitly set SPRING_PROFILES_ACTIVE=dev and DEMO_PASSWORD, then sign
in as owner@demo.test using that password. Never enable the dev profile in production.

See [database and native setup](docs/DATABASE.md), [frontend](docs/FRONTEND.md),
[backend repairs](docs/REPAIR_NOTES.md) and [manual branch merge order](docs/BRANCH_STACK.md).

All implementation is on the dependent fixing → database → frontend → spring-ai branches.
Pull requests target master and must be merged manually in that order. No automatic merges.

Validation is tracked in GitHub Actions. Do not assume unconfigured external providers work;
AI is unavailable without its provider branch and a server-side key.
