package com.example.shopping.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.shopping.model.Order;
import com.example.shopping.repositories.OrderRepository;

@RestController
@RequestMapping("/api/v1/orders")
@CrossOrigin(origins = {"http://furniture-storefrontend.s3-website-us-east-1.amazonaws.com",
                        "http://localhost:3000",
                    },
             allowCredentials = "true")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @GetMapping("/{orderId}")
    public ResponseEntity<Order> getOrderById(@PathVariable String orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        return ResponseEntity.ok(order);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Order>> getOrdersByUserId(@PathVariable String userId) {
        return ResponseEntity.ok(orderRepository.findByUserId(userId));
    }
}