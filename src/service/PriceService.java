package service;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class PriceService {

    // --- 1. 데이터 모델 정의 ---
    /** 주식 종목 정보를 담는 내부 모델 */
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
        
        // 현재가 getter
        public double getCurrentPrice() {
            return currentPrice;
        }

        // 현재가 setter (업데이트 시 사용)
        public void setCurrentPrice(double currentPrice) {
            this.currentPrice = currentPrice;
        }

   
        public String toCsvString() {
            // 종목코드, 종목명, 현재가(정수), 이전종가(정수) 형식으로 저장
            return String.format("%s,%s,%.0f,%.0f", 
                code, name, currentPrice, previousClosePrice);
        }
    }

    // --- 2. 가격 업데이트 로직 ---
    private static final double MAX_CHANGE_PERCENT = 0.05; // 최대 변동률 ±5%
    private final Random random = new Random();

    /**
     * 현재 가격을 ±5% 범위 내에서 무작위로 업데이트합니다.
     */
    public double updatePrice(double currentPrice) {
        if (currentPrice <= 0) {
            return 0.0; // 0원 이하일 경우 업데이트 방지
        }

        // [-0.05, 0.05) 범위의 무작위 변동률 생성
        double changePercentage = (random.nextDouble() * (2 * MAX_CHANGE_PERCENT)) - MAX_CHANGE_PERCENT;
        
        // 새 가격 계산 및 소수점 둘째 자리까지 반올림
        double newPrice = currentPrice * (1 + changePercentage);
        return Math.round(newPrice * 100.0) / 100.0;
    }

  
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

   
   
    public void saveStocks(String filePath, List<Stock> stocks) {
       
        try (PrintWriter pw = new PrintWriter(new FileWriter(filePath, false))) {
            for (Stock stock : stocks) {
                pw.println(stock.toCsvString());
            }
            System.out.println("\n✅ 주식 데이터가 파일에 성공적으로 저장되었습니다: " + filePath);
        } catch (IOException e) {
            System.err.println("파일 저장 중 오류 발생: " + e.getMessage());
        }
    }


    public static void main(String[] args) {
        final String FILE_PATH = "stock_data.txt";

        PriceService service = new PriceService();
        List<Stock> stockList = service.loadStocks(FILE_PATH); 

        if (stockList.isEmpty()) {
            System.out.println("로드된 주식 데이터
