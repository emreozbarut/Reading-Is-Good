package com.readingisgood.service;

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
        try {
            customerDao.save(customer);
        } catch (Exception e) {
            return RegisterCustomerResponse.builder().status(HttpStatus.BAD_REQUEST).build();
        }
        return RegisterCustomerResponse.builder().status(HttpStatus.CREATED).build();
    }

    public Optional<Customer> findById(Long customerId) {
        return customerDao.findById(customerId);
    }
}
