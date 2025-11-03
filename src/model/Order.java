package model;

public class Order {
	// 1주차 요구사항: 필드 선언
	private String orderId;
	private String userId;
	private String stockCode;
	private String orderType;
	private int quantity;
	private double price;
  private String status;
  private String orderDate;
  private String executionDate;
  private double executedPrice;

  // 생성자 - 기본 값도 설정
  public Order(String orderId, String userId, String stockCode, String orderType, int quantity, double price) {
    this.orderId = orderId;
    this.userId = userId;
    this.stockCode = stockCode;
    this.orderType = orderType;
    this.quantity = quantity;
    this.price = price;
    this.status = "PENDING";
    this.orderDate = java.time.LocalDateTime.now().toString();
    this.executionDate = null;
    this.executedPrice = 0.0;
  }

	// getter/setter
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

	public String getOrderType() {
    return orderType;
	}

	public void setOrderType(String orderType) {
    this.orderType = orderType;
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

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getOrderDate() {
    return orderDate;
  }

  public void setOrderDate(String orderDate) {
    this.orderDate = orderDate;
  }

  public String getExecutionDate() {
    return executionDate;
  }

  public void setExecutionDate(String executionDate) {
    this.executionDate = executionDate;
  }

  public double getExecutedPrice() {
    return executedPrice;
  }

  public void setExecutedPrice(double executedPrice) {
    this.executedPrice = executedPrice;
	}
}
