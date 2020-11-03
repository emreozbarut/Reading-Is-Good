package com.readingisgood.service;

import com.readingisgood.configuration.security.jwt.JwtTokenProvider;
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
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderDTOConverter converter;

    @Autowired
    private JwtTokenProvider provider;

    private OrderDao orderDao;
    private CustomerService customerService;

    public OrderService(OrderDao orderDao, CustomerService customerService) {
        this.orderDao = orderDao;
        this.customerService = customerService;
    }

    public List<OrderDTO> findAll() {
        return converter.convertOrdersToDTO(orderDao.findAll());
    }

    public SaveOrderResponse save(SaveOrderRequest saveOrderRequest, HttpServletRequest request) {
        String token = provider.resolveToken(request);
        if (StringUtils.isEmpty(token) || !provider.validateToken(token)) {
            return SaveOrderResponse.builder().status(HttpStatus.BAD_REQUEST).build();
        }
        Customer customer = (Customer) provider.getCurrentUser(token);
        if (Objects.isNull(customer)) {
            return SaveOrderResponse.builder().status(HttpStatus.BAD_REQUEST).build();
        }
        List<Orders> orders = converter.convertRequestToOrder(saveOrderRequest, customer);
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

    public List<OrderDTO> getCurrentUserOrders(HttpServletRequest request) {
        String token = provider.resolveToken(request);
        if (StringUtils.isEmpty(token) || !provider.validateToken(token)) {
            return Collections.emptyList();
        }
        Customer customer = (Customer) provider.getCurrentUser(token);
        if (Objects.isNull(customer)) {
            return Collections.emptyList();
        }
        return converter.convertOrdersToDTO(orderDao.getAllByCustomer(customer));
    }
}
