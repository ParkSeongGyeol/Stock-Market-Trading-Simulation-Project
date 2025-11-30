package repository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import model.Stock;

public class StockRepository {

    private static final String FILE_PATH = "stock_data.txt";
    private static final int MIN_STOCKS = 10;
    
    private List<Stock> stocks = new ArrayList<>(); 

    public StockRepository() {
        this.stocks = loadStocks(FILE_PATH);
        if (this.stocks.size() < MIN_STOCKS) {
            System.err.println("경고: 로드된 종목이 " + MIN_STOCKS + "개 미만입니다. 더미 데이터를 생성합니다.");
            generateDummyStocks();
        }
        System.out.println("종목 초기화 성공. 최종 종목 수: " + this.stocks.size() + "개");
    }

    private void generateDummyStocks() {
        stocks.add(new Stock("005930", "삼성전자", 70000, 69000));
        stocks.add(new Stock("000660", "SK하이닉스", 120000, 118000));
        stocks.add(new Stock("035420", "NAVER", 200000, 198000));
        stocks.add(new Stock("035720", "카카오", 50000, 49000));
        stocks.add(new Stock("005380", "현대차", 180000, 179000));
        stocks.add(new Stock("000270", "기아", 80000, 79500));
        stocks.add(new Stock("005490", "POSCO홀딩스", 500000, 495000));
        stocks.add(new Stock("051910", "LG화학", 600000, 590000));
        stocks.add(new Stock("006400", "삼성SDI", 650000, 645000));
        stocks.add(new Stock("068270", "셀트리온", 150000, 149000));
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

                    // model.Stock constructor: code, name, currentPrice, previousPrice
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
