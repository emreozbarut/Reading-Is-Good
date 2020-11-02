package com.readingisgood.service;

import com.readingisgood.converter.sku.SkuConverter;
import com.readingisgood.dao.SkuDao;
import com.readingisgood.dto.SkuDTO;
import com.readingisgood.model.Sku;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SkuService {

    @Autowired
    private SkuConverter converter;

    private SkuDao skuDao;

    public SkuService(SkuDao skuDao) {
        this.skuDao = skuDao;
    }

    public Optional<Sku> findById(Long id) {
        return skuDao.findById(id);
    }

    public List<SkuDTO> getBookStock() {
        return converter.convert(skuDao.findAll());
    }

    public void saveSku(Sku sku) {
        skuDao.save(sku);
    }
}
