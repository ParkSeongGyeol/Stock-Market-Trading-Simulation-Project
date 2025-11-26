package model;

public class Holding {
    private String stockCode;
    private int quantity;
    private double avgPrice;

    public Holding(String stockCode, int quantity, double avgPrice) {
        this.stockCode = stockCode;
        this.quantity = quantity;
        this.avgPrice = avgPrice;
    }

    public String getStockCode() {
        return stockCode;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getAvgPrice() {
        return avgPrice;
    }

    public void updateAvgPrice(int buyQuantity, double buyPrice) {
        double totalCost = (this.avgPrice * this.quantity) + (buyPrice * buyQuantity);
        this.quantity += buyQuantity;
        this.avgPrice = totalCost / this.quantity;
    }

    public void reduceQuantity(int sellQuantity) {
        this.quantity -= sellQuantity;
        if (this.quantity < 0) this.quantity = 0;
    }
}
