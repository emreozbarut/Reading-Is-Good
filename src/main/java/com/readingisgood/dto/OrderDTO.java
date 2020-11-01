package com.readingisgood.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class OrderDTO {

    private Long id;
    private boolean deleted;
    private Long customerId;
    private Long bookId;
    private Integer quantity;
    private Date createdDate;
    private Date modifiedDate;
}
