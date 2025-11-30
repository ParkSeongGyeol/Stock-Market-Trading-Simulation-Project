package repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import model.Order;

public class OrderRepository {
	// 2주차 요구사항: ArrayList 선언
	private ArrayList<Order> orders = new ArrayList<>();

	// 2주차 요구사항: addOrder() 메서드
	public void addOrder(Order order) {
		orders.add(order);
		System.out.println("DEBUG: 주문 저장됨 - " + order.getOrderId());
	}

	// 2주차 요구사항: getOrdersByUserId() 메서드
	public List<Order> getOrdersByUserId(String userId) {
		return orders.stream()
				.filter(order -> order.getUserId().equals(userId))
				.collect(Collectors.toList());
	}
	
	public List<Order> getAllOrders() {
		return new ArrayList<>(orders);
	}
}
