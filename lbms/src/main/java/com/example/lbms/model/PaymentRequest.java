package com.example.lbms.model;

public class PaymentRequest {

	private int orderId;
	private double amount;
	private String method; // "CARD", "NETBANKING", "UPI"

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}
}