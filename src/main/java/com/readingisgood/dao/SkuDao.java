package com.readingisgood.dao;

import com.readingisgood.model.Sku;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkuDao extends JpaRepository<Sku, Long> {
}
