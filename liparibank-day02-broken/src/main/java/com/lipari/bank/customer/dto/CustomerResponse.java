package com.lipari.bank.customer.dto;

import com.lipari.bank.customer.model.CustomerStatus;
import lombok.Builder;

@Builder
public record CustomerResponse(
        Long id,
        String fiscalCode,
        String firstName,
        String lastName,
        String email,
        String phone,
        CustomerStatus status
) {}
