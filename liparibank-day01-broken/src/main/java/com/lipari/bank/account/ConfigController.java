package com.lipari.bank.account;

import com.lipari.bank.shared.config.ConfigAuditEntry;
import com.lipari.bank.shared.config.LipariBankProperties;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("api/v1/config")
public class ConfigController {
    private final LipariBankProperties properties;

    private final ObjectProvider<ConfigAuditEntry> auditEntryProvider;

    public ConfigController (LipariBankProperties properties, ObjectProvider<ConfigAuditEntry> auditEntryProvider) {
        this.properties = properties;
        this.auditEntryProvider = auditEntryProvider;
    }

    @GetMapping
    public Map<String, Object> getConfig() {
        // Chiediamo a Spring un bean fresco qui, dentro il metodo
        ConfigAuditEntry freshEntry = auditEntryProvider.getObject();

        return Map.of(
                "bankCode", properties.bankCode(),
                "maxTransferAmount", properties.maxTransferAmount(),
                "auditEnabled", properties.audit().enabled(),
                "auditEntry", freshEntry
        );
    }
}
