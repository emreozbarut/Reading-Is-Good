package com.readingisgood.converter.order;

import com.readingisgood.dto.BookDTO;
import com.readingisgood.dto.OrderDTO;
import com.readingisgood.dto.OrderDetail;
import com.readingisgood.dto.SkuDTO;
import com.readingisgood.model.Customer;
import com.readingisgood.model.Orders;
import com.readingisgood.model.Sku;
import com.readingisgood.model.enums.BaseStatus;
import com.readingisgood.request.order.SaveOrderRequest;
import com.readingisgood.service.BookService;
import com.readingisgood.service.CustomerService;
import com.readingisgood.service.SkuService;
import com.readingisgood.utils.SkuUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class OrderDTOConverter {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private BookService bookService;

    @Autowired
    private SkuService skuService;

    public List<OrderDTO> convertOrdersToDTO(List<Orders> orders) {
        return orders.stream().map(order -> OrderDTO.builder()
                .id(order.getId())
                .bookId(order.getBook().getId())
                .deleted(order.isDeleted())
                .customerId(order.getCustomer().getId())
                .quantity(order.getQuantity())
                .createdDate(order.getCreatedDate())
                .modifiedDate(order.getModifiedDate())
                .build())
                .collect(Collectors.toList());
    }

    public List<Orders> convertRequestToOrder(SaveOrderRequest request, Customer customer) {
        if (CollectionUtils.isEmpty(request.getOrderDetails())) {
            return Collections.emptyList();
        }
        List<Orders> orders = new ArrayList<>();
        for (OrderDetail orderDetail : request.getOrderDetails()) {
            Optional<BookDTO> bookOptional = bookService.findById(orderDetail.getBookId());
            if (bookOptional.isPresent() && validateAndUpdateSku(bookOptional.get(), orderDetail)) {
                orders.add(createOrder(customer, bookOptional.get().getId(), orderDetail.getQuantity()));
            }
        }
        return orders;
    }

    private boolean validateAndUpdateSku(BookDTO book, OrderDetail orderDetail) {
        Optional<SkuDTO> skuOptional = book.getSkus()
                .stream()
                .filter(sku -> sku.getId().equals(orderDetail.getSkuId()) && BaseStatus.Active.name().equals(sku.getStatus()))
                .findAny();
        if (!skuOptional.isPresent()) {
            return false;
        }
        Optional<Sku> optionalSku = skuService.findById(skuOptional.get().getId());
        if (!optionalSku.isPresent()) {
            return false;
        }
        Sku sku = optionalSku.get();
        if (SkuUtils.isStockNotEnough(sku.getStock(), orderDetail.getQuantity())) {
            return false;
        }
        sku.setStock(sku.getStock() - orderDetail.getQuantity());
        skuService.saveSku(sku);
        return true;
    }

    private Orders createOrder(Customer customer, Long bookId, Integer quantity) {
        return Orders.builder()
                .customer(customer)
                .book(bookService.findBy(bookId).orElseThrow())
                .quantity(quantity)
                .build();
    }
}
