package model;

import java.util.HashMap;
import java.util.Map;

public class Portfolio {
    private String userId;
    private double cashBalance;
    private double totalAssets;
    private double totalProfit;
    private Map<String, Holding> holdings;

    public Portfolio(String userId, double cashBalance) {
        this.userId = userId;
        this.cashBalance = cashBalance;
        this.totalAssets = cashBalance;
        this.totalProfit = 0.0;
        this.holdings = new HashMap<>();
    }

    public String getUserId() {
        return userId;
    }

    public double getCashBalance() {
        return cashBalance;
    }

    public void setCashBalance(double cashBalance) {
        this.cashBalance = cashBalance;
    }

    public double getTotalAssets() {
        return totalAssets;
    }

    public void setTotalAssets(double totalAssets) {
        this.totalAssets = totalAssets;
    }

    public double getTotalProfit() {
        return totalProfit;
    }

    public void setTotalProfit(double totalProfit) {
        this.totalProfit = totalProfit;
    }

    public Map<String, Holding> getHoldings() {
        return holdings;
    }

    public void addHolding(String stockCode, Holding holding) {
        holdings.put(stockCode, holding);
    }

    public void removeHolding(String stockCode) {
        holdings.remove(stockCode);
    }

    public Holding getHolding(String stockCode) {
        return holdings.get(stockCode);
    }
}
