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

/**
 * REST Controller for handling payment-related operations.
 * This controller simulates payment processing.
 */
@RestController
@RequestMapping("/api/payments")
public class PaymentController {

	// Service layer dependency for payment processing logic
	private final PaymentService paymentService;

	// Constructor-based dependency injection
	public PaymentController(PaymentService p) {
		this.paymentService = p;
	}

	/**
	 * Process a payment request (simulation)
	 * URL: POST /api/payments/pay
	 *
	 * @param req PaymentRequest containing payment details
	 * @return PaymentResponse with success or failure status
	 *         - 200 OK if payment is successful
	 *         - 402 PAYMENT_REQUIRED if payment fails
	 */
	@PostMapping("/pay")
	public ResponseEntity<PaymentResponse> pay(@RequestBody PaymentRequest req) {

		// Process payment using service layer
		PaymentResponse resp = paymentService.processPayment(req);

		// Return appropriate HTTP status based on payment result
		if (resp.isSuccess()) {
			return ResponseEntity.ok(resp);
		}

		return ResponseEntity
				.status(HttpStatus.PAYMENT_REQUIRED)
				.body(resp);
	}
}
