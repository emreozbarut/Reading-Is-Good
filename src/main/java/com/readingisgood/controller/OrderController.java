package com.readingisgood.controller;

import com.readingisgood.dto.OrderDTO;
import com.readingisgood.request.order.SaveOrderRequest;
import com.readingisgood.response.order.SaveOrderResponse;
import com.readingisgood.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/api/order")
public class OrderController {

    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @ResponseBody
    @GetMapping("/findAll")
    public List<OrderDTO> findAll() {
        return orderService.findAll();
    }

    @ResponseBody
    @PostMapping("/save")
    public SaveOrderResponse save(@RequestBody SaveOrderRequest saveOrderRequest, HttpServletRequest request) {
        return orderService.save(saveOrderRequest, request);
    }

    @ResponseBody
    @GetMapping("/getOrders/{customerId}")
    public List<OrderDTO> getOrdersBy(@PathVariable(name = "customerId") Long customerId) {
        return orderService.getOrdersBy(customerId);
    }

    @ResponseBody
    @GetMapping("/getCurrentUserOrders")
    public List<OrderDTO> getCurrentUserOrders(HttpServletRequest request) {
        return orderService.getCurrentUserOrders(request);
    }
}
