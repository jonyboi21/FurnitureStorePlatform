package com.example.shopping.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.example.shopping.model.Order;
import com.example.shopping.dto.PaymentEvent;
import com.example.shopping.repositories.OrderRepository;
import com.example.shopping.services.CartService;

@Service
public class PaymentConsumer {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CartService cartService;

    @KafkaListener(topics = "payment-requested", groupId = "payment-group")
    public void processPayment(PaymentEvent event) {
        Order order = orderRepository.findById(event.getOrderId())
                .orElseThrow(() -> new RuntimeException("Order not found"));

        try {
            boolean paymentApproved = true;

            if (paymentApproved) {
                order.setStatus("PAID");
                orderRepository.save(order);
                cartService.clearCart(order.getUserId());
            } else {
                order.setStatus("PAYMENT_FAILED");
                orderRepository.save(order);
            }
        } catch (Exception e) {
            order.setStatus("PAYMENT_FAILED");
            orderRepository.save(order);
        }
    }
}