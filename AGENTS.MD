# AGENTS.md — LipariBank Day 01

## Project

Spring Boot 3.3.4, Java 21, Maven. Project root: `liparibank-day01-broken/` (nested one level under repo root).

## Commands

| Action | Command |
|--------|---------|
| Run app | `./mvnw spring-boot:run` (Linux/macOS) or `mvnw.cmd spring-boot:run` (Windows) |
| Compile | `./mvnw compile` |
| Run tests | `./mvnw test` |
| Clean build | `./mvnw clean install` |

## Architecture

```
src/main/java/com/lipari/bank/
├── LipariBankApplication.java          # @SpringBootApplication entrypoint
├── shared/config/
│   └── LipariBankProperties.java       # @ConfigurationProperties(prefix="liparibank") record
└── account/
    ├── NotificationService.java        # interface
    ├── EmailNotificationService.java   # @Service("emailNotificationService") implements NotificationService
    ├── LogNotificationService.java     # @Service("logNotificationService") implements NotificationService
    └── AlertService.java               # @Service, depends on NotificationService

src/main/java/com/liparibank/lifecycle/
└── BankSystemLifecycle.java            # @Component with @PostConstruct startup logs
```

## Intentional Bugs

This is a **training/exercise** project containing **3 intentional bugs**:

1. **Binding failure** (`application.yml` line 9): `max-transfer-amount: 50000.00` is a decimal value, but `LipariBankProperties.maxTransferAmount` is declared as `int`. Spring Boot strict binding rejects the decimal.

2. **Ambiguous DI** (`AlertService`): two beans implement `NotificationService` (`EmailNotificationService`, `LogNotificationService`). Spring cannot choose which to inject. Fix: add `@Primary` to one implementation, or use `@Qualifier`, or inject a `List<NotificationService>`.

3. **Missing bean registration**: `LipariBankProperties` has `@ConfigurationProperties` but no `@Component` or `@EnableConfigurationProperties`. Spring won't bind the YAML values.

4. **Package mismatch**: `BankSystemLifecycle.java` declares `package com.liparibank.lifecycle;` while component scanning starts from `com.lipari.bank`. The component will never be discovered. Fix: rename to `com.lipari.bank.lifecycle` and move the file.

## Configuration

- Config prefix: `liparibank` (see `application.yml`)
- Fields: `bankCode` (String), `maxTransferAmount`, `audit.enabled` (boolean)
- Validation: `@Validated` on properties record, `@NotBlank` on `bankCode`

## Notes

- Lombok is available but not currently used
- No test files exist yet (bonus mission requires adding endpoints and beans)
- `spring-boot-devtools` is on classpath for hot-reload
