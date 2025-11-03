package repository;

import java.util.HashMap;

import model.Portfolio;

public class PortfolioRepository {
	
	HashMap<String, Portfolio> PortfolioInfo = new HashMap<String, Portfolio>();
			
	public void createPortfolio() {
		PortfolioInfo.put(null, null);
	}
}
