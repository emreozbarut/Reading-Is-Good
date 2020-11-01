package com.readingisgood.request.order;

import com.readingisgood.dto.OrderDetail;
import lombok.Data;

import java.util.List;

@Data
public class SaveOrderRequest {

    private Long customerId;
    private List<OrderDetail> orderDetails;
}
