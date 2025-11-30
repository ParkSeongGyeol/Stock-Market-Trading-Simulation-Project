package main;

import java.util.List;

import model.Transaction;
import model.User;
import repository.OrderRepository;
import repository.PortfolioRepository;
import repository.StockRepository;
import repository.UserRepository;
import service.AuthService;
import service.OrderService;
import service.PortfolioService;
import service.StockService;
import service.UserService;

public class TestTrading {

	public static void main(String[] args) {
		System.out.println("=== 거래 엔진 테스트 시작 ===");

		// 1. 저장소 및 서비스 초기화
		StockRepository stockRepo = new StockRepository();
		StockService stockService = new StockService(stockRepo);
		
		UserRepository userRepo = new UserRepository();
		AuthService authService = new AuthService(); // Mock or simple
		UserService userService = new UserService(userRepo, authService);
		
		PortfolioRepository portfolioRepo = new PortfolioRepository();
		PortfolioService portfolioService = new PortfolioService(portfolioRepo);
		
		OrderRepository orderRepo = new OrderRepository();
		OrderService orderService = new OrderService(orderRepo, stockService, portfolioService, authService);

		// 2. 테스트 데이터 준비
		// 사용자 생성 (1000만원 보유)
		User user = new User("testUser", "1234", "테스터", null);
		user.setBalance(10000000);
		userRepo.addUser(user);
		
		// 로그인 처리 (AuthService를 통해 현재 사용자 설정)
		authService.setLoggedInUser(user);
		
		// 포트폴리오 초기화 (사용자 잔액과 동기화)
		portfolioRepo.createPortfolio(user.getUserId(), user.getBalance());
		
		System.out.println("사용자 생성 완료: " + user.getUserName() + ", 잔액: " + user.getBalance());

		// 종목 확인 (파일에서 로드된 첫번째 종목 사용)
		if (stockRepo.getAllStocks().isEmpty()) {
			System.out.println("오류: 종목 데이터가 없습니다. stock_data.txt를 확인하세요.");
			return;
		}
		String stockCode = stockRepo.getAllStocks().get(0).getStockCode();
		String stockName = stockRepo.getAllStocks().get(0).getStockName();
		int currentPrice = stockRepo.getAllStocks().get(0).getCurrentPrice();
		System.out.println("테스트 종목: " + stockName + " (" + stockCode + "), 가격: " + currentPrice);

		// 3. 매수 테스트
		System.out.println("\n[매수 테스트]");
		int buyQty = 10;
		// userId 파라미터 제거됨
		boolean buyResult = orderService.processBuyOrder(stockCode, buyQty);
		System.out.println("매수 성공 여부: " + buyResult);
		System.out.println("매수 후 포트폴리오 잔액: " + (long)portfolioService.getPortfolio(user.getUserId()).getCashBalance());

		// 4. 매도 테스트
		System.out.println("\n[매도 테스트]");
		int sellQty = 5;
		// userId 파라미터 제거됨
		boolean sellResult = orderService.processSellOrder(stockCode, sellQty);
		System.out.println("매도 성공 여부: " + sellResult);
		System.out.println("매도 후 포트폴리오 잔액: " + (long)portfolioService.getPortfolio(user.getUserId()).getCashBalance());

		// 5. 거래 내역 조회 테스트
		System.out.println("\n[거래 내역 조회]");
		// userId 파라미터 제거됨
		List<Transaction> history = orderService.getOrderHistory();
		for (Transaction tx : history) {
			System.out.println(tx);
		}
		
		System.out.println("\n=== 거래 엔진 테스트 종료 ===");
	}
}
