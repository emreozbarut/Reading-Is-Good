package com.readingisgood.dao;

import com.readingisgood.model.Customer;
import com.readingisgood.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDao extends JpaRepository<Orders, Long> {
    List<Orders> getAllByCustomer(Customer customer);
}
