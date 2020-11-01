package com.readingisgood.request.customer;

import lombok.Data;

@Data
public class RegisterCustomerRequest {

    private String name;
    private String surname;
    private String email;
    private String password;
}
