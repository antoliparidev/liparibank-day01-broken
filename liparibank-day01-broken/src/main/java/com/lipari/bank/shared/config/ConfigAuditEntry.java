package com.lipari.bank.shared.config;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.util.UUID;
// 1. @Component dice a Spring: "Gestiscimi tu".
@Component
// 2. @Scope("prototype") dice a Spring: "Crea una NUOVA istanza ogni volta che mi chiedi questo bean".
@Scope("prototype")
public class ConfigAuditEntry {

    private final String id;
    private final LocalDateTime timestamp;

    // Il costruttore viene eseguito OGNI volta che il bean viene creato.
    public ConfigAuditEntry() {
        this.id = UUID.randomUUID().toString();
        this.timestamp = LocalDateTime.now();
    }

    // Getters necessari per Jackson (convertire in JSON)
    public String getId() { return id; }
    public LocalDateTime getTimestamp() { return timestamp; }
}