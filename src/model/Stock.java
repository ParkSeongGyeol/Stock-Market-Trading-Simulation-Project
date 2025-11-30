package model;

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
    // Repository로 이관됨.
}
