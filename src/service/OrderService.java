package service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import model.Order;
import model.Transaction;
import model.User;
import repository.OrderRepository;
import repository.UserRepository;
// StockService is in the same package, no import needed

public class OrderService {

	private OrderRepository orderRepository;
	private StockService stockService;
	private PortfolioService portfolioService;
	private AuthService authService;

	public OrderService(OrderRepository orderRepository, StockService stockService, PortfolioService portfolioService, AuthService authService) {
		this.orderRepository = orderRepository;
		this.stockService = stockService;
		this.portfolioService = portfolioService;
		this.authService = authService;
	}

	private String getCurrentUserId() {
		User user = authService.getCurrentUser();
		if (user == null) {
			throw new IllegalStateException("로그인이 필요합니다.");
		}
		return user.getUserId();
	}

	// 3주차 요구사항: validateBuyOrder() - 매수 주문 검증
	public boolean validateBuyOrder(String stockCode, int quantity, double price) {
		String userId;
		try {
			userId = getCurrentUserId();
		} catch (IllegalStateException e) {
			System.out.println("오류: " + e.getMessage());
			return false;
		}

		if (quantity <= 0 || price <= 0) {
			System.out.println("오류: 수량과 가격은 양수여야 합니다.");
			return false;
		}

		double totalCost = quantity * price;
		if (!portfolioService.hasEnoughCash(userId, totalCost)) {
			System.out.println("오류: 잔액이 부족합니다. (필요: " + (long)totalCost + "원)");
			return false;
		}

		return true;
	}

	// 4주차 요구사항: 매수 주문 처리
	public boolean processBuyOrder(String stockCode, int quantity) {
		String userId;
		try {
			userId = getCurrentUserId();
		} catch (IllegalStateException e) {
			System.out.println("매수 실패: " + e.getMessage());
			return false;
		}

		// 1. 현재가 조회
		int currentPrice = stockService.getStockPrice(stockCode);
		if (currentPrice <= 0) {
			System.out.println("오류: 종목 정보를 찾을 수 없거나 가격 정보가 없습니다.");
			return false;
		}

		// 2. 유효성 검증
		if (!validateBuyOrder(stockCode, quantity, currentPrice)) {
			return false;
		}

		try {
			// 3. 포트폴리오 업데이트 (현금 차감 및 보유 주식 추가)
			portfolioService.buyStock(userId, stockCode, quantity, currentPrice);

			// 4. 주문 생성 및 저장
			String orderId = UUID.randomUUID().toString().substring(0, 8);
			Order order = new Order(orderId, userId, stockCode, "BUY", quantity, currentPrice);
			order.setStatus("EXECUTED");
			order.setExecutionDate(java.time.LocalDateTime.now().toString());
			order.setExecutedPrice(currentPrice);
			
			orderRepository.addOrder(order);

			System.out.println("매수 주문 체결 완료! " + stockCode + " " + quantity + "주 @ " + currentPrice + "원");
			return true;

		} catch (IllegalStateException e) {
			System.out.println("매수 실패: " + e.getMessage());
			return false;
		}
	}

	// 5주차 요구사항: 매도 주문 처리
	public boolean processSellOrder(String stockCode, int quantity) {
		String userId;
		try {
			userId = getCurrentUserId();
		} catch (IllegalStateException e) {
			System.out.println("매도 실패: " + e.getMessage());
			return false;
		}

		// 1. 현재가 조회
		int currentPrice = stockService.getStockPrice(stockCode);
		if (currentPrice <= 0) {
			System.out.println("오류: 종목 정보를 찾을 수 없습니다.");
			return false;
		}

		try {
			// 2. 포트폴리오 업데이트 (보유 주식 차감 및 현금 증가)
			portfolioService.sellStock(userId, stockCode, quantity, currentPrice);

			// 3. 주문 생성 및 저장
			String orderId = UUID.randomUUID().toString().substring(0, 8);
			Order order = new Order(orderId, userId, stockCode, "SELL", quantity, currentPrice);
			order.setStatus("EXECUTED");
			order.setExecutionDate(java.time.LocalDateTime.now().toString());
			order.setExecutedPrice(currentPrice);

			orderRepository.addOrder(order);

			System.out.println("매도 주문 체결 완료! " + stockCode + " " + quantity + "주 @ " + currentPrice + "원");
			return true;

		} catch (IllegalStateException e) {
			System.out.println("매도 실패: " + e.getMessage());
			return false;
		}
	}

	// 6주차 요구사항: getOrderHistory() - 거래 내역 조회
	public List<Transaction> getOrderHistory() {
		String userId;
		try {
			userId = getCurrentUserId();
		} catch (IllegalStateException e) {
			System.out.println("오류: " + e.getMessage());
			return new ArrayList<>();
		}

		List<Order> userOrders = orderRepository.getOrdersByUserId(userId);
		List<Transaction> transactions = new ArrayList<>();

		for (Order order : userOrders) {
			// Order -> Transaction 변환
			if ("EXECUTED".equals(order.getStatus())) {
				Transaction tx = new Transaction(
						UUID.randomUUID().toString().substring(0, 8),
						order.getOrderId(),
						order.getUserId(),
						order.getStockCode(),
						order.getOrderType(),
						order.getQuantity(),
						order.getExecutedPrice()
				);
				transactions.add(tx);
			}
		}
		return transactions;
	}
}
