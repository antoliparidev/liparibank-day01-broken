# AGENTS.md — LipariBank Day 02

## Project

Spring Boot 3.3.4, Java 21, Maven. Project root: `liparibank-day02-broken/`.

## Commands

| Action | Command |
|--------|---------|
| Run app | `./mvnw spring-boot:run` |
| Compile | `./mvnw compile` |
| Run tests | `./mvnw test` |
| Clean build | `./mvnw clean install` |

## Architecture

```
src/main/java/com/lipari/bank/
├── LipariBankApplication.java          # @SpringBootApplication entrypoint
├── account/
│   ├── model/Account.java              # JPA entity: id, iban, balance, status, createdAt
│   ├── dto/
│   │   ├── AccountCreateRequest.java   # record: codiceFiscale, initialBalance
│   │   └── AccountResponse.java        # record with @Builder
│   ├── AccountRepository.java          # JpaRepository<Account, Long>, + findByIban
│   ├── AccountMapper.java              # @Component, Entity ↔ DTO conversion
│   ├── AccountService.java             # CRUD with @Transactional
│   └── AccountController.java          # @RestController, /api/v1/accounts
└── shared/config/
    └── OpenApiConfig.java              # OpenAPI bean config
```

## Intentional Bugs (3)

1. **Controller accepts entity instead of DTO** (`AccountController.java:67`): `create()` parameter is `@Valid @RequestBody Account` (JPA entity) not `AccountCreateRequest`. Swagger shows wrong schema and `codiceFiscale` is discarded.

2. **Spring Security blocks all endpoints**: `spring-boot-starter-security` in `pom.xml` but no `SecurityFilterChain` bean. Auto-config generates random password → all endpoints return 401.

3. **Missing `final` on repository field** (`AccountService.java:20`): `private AccountRepository accountRepository;` is not `final`, so `@RequiredArgsConstructor` skips it → NPE at runtime.

## Notes

- H2 console at `/h2-console` (in-memory, `create-drop` DDL)
- Swagger UI at `/swagger-ui.html`, OpenAPI spec at `/api-docs`
- Lombok used (`@Getter`, `@Setter`, `@NoArgsConstructor`, `@RequiredArgsConstructor`, `@Builder`, `@Slf4j`)
- No custom exceptions (uses bare `RuntimeException` with message)
- No `@ExceptionHandler` / `@ControllerAdvice`
- No test files exist
- No `spring-boot-devtools` (unlike day01)
