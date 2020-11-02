package com.readingisgood.converter.sku;

import com.readingisgood.dto.SkuDTO;
import com.readingisgood.model.Sku;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SkuConverter {

    public List<SkuDTO> convert(List<Sku> skus) {
        return skus.stream().map(this::convertSkuToDTO)
                .collect(Collectors.toList());
    }

    public SkuDTO convertSkuToDTO(Sku sku) {
        return SkuDTO.builder()
                .id(sku.getId())
                .status(sku.getStatus().name())
                .bookId(sku.getBook().getId())
                .stock(sku.getStock())
                .title(sku.getBook().getTitle())
                .build();
    }
}
