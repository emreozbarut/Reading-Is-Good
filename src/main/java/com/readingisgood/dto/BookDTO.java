package com.readingisgood.dto;

import com.readingisgood.model.enums.Genre;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@Builder
public class BookDTO {

    private Long id;
    private int version;
    private String status;
    private BigDecimal price;
    private String title;
    private String author;
    private Genre genre;
    private List<SkuDTO> skus;
    private Date createdDate;
    private Date modifiedDate;

}
