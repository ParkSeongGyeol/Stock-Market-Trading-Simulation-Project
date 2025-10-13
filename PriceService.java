package stockgame;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class PriceService {

    // --- 1. 데이터 모델 정의 (내부 정적 클래스) ---
    /**
     * 주식 종목의 정보를 담는 모델입니다.
     */
    private static class Stock {
        private final String code;
        private final String name;
        private double currentPrice;
        private final double previousClosePrice;

        public Stock(String code, String name, double currentPrice, double previousClosePrice) {
            this.code = code;
            this.name = name;
            this.currentPrice = currentPrice;
            this.previousClosePrice = previousClosePrice;
        }
        
        // Getter for currentPrice
        public double getCurrentPrice() {
            return currentPrice;
        }

        // Setter for price update
        public void setCurrentPrice(double currentPrice) {
            this.currentPrice = currentPrice;
        }

        @Override
        public String toString() {
            return String.format("[%s] %s: 현재가=%.2f, 이전종가=%.2f",
                code, name, currentPrice, previousClosePrice);
        }
    }

    // --- 2. 가격 업데이트 로직 (요구사항) ---
    private static final double MAX_CHANGE_PERCENT = 0.05; // ±5%
    private final Random random = new Random();

    /**
     * 현재 가격을 -5% ~ +5% 범위 내에서 무작위로 업데이트합니다.
     *
     * @param currentPrice 현재 가격
     * @return 무작위 변동이 적용된 새로운 가격
     */
    public double updatePrice(double currentPrice) {
        if (currentPrice <= 0) {
            return 0.0;
        }

        // [-0.05, 0.05) 범위의 무작위 변동률을 생성합니다.
        double changePercentage = (random.nextDouble() * (2 * MAX_CHANGE_PERCENT)) - MAX_CHANGE_PERCENT;
        
        // 새 가격 계산 및 소수점 둘째 자리까지 반올림
        double newPrice = currentPrice * (1 + changePercentage);
        return Math.round(newPrice * 100.0) / 100.0;
    }

    // --- 3. 데이터 로드 로직 (파일 처리) ---
    /**
     * stock_data.txt 파일에서 주식 데이터를 로드합니다.
     *
     * @param filePath 로드할 데이터 파일의 경로
     * @return 로드된 Stock 객체의 리스트
     */
    public List<Stock> loadStocks(String filePath) {
        List<Stock> stocks = new ArrayList<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty() || line.trim().startsWith("//") || line.trim().startsWith("[")) {
                    continue;
                }
                
                String[] parts = line.split(",");
                
                if (parts.length < 4) {
                    System.err.println("경고: 유효하지 않은 데이터 줄입니다 - " + line);
                    continue;
                }
                
                try {
                    String code = parts[0].trim();
                    String name = parts[1].trim();
                    double currentPrice = Double.parseDouble(parts[2].trim());
                    double previousClosePrice = Double.parseDouble(parts[3].trim());

                    stocks.add(new Stock(code, name, currentPrice, previousClosePrice));
                } catch (NumberFormatException e) {
                    System.err.println("경고: 가격 변환 오류 발생 - " + line);
                }
            }
        } catch (IOException e) {
            System.err.println("파일 로드 중 오류 발생: " + e.getMessage());
        }
        return stocks;
    }

    public static void main(String[] args) {
        final String FILE_PATH = "stock_data.txt";

        PriceService service = new PriceService();
        List<Stock> stockList = service.loadStocks(FILE_PATH);

        if (stockList.isEmpty()) {
            System.out.println("로드된 주식 데이터가 없습니다. 파일 경로와 내용을 확인하세요.");
            return;
        }

        System.out.println("--- 주식 가격 무작위 업데이트 시작 ---");
        for (Stock stock : stockList) {
            double oldPrice = stock.getCurrentPrice();
            
            double newPrice = service.updatePrice(oldPrice);
            
           
            stock.setCurrentPrice(newPrice);
            
           
            double change = newPrice - oldPrice;
            double changePercent = (change / oldPrice) * 100;
            
            System.out.printf(
                "[%s] %s: 이전가=%.2f -> **새 현재가=%.2f** (변동률: %.2f%%)\n",
                stock.code, stock.name, oldPrice, newPrice, changePercent
            );
        }
        System.out.println("------------------------------------------");
    }
}