package model;

public class Portfolio {
	
	private String userId;
	private double cashBalance;
	private double totalAssets;
	private double totalProfit;
	
	public Portfolio(String userId, double cashBalance) {
		
		this.setUserId(userId);
		this.setCashBalance(cashBalance);
		this.setTotalAssets(cashBalance);
		this.setTotalProfit(0.0);
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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
}