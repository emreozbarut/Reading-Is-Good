package com.readingisgood.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SkuDTO {

    private Long id;
    private String status;
    private Long bookId;
    private String title;
    private Integer stock;
}
