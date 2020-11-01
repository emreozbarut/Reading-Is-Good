package com.readingisgood.response.order;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
public class SaveOrderResponse {

    private HttpStatus status;
}
