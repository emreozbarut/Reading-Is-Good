package com.readingisgood.controller;

import com.readingisgood.dto.CustomerDTO;
import com.readingisgood.request.customer.RegisterCustomerRequest;
import com.readingisgood.response.customer.RegisterCustomerResponse;
import com.readingisgood.service.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/api/customer")
public class CustomerController {

    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @ResponseBody
    @GetMapping("/findAll")
    public List<CustomerDTO> findAll() {
        return customerService.findAll();
    }

    @ResponseBody
    @PostMapping("/register")
    public RegisterCustomerResponse register(@RequestBody RegisterCustomerRequest request) {
        return customerService.register(request);
    }
}
