package com.readingisgood.converter.customer;

import com.readingisgood.converter.order.OrderDTOConverter;
import com.readingisgood.dto.CustomerDTO;
import com.readingisgood.model.Customer;
import com.readingisgood.model.enums.BaseStatus;
import com.readingisgood.request.customer.RegisterCustomerRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomerDTOConverter {

    @Autowired
    private OrderDTOConverter orderDTOConverter;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<CustomerDTO> convertCustomerToDTO(List<Customer> customers) {
        return customers.stream().map(customer -> CustomerDTO.builder()
                .id(customer.getId())
                .deleted(customer.isDeleted())
                .status(customer.getStatus().name())
                .name(customer.getName())
                .surname(customer.getSurname())
                .username(customer.getUsername())
                .roles(customer.getRoles())
                .email(customer.getEmail())
                .createdDate(customer.getCreatedDate())
                .modifiedDate(customer.getModifiedDate())
                .orders(orderDTOConverter.convertOrdersToDTO(customer.getOrders()))
                .build())
                .collect(Collectors.toList());
    }

    public Customer convertRequestToCustomer(RegisterCustomerRequest request) {
        return Customer.builder()
                .name(request.getName())
                .surname(request.getSurname())
                .username(request.getUsername())
                .roles(Collections.singletonList("ROLE_USER"))
                .status(BaseStatus.Active)
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();
    }
}
