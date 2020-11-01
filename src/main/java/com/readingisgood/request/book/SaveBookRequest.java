package com.readingisgood.request.book;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class SaveBookRequest {

    private BigDecimal price;
    private Integer stock;
    private String title;
    private String author;
    private String genre;
}
