package com.readingisgood.response.book;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
public class SaveBookResponse {

    private HttpStatus httpStatus;
}
