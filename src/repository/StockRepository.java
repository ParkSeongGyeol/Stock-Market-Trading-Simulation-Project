package stockgame;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class StockRepository {

    
    private static final String FILE_PATH = "stock_data.txt";
    private static final int MIN_STOCKS = 10;
    
     
    private List<Stock> stocks = new ArrayList<>(); 

  
    public static class Stock {
        private String code;
        private String name;
        private int currentPrice;
        private int openingPrice; 

        public Stock(String code, String name, int currentPrice, int openingPrice) {
            this.code = code;
            this.name = name;
            this.currentPrice = currentPrice;
            this.openingPrice = openingPrice;
        }

        // Getter methods
        public String getCode() { return code; }
        public String getName() { return name; }
        public int getCurrentPrice() { return currentPrice; }
        public int getOpeningPrice() { return openingPrice; }
        
       
        public void setCurrentPrice(int currentPrice) { 
            this.currentPrice = currentPrice; 
        }

        @Override
        public String toString() {
            return "종목코드: " + code + ", 종목명: " + name + 
                   ", 현재가: " + currentPrice + ", 시가: " + openingPrice;
        }
    }
    
 
    public StockRepository() {
        this.stocks = loadStocks(FILE_PATH);
        if (this.stocks.size() < MIN_STOCKS) {
            System.err.println("경고: 로드된 종목이 " + MIN_STOCKS + "개 미만입니다.");
        }
        System.out.println("종목 초기화 성공. 최종 종목 수: " + this.stocks.size() + "개");
    }

 
    public List<Stock> getAllStocks() {
        return this.stocks;
    }

   
    private List<Stock> loadStocks(String filePath) {
        List<Stock> loadedStocks = new ArrayList<>();
        System.out.println("--- 파일(" + filePath + ")에서 종목 데이터 로드 시도 중 ---");
        
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
                   
                    int currentPrice = (int) Math.round(Double.parseDouble(parts[2].trim())); 
                    int openingPrice = (int) Math.round(Double.parseDouble(parts[3].trim()));

                    loadedStocks.add(new Stock(code, name, currentPrice, openingPrice));
                } catch (NumberFormatException e) {
                    System.err.println("경고: 가격 변환 오류 발생 - " + line);
                }
            }
            System.out.println("파일 로드 완료. 로드된 종목 수: " + loadedStocks.size() + "개");
        } catch (IOException e) {
            System.err.println("파일 로드 중 오류 발생: " + e.getMessage());
        }
        return loadedStocks;
    }
}
