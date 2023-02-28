package com.epam.esm.web.controller;

import com.epam.esm.model.entity.Order;
import com.epam.esm.service.OrderService;
import com.epam.esm.web.link.OrdersLinkAdder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrdersController {

    private final OrderService orderService;
    private final OrdersLinkAdder linkAdder;

    @Autowired
    public OrdersController(OrderService orderService, OrdersLinkAdder linkAdder) {
        this.orderService = orderService;
        this.linkAdder = linkAdder;
    }

    @GetMapping("/users/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public List<Order> getAll(@PathVariable long userId,
                              @RequestParam(value = "page", defaultValue = "0", required = false) int page,
                              @RequestParam(value = "size", defaultValue = "25", required = false) int size) {

        List<Order> all = orderService.getAllByUserId(userId, page, size);
        all.forEach(linkAdder::addLinks);
        return all;
    }

    @PostMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public Order create(@PathVariable long userId,
                        @RequestParam long certificateId) {

        Order order = orderService.create(userId, certificateId);
        linkAdder.addLinks(order);
        return order;
    }

    @GetMapping("/{orderId}")
    @ResponseStatus(HttpStatus.OK)
    public Order getById(@PathVariable long orderId) {
        Order order = orderService.getById(orderId);
        linkAdder.addLinks(order);
        return order;
    }
}
