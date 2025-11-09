package service;

import model.Portfolio;
import repository.PortfolioRepository;


public class PortfolioService {

    private final PortfolioRepository portfolioRepository;

    public PortfolioService(PortfolioRepository portfolioRepository) {
        if (portfolioRepository == null) {
            throw new IllegalArgumentException("portfolioRepository는 null일 수 없습니다.");
        }
        this.portfolioRepository = portfolioRepository;
    }


    public Portfolio getOrCreate(String userId, double initialCash) {
        Portfolio p = portfolioRepository.getPortfolio(userId);
        if (p == null) {
            p = portfolioRepository.createPortfolio(userId, initialCash);
        }
        return p;
    }


    public double getCashBalance(String userId) {
        Portfolio p = mustGet(userId);
        return p.getCashBalance();
    }


    public boolean hasEnoughCash(String userId, double required) {
        if (required < 0) throw new IllegalArgumentException("required는 양수여야 합니다.");
        Portfolio p = mustGet(userId);
        return p.getCashBalance() >= required;
    }



    public Portfolio updateCashBalance(String userId, double delta) {
        Portfolio p = mustGet(userId);

        if (delta < 0) {
            double need = Math.abs(delta);
            if (p.getCashBalance() < need) {
                throw new IllegalStateException(
                    String.format("잔액 부족: 필요금액=%.2f, 보유잔액=%.2f", need, p.getCashBalance())
                );
            }
        }

        double next = p.getCashBalance() + delta;
        p.setCashBalance(next);
        portfolioRepository.save(p);
        return p;
    }


    private Portfolio mustGet(String userId) {
        if (userId == null || userId.isBlank()) {
            throw new IllegalArgumentException("userId는 비어 있을 수 없습니다.");
        }
        Portfolio p = portfolioRepository.getPortfolio(userId);
        if (p == null) {
            throw new IllegalStateException("포트폴리오가 존재하지 않습니다. 먼저 getOrCreate()를 호출하세요. userId=" + userId);
        }
        return p;
    }
}
