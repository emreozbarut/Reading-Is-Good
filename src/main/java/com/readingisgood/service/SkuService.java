package com.readingisgood.service;

import com.readingisgood.dao.SkuDao;
import com.readingisgood.dto.SkuDTO;
import com.readingisgood.model.Sku;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SkuService {

    private SkuDao skuDao;

    public SkuService(SkuDao skuDao) {
        this.skuDao = skuDao;
    }

    public Optional<Sku> findById(Long id) {
        return skuDao.findById(id);
    }

    public List<SkuDTO> getBookStock() {
        return convertSkuToDTO(skuDao.findAll());
    }

    public void saveSku(Sku sku) {
        skuDao.save(sku);
    }

    private List<SkuDTO> convertSkuToDTO(List<Sku> skus) {
        return skus.stream().map(sku -> SkuDTO.builder()
                    .id(sku.getId())
                    .status(sku.getStatus().name())
                    .bookId(sku.getBook().getId())
                    .stock(sku.getStock())
                    .title(sku.getBook().getTitle())
                    .build())
                .collect(Collectors.toList());
    }
}
