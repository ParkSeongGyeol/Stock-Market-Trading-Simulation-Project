package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {
	// 6주차 요구사항: 거래 내역 모델
	private String transactionId;
	private String orderId;
	private String userId;
	private String stockCode;
	private String transactionType; // "BUY" 또는 "SELL"
	private int quantity;
	private double price;
	private LocalDateTime transactionDate;

	public Transaction(String transactionId, String orderId, String userId, String stockCode, String transactionType,
			int quantity, double price) {
		this.transactionId = transactionId;
		this.orderId = orderId;
		this.userId = userId;
		this.stockCode = stockCode;
		this.transactionType = transactionType;
		this.quantity = quantity;
		this.price = price;
		this.transactionDate = LocalDateTime.now();
	}

	// getter/setter
	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getStockCode() {
		return stockCode;
	}

	public void setStockCode(String stockCode) {
		this.stockCode = stockCode;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public LocalDateTime getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(LocalDateTime transactionDate) {
		this.transactionDate = transactionDate;
	}

	@Override
	public String toString() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		return String.format("[%s] %s | %s | %s | %d주 | @%,.0f원", 
				transactionDate.format(formatter), 
				transactionType.equals("BUY") ? "매수" : "매도",
				stockCode, 
				userId,
				quantity, 
				price);
	}
}
