package com.readingisgood.request.customer;

import lombok.Data;

@Data
public class RegisterCustomerRequest {

    private String name;
    private String surname;
    private String username;
    private String email;
    private String password;
}
