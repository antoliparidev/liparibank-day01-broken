package com.lipari.bank.customer.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Customer {

    private Long id;
    private String fiscalCode;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private CustomerStatus status;
}
