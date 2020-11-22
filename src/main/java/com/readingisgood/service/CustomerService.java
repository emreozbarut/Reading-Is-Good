package com.readingisgood.service;

import com.readingisgood.configuration.security.jwt.JwtTokenProvider;
import com.readingisgood.converter.customer.CustomerDTOConverter;
import com.readingisgood.dao.CustomerDao;
import com.readingisgood.dto.CustomerDTO;
import com.readingisgood.model.Customer;
import com.readingisgood.request.customer.RegisterCustomerRequest;
import com.readingisgood.response.customer.RegisterCustomerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerDTOConverter converter;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    private CustomerDao customerDao;

    public CustomerService(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    public List<CustomerDTO> findAll() {
        return converter.convertCustomerToDTO(customerDao.findAll());
    }

    public RegisterCustomerResponse register(RegisterCustomerRequest request) {
        return saveCustomer(converter.convertRequestToCustomer(request));
    }

    private RegisterCustomerResponse saveCustomer(Customer customer) {
        RegisterCustomerResponse response = RegisterCustomerResponse.builder().build();
        try {
            customerDao.save(customer);
            response.setBearerToken(jwtTokenProvider.createToken(customer.getUsername(), customer.getRoles()));
        } catch (Exception e) {
            response.setStatus(HttpStatus.BAD_REQUEST);
            return response;
        }
        response.setStatus(HttpStatus.CREATED);
        return response;
    }

    Optional<Customer> findById(Long customerId) {
        return customerDao.findById(customerId);
    }

    public Optional<Customer> findByUsername(String username) {
        return customerDao.findByUsername(username);
    }
}
