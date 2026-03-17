package com.example.shopping.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.shopping.kafka.PaymentProducer;
import com.example.shopping.model.Cart;
import com.example.shopping.model.CartItem;
import com.example.shopping.dto.CheckoutRequest;
import com.example.shopping.model.Order;
import com.example.shopping.model.OrderItem;
import com.example.shopping.dto.OrderResponse;
import com.example.shopping.dto.PaymentEvent;
import com.example.shopping.repositories.OrderRepository;

@Service
public class CheckoutService {

    @Autowired
    private CartService cartService;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private PaymentProducer paymentProducer;

    public OrderResponse checkout(CheckoutRequest request) {
        Cart cart = cartService.getCartByUserId(request.getUserId());

        if (cart.getItems() == null || cart.getItems().isEmpty()) {
            throw new RuntimeException("Cart is empty");
        }

        List<OrderItem> orderItems = new ArrayList<>();
        double total = 0.0;

        for (CartItem cartItem : cart.getItems()) {
            double subTotal = cartItem.getUnitPrice() * cartItem.getQuantity();

            OrderItem orderItem = new OrderItem();
            orderItem.setFurnitureId(cartItem.getFurnitureId());
            orderItem.setFurnitureName(cartItem.getFurnitureName());
            orderItem.setUnitPrice(cartItem.getUnitPrice());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setSubTotal(subTotal);
            orderItem.setImageUrl(cartItem.getImageUrl());

            orderItems.add(orderItem);
            total += subTotal;
        }

        Order order = new Order();
        order.setUserId(request.getUserId());
        order.setItems(orderItems);
        order.setTotalAmount(total);
        order.setStatus("PENDING_PAYMENT");

        Order savedOrder = orderRepository.save(order);

        PaymentEvent event = new PaymentEvent(
                savedOrder.getTotalAmount(),
                savedOrder.getId(),
                savedOrder.getUserId(),
                request.getPaymentMethod(),
                "REQUESTED"
        );

        paymentProducer.sendPaymentRequested(event);

        return new OrderResponse(savedOrder.getId(), savedOrder.getStatus(), savedOrder.getTotalAmount());
    }
}