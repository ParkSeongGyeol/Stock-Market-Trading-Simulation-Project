package stockgame;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StockRepository {

    // 파일 경로 및 최소 종목 수 상수 정의
    private static final String FILE_PATH = "stock_data.txt";
    private static final int MIN_STOCKS = 10;

    // 1. 내부 클래스로 Stock 모델 정의
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

        // Getter methods (필수)
        public String getCode() { return code; }
        public String getName() { return name; }
        public int getCurrentPrice() { return currentPrice; }
        public int getOpeningPrice() { return openingPrice; }

        @Override
        public String toString() {
            return "종목코드: " + code + ", 종목명: " + name + 
                   ", 현재가: " + currentPrice + ", 시가: " + openingPrice;
        }
    }

    // ArrayList를 사용하여 종목을 저장할 필드 선언
    private List<Stock> stocks;

    // 생성자: 파일에서 종목을 읽고 10개 미만일 경우 초기화 실패
    public StockRepository() {
        this.stocks = new ArrayList<>();
        
        try {
            initializeStocksFromFile(FILE_PATH);
        } catch (IOException e) {
            System.err.println("오류: 파일을 읽는 데 실패했습니다: " + FILE_PATH);
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.err.println("오류: 파일 데이터 중 숫자 형식 변환에 실패했습니다.");
            e.printStackTrace();
        }
    }

    private void initializeStocksFromFile(String filePath) throws IOException, NumberFormatException {
        System.out.println("--- 파일(" + filePath + ")에서 종목 데이터 로드 시도 중 ---");
        
        // 임시 리스트에 데이터를 먼저 로드
        List<Stock> temporaryStocks = new ArrayList<>();
        
        // 1. 파일에서 데이터 로드
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty() || line.trim().startsWith("[")) continue; 
                
                String[] parts = line.split(",");
                
                if (parts.length < 4) {
                    System.err.println("경고: 데이터 필드가 부족합니다. 줄 건너뛰기: " + line);
                    continue;
                }
                
                // 데이터 파싱 및 임시 리스트에 추가
                String code = parts[0].trim();
                String name = parts[1].trim();
                int currentPrice = Integer.parseInt(parts[2].trim());
                int openingPrice = Integer.parseInt(parts[3].trim());
                
                temporaryStocks.add(new Stock(code, name, currentPrice, openingPrice));
            }
        }

        System.out.println("파일 로드 완료. 로드된 종목 수: " + temporaryStocks.size() + "개");
        
        // 2. 종목 수가 10개 미만인지 확인하고 초기화 여부 결정
        if (temporaryStocks.size() < MIN_STOCKS) {
            System.err.println("!! 초기화 실패: 로드된 종목(" + temporaryStocks.size() + "개)이 최소 요구 수(" + MIN_STOCKS + "개)에 미달합니다.");
            // 초기화가 실패했으므로 this.stocks는 비어있는 (기본 생성 시점의) 상태로 유지됨
        } else {
            // 10개 이상이면 this.stocks에 데이터 할당
            this.stocks = temporaryStocks;
            System.out.println("종목 초기화 성공. 최종 종목 수: " + this.stocks.size() + "개");
        }
    }

    /**
     * 저장된 모든 종목 리스트를 반환합니다.
     * @return 모든 Stock 객체를 포함하는 List
     */
    public List<Stock> getAllStocks() {
        return this.stocks;
    }

    // --- 테스트를 위한 메인 메서드 ---
    public static void main(String[] args) {
        // StockRepository 인스턴스 생성 및 파일에서 데이터 로드 시도
        StockRepository repository = new StockRepository();
        
        System.out.println("\n==================================");
        
        List<Stock> allStocks = repository.getAllStocks();

        System.out.println("--- [getAllStocks() 호출 결과] ---");
        
        // 결과 출력
        if (allStocks.isEmpty()) {
            System.out.println("초기화 조건(최소 10개 종목)이 충족되지 않아 로드된 종목이 없습니다.");
        } else {
            for (int i = 0; i < allStocks.size(); i++) {
                System.out.println((i + 1) + ". " + allStocks.get(i));
            }
        }
        
        System.out.println("==================================");
    }
}