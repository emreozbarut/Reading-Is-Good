package com.readingisgood.dto;

import lombok.Data;

@Data
public class OrderDetail {

    private Long bookId;
    private Long skuId;
    private Integer quantity;
}
