package service;

import java.util.Random;

public class PriceService {

    private static final double MAX_CHANGE_PERCENT = 0.05; // ±5%
    private final Random random = new Random();

    /**
     * 현재 가격을 -5% ~ +5% 범위 내에서 무작위로 업데이트합니다. */
    public double updatePrice(double currentPrice) {
        if (currentPrice <= 0) {
            return 0.0;
        }

        double changePercentage = (random.nextDouble() * (2 * MAX_CHANGE_PERCENT)) - MAX_CHANGE_PERCENT;
        
        // 새 가격 계산 및 소수점 둘째 자리까지 반올림
        double newPrice = currentPrice * (1 + changePercentage);
        return Math.round(newPrice * 100.0) / 100.0;
    }
}
