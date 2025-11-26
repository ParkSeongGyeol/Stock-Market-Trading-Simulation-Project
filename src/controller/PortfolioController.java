package controller;

import model.Portfolio;
import model.Holding;
import service.PortfolioService;
import view.PortfolioView;

import java.util.Scanner;

public class PortfolioController {

    private final PortfolioService portfolioService;
    private final PortfolioView portfolioView;
    private final Scanner scanner;

    public PortfolioController(PortfolioService portfolioService, PortfolioView portfolioView) {
        this.portfolioService = portfolioService;
        this.portfolioView = portfolioView;
        this.scanner = new Scanner(System.in);
    }


    public void showPortfolioMenu(String userId) {
        while (true) {
            System.out.println("\n===== 포트폴리오 관리 =====");
            System.out.println("1. 포트폴리오 조회");
            System.out.println("2. 매수");
            System.out.println("3. 매도");
            System.out.println("4. 평가 가치 업데이트");
            System.out.println("5. 뒤로가기");
            System.out.print("메뉴 선택: ");

            int menu = inputNumber();
            switch (menu) {
                case 1 -> displayPortfolio(userId);
                case 2 -> buyStock(userId);
                case 3 -> sellStock(userId);
                case 4 -> updatePortfolioValue(userId);
                case 5 -> {
                    System.out.println("메인 메뉴로 돌아갑니다.");
                    return;
                }
                default -> System.out.println("잘못된 입력입니다.");
            }
        }
    }


    private int inputNumber() {
        while (!scanner.hasNextInt()) {
            System.out.println("숫자를 입력하세요.");
            scanner.next();
        }
        return scanner.nextInt();
    }


    private void displayPortfolio(String userId) {
        Portfolio portfolio = portfolioService.getPortfolio(userId);

        if (portfolio == null) {
            System.out.println("포트폴리오가 존재하지 않습니다.");
            return;
        }

        portfolioView.displayPortfolio(portfolio);
    }

    private void buyStock(String userId) {
        System.out.print("종목 코드 입력: ");
        String stockCode = scanner.next();

        System.out.print("매수 수량 입력: ");
        int quantity = inputNumber();

        System.out.print("매수 가격 입력: ");
        double price = scanner.nextDouble();

        try {
            portfolioService.buyStock(userId, stockCode, quantity, price);
            System.out.println("매수 완료!");
        } catch (Exception e) {
            System.out.println("매수 실패: " + e.getMessage());
        }
    }


    private void sellStock(String userId) {
        System.out.print("종목 코드 입력: ");
        String stockCode = scanner.next();

        Portfolio p = portfolioService.getPortfolio(userId);
        Holding holding = (p != null) ? p.getHolding(stockCode) : null;

        if (holding == null) {
            System.out.println("해당 종목을 보유하고 있지 않습니다.");
            return;
        }

        System.out.printf("보유 수량: %d개\n", holding.getQuantity());
        System.out.print("매도 수량 입력: ");
        int quantity = inputNumber();

        System.out.print("매도 가격 입력: ");
        double price = scanner.nextDouble();

        try {
            portfolioService.sellStock(userId, stockCode, quantity, price);
            System.out.println("매도 완료!");
        } catch (Exception e) {
            System.out.println("매도 실패: " + e.getMessage());
        }
    }


    private void updatePortfolioValue(String userId) {
        try {
            portfolioService.updateAfterTrade(userId);
            System.out.println("포트폴리오 평가 가치 업데이트 완료!");
        } catch (Exception e) {
            System.out.println("업데이트 실패: " + e.getMessage());
        }
    }
}
