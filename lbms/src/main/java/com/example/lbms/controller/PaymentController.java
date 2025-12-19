package com.example.lbms.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.lbms.model.PaymentRequest;
import com.example.lbms.model.PaymentResponse;
import com.example.lbms.service.PaymentService;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

	private final PaymentService paymentService;

	public PaymentController(PaymentService p) {
		this.paymentService = p;
	}

	///FOR POSTMAPPING FOR PAYMENT SIMULATION
	@PostMapping("/pay")
	public ResponseEntity<PaymentResponse> pay(@RequestBody PaymentRequest req) {
		PaymentResponse resp = paymentService.processPayment(req);
		if (resp.isSuccess())
			return ResponseEntity.ok(resp);
		return ResponseEntity.status(HttpStatus.PAYMENT_REQUIRED).body(resp);
	}
}