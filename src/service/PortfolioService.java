package service;

import model.Holding;
import model.Portfolio;
import repository.PortfolioRepository;

public class PortfolioService {

    private final PortfolioRepository portfolioRepository;

    public PortfolioService(PortfolioRepository portfolioRepository) {
        this.portfolioRepository = portfolioRepository;
    }

    // ⭐ Controller가 반드시 필요로 하는 메서드
    public Portfolio getPortfolio(String userId) {
        return portfolioRepository.getPortfolio(userId);
    }

    public boolean hasEnoughCash(String userId, double amount) {
        Portfolio p = getPortfolio(userId);
        return p != null && p.getCashBalance() >= amount;
    }

    public void updateCash(String userId, double delta) {
        Portfolio p = getPortfolio(userId);
        if (p == null) throw new IllegalStateException("포트폴리오 없음");

        double newBalance = p.getCashBalance() + delta;
        if (newBalance < 0)
            throw new IllegalStateException("잔액 부족");

        p.setCashBalance(newBalance);
        portfolioRepository.save(p);
    }

    public void buyStock(String userId, String stockCode, int quantity, double price) {
        Portfolio p = getPortfolio(userId);
        if (p == null) throw new IllegalStateException("포트폴리오 없음");

        double cost = price * quantity;
        if (!hasEnoughCash(userId, cost))
            throw new IllegalStateException("잔액 부족");

        // 잔액 차감
        updateCash(userId, -cost);

        // 보유 종목 업데이트
        Holding holding = p.getHolding(stockCode);

        if (holding == null) {
            holding = new Holding(stockCode, quantity, price);
        } else {
            holding.updateAvgPrice(quantity, price);
        }

        p.addHolding(stockCode, holding);
        portfolioRepository.save(p);
    }

    // ⭐ Controller 시그니처에 맞춘 판매 메서드
    public void sellStock(String userId, String stockCode, int quantity, double price) {
        Portfolio p = getPortfolio(userId);
        if (p == null) throw new IllegalStateException("포트폴리오 없음");

        Holding holding = p.getHolding(stockCode);
        if (holding == null)
            throw new IllegalStateException("보유 종목 없음");

        if (holding.getQuantity() < quantity)
            throw new IllegalStateException("보유 수량 부족");

        double revenue = quantity * price;

        // 잔액 증가
        updateCash(userId, revenue);

        // 보유수량 감소
        holding.reduceQuantity(quantity);

        if (holding.getQuantity() == 0)
            p.removeHolding(stockCode);

        portfolioRepository.save(p);
    }

    public void updateAfterTrade(String userId) {
        Portfolio p = getPortfolio(userId);
        if (p == null) throw new IllegalStateException("포트폴리오 없음");

        double total = p.getCashBalance();

        for (Holding h : p.getHoldings().values()) {
            total += h.getAvgPrice() * h.getQuantity();
        }

        p.setTotalAssets(total);
        portfolioRepository.save(p);
    }

    public double calculateProfit(String userId, String stockCode, double currentPrice) {
        Portfolio p = getPortfolio(userId);
        if (p == null) return 0;

        Holding h = p.getHolding(stockCode);
        if (h == null) return 0;

        return (currentPrice - h.getAvgPrice()) * h.getQuantity();
    }

    public double getTotalProfit(String userId, double totalCurrentValue) {
        Portfolio p = getPortfolio(userId);
        if (p == null) return 0;

        double profit = totalCurrentValue - p.getTotalAssets();
        p.setTotalProfit(profit);

        portfolioRepository.save(p);
        return profit;
    }
}
