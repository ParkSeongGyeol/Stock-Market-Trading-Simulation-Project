package service;

import java.util.List;

import model.Order;
import model.Transaction;

public class OrderService {

	// 3주차 요구사항: validateBuyOrder() - 매수 주문 검증
	public boolean validateBuyOrder(String userId, String stockCode, int quantity, double price) {
		// TODO: 잔액 확인 로직 구현 필요
		// TODO: 수량 검증 로직 구현 필요
		return false;
	}

	// 6주차 요구사항: getOrderHistory() - 거래 내역 조회
	public List<Transaction> getOrderHistory(String userId) {
		// TODO: 최근 거래 10건 조회 로직 구현 필요
		// TODO: 거래 내역 포맷팅 구현 필요
		return null;
	}
}
