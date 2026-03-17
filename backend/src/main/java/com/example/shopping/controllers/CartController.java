package com.example.shopping.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.shopping.dto.AddToCartRequest;
import com.example.shopping.model.Cart;
import com.example.shopping.dto.UpdateCartRequest;
import com.example.shopping.services.CartService;

@RestController
@RequestMapping("/api/v1/cart")
@CrossOrigin(origins = "http://furniture-storefrontend.s3-website-us-east-1.amazonaws.com",
             allowCredentials = "true")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("/{userId}")
    public ResponseEntity<Cart> getCart(@PathVariable String userId) {
        return ResponseEntity.ok(cartService.getCartByUserId(userId));
    }

    @PostMapping("/add")
    public ResponseEntity<Cart> addToCart(@RequestBody AddToCartRequest request) {
        return ResponseEntity.ok(cartService.addToCart(request));
    }

    @PutMapping("/update")
    public ResponseEntity<Cart> updateCart(@RequestBody UpdateCartRequest request) {
        return ResponseEntity.ok(cartService.updateCart(request));
    }

    @DeleteMapping("/remove/{furnitureId}")
    public ResponseEntity<Cart> removeFromCart(@PathVariable String furnitureId,
                                               @RequestParam String userId) {
        return ResponseEntity.ok(cartService.removeFromCart(userId, furnitureId));
    }
}