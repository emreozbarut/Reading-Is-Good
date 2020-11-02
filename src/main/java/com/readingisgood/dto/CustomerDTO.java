package com.readingisgood.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Builder
public class CustomerDTO {

    private Long id;
    private boolean deleted;
    private String status;
    private String name;
    private String surname;
    private String username;
    private String email;
    private List<String> roles;
    private List<OrderDTO> orders;
    private Date createdDate;
    private Date modifiedDate;
}
