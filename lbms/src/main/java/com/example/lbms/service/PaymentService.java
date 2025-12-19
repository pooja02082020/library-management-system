package com.example.lbms.service;

import java.util.Random;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.lbms.model.PaymentRequest;
import com.example.lbms.model.PaymentResponse;

@Service
public class PaymentService {

    public PaymentResponse processPayment(PaymentRequest req) {

        // simple deterministic simulation:
        // if amount < 1000 -> success, else 50% random
        boolean success;

        if (req.getAmount() < 1000)
            success = true;
        else
            success = new Random().nextBoolean();

        String txId = "TXN-" + UUID.randomUUID().toString().substring(0, 8);

        if (success) {
            return new PaymentResponse(
                    req.getOrderId(),
                    true,
                    txId,
                    "Payment successful"
            );
        } else {
            return new PaymentResponse(
                    req.getOrderId(),
                    false,
                    txId,
                    "Payment failed: insufficient funds (simulated)"
            );
        }
    }
}
