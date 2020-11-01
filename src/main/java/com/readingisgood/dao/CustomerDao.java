package com.readingisgood.dao;

import com.readingisgood.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerDao extends JpaRepository<Customer, Long> {
    public Optional<Customer> findByName(String name);
}
