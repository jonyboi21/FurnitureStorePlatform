package com.example.shopping.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.shopping.dto.AddToCartRequest;
import com.example.shopping.model.Cart;
import com.example.shopping.model.CartItem;
import com.example.shopping.dto.UpdateCartRequest;
import com.example.shopping.model.furniture;
import com.example.shopping.repositories.CartRepository;
import com.example.shopping.repositories.furnitureRepo;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private furnitureRepo furnitureRepo;

    public Cart getCartByUserId(String userId) {
        return cartRepository.findByUserId(userId)
                .orElseGet(() -> {
                    Cart cart = new Cart();
                    cart.setUserId(userId);
                    cart.setItems(new ArrayList<>());
                    cart.setUpdatedAt(LocalDateTime.now());
                    return cartRepository.save(cart);
                });
    }

    public Cart addToCart(AddToCartRequest request) {
        Cart cart = getCartByUserId(request.getUserId());

        furniture product = furnitureRepo.findById(request.getFurnitureId())
                .orElseThrow(() -> new RuntimeException("Furniture not found"));

        Optional<CartItem> existingItem = cart.getItems().stream()
                .filter(item -> item.getFurnitureId().equals(request.getFurnitureId()))
                .findFirst();

        if (existingItem.isPresent()) {
            CartItem item = existingItem.get();
            item.setQuantity(item.getQuantity() + request.getQuantity());
        } else {
            CartItem item = new CartItem();
            item.setFurnitureId(product.getId());
            item.setFurnitureName(product.getFurnitureName());
            item.setUnitPrice(product.getPrice());
            item.setQuantity(request.getQuantity());
            item.setImageUrl(product.getImageUrl());
            cart.getItems().add(item);
        }

        cart.setUpdatedAt(LocalDateTime.now());
        return cartRepository.save(cart);
    }

    public Cart updateCart(UpdateCartRequest request) {
        Cart cart = getCartByUserId(request.getUserId());

        CartItem item = cart.getItems().stream()
                .filter(cartItem -> cartItem.getFurnitureId().equals(request.getFurnitureId()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Item not found in cart"));

        if (request.getQuantity() <= 0) {
            cart.getItems().remove(item);
        } else {
            item.setQuantity(request.getQuantity());
        }

        cart.setUpdatedAt(LocalDateTime.now());
        return cartRepository.save(cart);
    }

    public Cart removeFromCart(String userId, String furnitureId) {
        Cart cart = getCartByUserId(userId);
        cart.getItems().removeIf(item -> item.getFurnitureId().equals(furnitureId));
        cart.setUpdatedAt(LocalDateTime.now());
        return cartRepository.save(cart);
    }

    public void clearCart(String userId) {
        Cart cart = getCartByUserId(userId);
        cart.setItems(new ArrayList<>());
        cart.setUpdatedAt(LocalDateTime.now());
        cartRepository.save(cart);
    }
}