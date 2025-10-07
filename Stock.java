package stockgame;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Stock {
    
    // --- 1. 필드 및 상수 ---
    private String stockCode;
    private String stockName;
    private int currentPrice;
    private int previousPrice;
    
    private static final String FILE_NAME = "stock_data.txt";
    private static final Scanner scanner = new Scanner(System.in); 

    // --- 2. 생성자 및 Getter/Setter (로직 동일) ---
    public Stock() { }
    public Stock(String stockCode, String stockName, int currentPrice, int previousPrice) {
        this.stockCode = stockCode;
        this.stockName = stockName;
        this.currentPrice = currentPrice;
        this.previousPrice = previousPrice;
    }
    public String getStockCode() { return stockCode; }
    public void setStockCode(String stockCode) { this.stockCode = stockCode; }
    public String getStockName() { return stockName; }
    public void setStockName(String stockName) { this.stockName = stockName; }
    public int getCurrentPrice() { return currentPrice; }
    public void setCurrentPrice(int newPrice) {
        this.previousPrice = this.currentPrice;
        this.currentPrice = newPrice;
    }
    public int getPreviousPrice() { return previousPrice; }
    @Override
    public String toString() {
        return String.format(
            "| 코드: %s | 종목명: %s | 현재가: %d원 | 전일가: %d원 |", 
            stockCode, stockName, currentPrice, previousPrice
        );
    }
    
    // --- 3. 데이터 관리 메서드 (저장/불러오기) ---
    public static void saveAllStocks(List<Stock> stocks) {
        try (PrintWriter writer = new PrintWriter(FILE_NAME)) {
            for (Stock stock : stocks) {
                writer.printf("%s,%s,%d,%d\n", 
                              stock.stockCode, stock.stockName, 
                              stock.currentPrice, stock.previousPrice);
            }
            System.out.println("✅ 총 " + stocks.size() + "개 종목 정보가 " + FILE_NAME + "에 저장되었습니다.");
        } catch (FileNotFoundException e) {
            System.err.println("❌ 파일 저장 중 오류: " + e.getMessage());
        }
    }

    public static List<Stock> loadAllStocks() {
        List<Stock> stocks = new ArrayList<>();
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            return stocks;
        }
        
        try (Scanner fileScanner = new Scanner(file)) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] data = line.split(",");
                if (data.length == 4) {
                    try {
                        stocks.add(new Stock(data[0], data[1], Integer.parseInt(data[2]), Integer.parseInt(data[3])));
                    } catch (NumberFormatException e) {
                         System.err.println("❌ 파일 데이터 오류: 가격 형식이 잘못되었습니다. 라인: " + line);
                    }
                }
            }
            System.out.println("📂 파일에서 총 " + stocks.size() + "개 종목 정보를 불러왔습니다.");
        } catch (FileNotFoundException e) {
            System.err.println("❌ 파일을 불러오는 중 오류: " + e.getMessage());
        }
        return stocks;
    }
    
    // --- 4. 유틸리티 메서드 ---
    private static Stock findStockByCode(String code, List<Stock> stocks) {
        for (Stock stock : stocks) {
            if (stock.getStockCode().equals(code)) {
                return stock;
            }
        }
        return null;
    }

    private static void displayStocks(List<Stock> stocks) {
        if (stocks.isEmpty()) {
            System.out.println("등록된 주식 정보가 없습니다.");
            return;
        }
        System.out.println("\n=== 등록된 주식 정보 목록 (" + stocks.size() + "개) ===");
        for (Stock stock : stocks) {
            System.out.println(String.format("   %s", stock.toString()));
        }
        System.out.println("=========================================");
    }

    // --- 5. Main 메서드 (실행 로직) ---
    public static void main(String[] args) {
        
        List<Stock> stockList = loadAllStocks();
        
        while (true) {
            System.out.println("\n[메뉴]");
            System.out.println("1. 주식 정보 등록");
            System.out.println("2. 등록된 주식 목록 보기");
            System.out.println("3. 현재가 업데이트");
            System.out.println("4. 주식 정보 삭제");
            System.out.println("5. 저장하고 종료");
            System.out.print("선택: ");
            
            if (!scanner.hasNextInt()) {
                System.out.println("❌ 유효하지 않은 입력입니다. 숫자를 입력하세요.");
                scanner.nextLine();
                continue;
            }
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1: // 등록
                    System.out.println("\n[새 주식 정보 등록]");
                    String code;
                    
                    // ★★★ 종목 코드 숫자 유효성 검사 루프 ★★★
                    while (true) {
                        System.out.print("종목 코드를 입력하세요 (숫자만 허용): ");
                        code = scanner.nextLine().trim();
                        
                        if (code.matches("\\d+")) { // "\\d+"는 하나 이상의 숫자를 의미
                            break; // 숫자로만 구성되어 있으면 루프 탈출
                        } else {
                            System.out.println("⛔ 경고: 종목 코드는 숫자만 입력해야 합니다. 다시 입력해 주세요.");
                        }
                    }
                    // ★★★ 루프 끝 ★★★
                    
                    if (findStockByCode(code, stockList) != null) {
                        System.out.println("⛔ 경고: 종목코드 " + code + "는 이미 등록되어 있습니다. 등록을 취소합니다.");
                        break;
                    }
                    
                    System.out.print("종목 이름을 입력하세요: ");
                    String name = scanner.nextLine().trim();
                    
                    System.out.print("현재 가격을 입력하세요: ");
                    int initialPrice = 0;
                    if (scanner.hasNextInt()) {
                        initialPrice = scanner.nextInt();
                    }
                    scanner.nextLine();
                    
                    Stock newStock = new Stock(code, name, initialPrice, 0);
                    stockList.add(newStock);
                    System.out.println("✔ " + newStock.getStockName() + " 종목이 목록에 추가되었습니다.");
                    break;
                    
                case 2: // 목록 보기
                    displayStocks(stockList);
                    break;
                    
                case 3: // 현재가 업데이트 (종목 코드 기반)
                    displayStocks(stockList);
                    if (stockList.isEmpty()) break;
                    
                    System.out.print("업데이트할 종목 코드를 입력하세요: ");
                    String targetCode = scanner.nextLine().trim();
                    
                    Stock targetStock = findStockByCode(targetCode, stockList); 

                    if (targetStock != null) {
                        System.out.println("선택된 종목: " + targetStock.getStockName());
                        System.out.print("새로운 현재 가격을 입력하세요: ");
                        if (scanner.hasNextInt()) {
                            int newPrice = scanner.nextInt();
                            scanner.nextLine();
                            targetStock.setCurrentPrice(newPrice);
                            System.out.println("✔ " + targetStock.getStockName() + " 가격 업데이트 완료!");
                            System.out.println("업데이트 후: " + targetStock.toString());
                        } else {
                            System.out.println("❌ 유효한 숫자를 입력해야 합니다.");
                            scanner.nextLine();
                        }
                    } else {
                        System.out.println("❌ 입력하신 종목 코드(" + targetCode + ")를 찾을 수 없습니다.");
                    }
                    break;
                    
                case 4: // 주식 정보 삭제
                    System.out.println("\n[주식 정보 삭제]");
                    displayStocks(stockList);
                    if (stockList.isEmpty()) break;
                    
                    System.out.print("삭제할 종목 코드를 입력하세요: ");
                    String deleteCode = scanner.nextLine().trim();
                    
                    Stock stockToDelete = findStockByCode(deleteCode, stockList);
                    
                    if (stockToDelete != null) {
                        stockList.remove(stockToDelete);
                        System.out.println("🗑️ " + stockToDelete.getStockName() + " (" + deleteCode + ") 종목이 목록에서 삭제되었습니다.");
                    } else {
                        System.out.println("❌ 입력하신 종목 코드(" + deleteCode + ")를 찾을 수 없습니다.");
                    }
                    break;
                    
                case 5: // 저장하고 종료
                    saveAllStocks(stockList);
                    System.out.println("\n프로그램을 종료합니다.");
                    return;
                    
                default:
                    System.out.println("❌ 잘못된 메뉴 선택입니다. 다시 입력해 주세요.");
            }
        }
    }
}