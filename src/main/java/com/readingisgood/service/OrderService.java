package com.readingisgood.service;

import com.readingisgood.converter.order.OrderDTOConverter;
import com.readingisgood.dao.OrderDao;
import com.readingisgood.dto.OrderDTO;
import com.readingisgood.model.Customer;
import com.readingisgood.model.Orders;
import com.readingisgood.request.order.SaveOrderRequest;
import com.readingisgood.response.order.SaveOrderResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderDTOConverter converter;

    private OrderDao orderDao;
    private CustomerService customerService;

    public OrderService(OrderDao orderDao, CustomerService customerService) {
        this.orderDao = orderDao;
        this.customerService = customerService;
    }

    public List<OrderDTO> findAll() {
        return converter.convertOrdersToDTO(orderDao.findAll());
    }

    public SaveOrderResponse save(SaveOrderRequest request) {
        List<Orders> orders = converter.convertRequestToOrder(request);
        if (CollectionUtils.isEmpty(orders)) {
            return SaveOrderResponse.builder().status(HttpStatus.BAD_REQUEST).build();
        }

        try {
            saveOrders(orders);
        } catch (Exception e) {
            return SaveOrderResponse.builder().status(HttpStatus.BAD_REQUEST).build();
        }
        return SaveOrderResponse.builder().status(HttpStatus.CREATED).build();
    }

    private void saveOrders(List<Orders> orders) {
        orderDao.saveAll(orders);
    }

    public List<OrderDTO> getOrdersBy(Long customerId) {
        Optional<Customer> customerOptional = customerService.findById(customerId);

        if (!customerOptional.isPresent()) {
            return Collections.emptyList();
        }
        return converter.convertOrdersToDTO(orderDao.getAllByCustomer(customerOptional.get()));
    }
}
