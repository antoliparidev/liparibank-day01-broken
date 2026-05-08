package com.lipari.bank.customer.dto;

import jakarta.validation.constraints.Email;

public record CustomerUpdateRequest(

        @Email(message = "Email non valida")
        String email,

        String phone

) {}
