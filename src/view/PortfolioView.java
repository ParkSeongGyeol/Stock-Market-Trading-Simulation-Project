package view;

import model.Portfolio;
import model.Holding;

public class PortfolioView {
    public void displayPortfolio(Portfolio p) {
        System.out.println("====== " + p.getUserId() + "님의 포트폴리오 ======");
        System.out.printf("보유 현금: %.2f원%n", p.getCashBalance());
        System.out.printf("총 자산: %.2f원%n", p.getTotalAssets());
        System.out.println("보유 종목:");
        for (Holding h : p.getHoldings().values()) {
            System.out.printf(" - %s | 수량: %d | 평균가: %.2f%n",
                    h.getStockCode(), h.getQuantity(), h.getAvgPrice());
        }
        System.out.println("==============================");
    }
}
