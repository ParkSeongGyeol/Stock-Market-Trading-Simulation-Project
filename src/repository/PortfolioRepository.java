package repository;

import java.util.HashMap;
import java.util.Map;
import model.Portfolio;

public class PortfolioRepository {
    private Map<String, Portfolio> store = new HashMap<>();

    public Portfolio createPortfolio(String userId, double cash) {
        if (store.containsKey(userId)) return store.get(userId);
        Portfolio portfolio = new Portfolio(userId, cash);
        store.put(userId, portfolio);
        return portfolio;
    }

    public Portfolio getPortfolio(String userId) {
        return store.get(userId);
    }

    public void save(Portfolio portfolio) {
        store.put(portfolio.getUserId(), portfolio);
    }

    public void clear() {
        store.clear();
    }
}
