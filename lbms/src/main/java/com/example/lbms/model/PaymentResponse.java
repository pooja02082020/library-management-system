package com.example.lbms.model;

public class PaymentResponse {

	private int orderId;
	private boolean success;
	private String transactionId;
	private String message;

	public PaymentResponse(int orderId, boolean success, String transactionId, String message) {
		super();
		this.orderId = orderId;
		this.success = success;
		this.transactionId = transactionId;
		this.message = message;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
