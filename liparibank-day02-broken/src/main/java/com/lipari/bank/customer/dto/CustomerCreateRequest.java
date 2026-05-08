package com.lipari.bank.customer.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CustomerCreateRequest(

        @NotBlank(message = "Il codice fiscale è obbligatorio")
        String fiscalCode,

        @NotBlank(message = "Il nome è obbligatorio")
        String firstName,

        @NotBlank(message = "Il cognome è obbligatorio")
        String lastName,

        @Email(message = "Email non valida")
        @NotBlank(message = "L'email è obbligatoria")
        String email,

        String phone

) {}
