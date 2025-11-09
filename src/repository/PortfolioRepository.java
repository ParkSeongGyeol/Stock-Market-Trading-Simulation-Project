package repository;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import model.Portfolio;

public class PortfolioRepository {

    private final Map<String, Portfolio> portfolioStore = new HashMap<>();

    
    public Portfolio createPortfolio(String userId, double initialCash) {
        if (userId == null || userId.isBlank()) {
            throw new IllegalArgumentException("userId는 비어 있을 수 없습니다.");
        }
        Portfolio existing = portfolioStore.get(userId);
        if (existing != null) {
            return existing;
        }
        Portfolio portfolio = new Portfolio(userId, initialCash);
        portfolioStore.put(userId, portfolio);
        return portfolio;
    }

    
    public Portfolio getPortfolio(String userId) {
        return portfolioStore.get(userId);
    }

    
    public void save(Portfolio portfolio) {
        if (portfolio == null || portfolio.getUserId() == null || portfolio.getUserId().isBlank()) {
            throw new IllegalArgumentException("올바르지 않은 포트폴리오입니다.");
        }
        portfolioStore.put(portfolio.getUserId(), portfolio);
    }

    
    public Map<String, Portfolio> findAll() {
        return Collections.unmodifiableMap(portfolioStore);
    }

    
    public void clear() {
        portfolioStore.clear();
    }
}
