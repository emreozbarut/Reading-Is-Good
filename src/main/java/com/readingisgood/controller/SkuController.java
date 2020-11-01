package com.readingisgood.controller;

import com.readingisgood.dto.SkuDTO;
import com.readingisgood.service.SkuService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/api/sku")
public class SkuController {

    private SkuService skuService;

    public SkuController(SkuService skuService) {
        this.skuService = skuService;
    }

    @ResponseBody
    @GetMapping("/book/stock")
    public List<SkuDTO> getBookStock() {
        return skuService.getBookStock();
    }
}
