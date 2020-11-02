package com.readingisgood.response.customer;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
public class RegisterCustomerResponse {

    private HttpStatus status;
    private String bearerToken;
}
