package com.example.shopping.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.shopping.dto.CheckoutRequest;
import com.example.shopping.dto.OrderResponse;
import com.example.shopping.services.CheckoutService;

@RestController
@RequestMapping("/api/v1/checkout")
@CrossOrigin(origins = {"http://furniture-storefrontend.s3-website-us-east-1.amazonaws.com",
                        "http://localhost:3000",
                    },
             allowCredentials = "true")
public class CheckoutController {

    @Autowired
    private CheckoutService checkoutService;

    @PostMapping
    public ResponseEntity<OrderResponse> checkout(@RequestBody CheckoutRequest request) {
        return ResponseEntity.ok(checkoutService.checkout(request));
    }
}