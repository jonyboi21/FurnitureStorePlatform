package com.example.shopping.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.example.shopping.dto.PaymentEvent;

@Service
public class PaymentProducer {

    private static final String PAYMENT_REQUESTED_TOPIC = "payment-requested";

    @Autowired
    private KafkaTemplate<String, PaymentEvent> kafkaTemplate;

    public void sendPaymentRequested(PaymentEvent event) {
        kafkaTemplate.send(PAYMENT_REQUESTED_TOPIC, event.getOrderId(), event);
    }
}