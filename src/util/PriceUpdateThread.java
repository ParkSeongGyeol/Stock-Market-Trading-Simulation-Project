package util;

import java.util.List;
import model.Stock;
import repository.StockRepository;
import service.PriceService;

public class PriceUpdateThread extends Thread {

    private static final int UPDATE_INTERVAL_SECONDS = 5; // 5초마다 업데이트
    private final StockRepository repository;
    private final PriceService priceService;
    private volatile boolean running = true; 
    
    // ⭐ 실시간 알림 기능 추가: 5% 이상 변동 시 알림을 위한 상수
    private static final double ALERT_PERCENTAGE = 5.0; 
    
    public PriceUpdateThread(StockRepository repository, PriceService priceService) {
        this.repository = repository;
        this.priceService = priceService;
        this.setName("PriceUpdater"); 
    }
    
    public void stopRunning() {
        this.running = false;
        this.interrupt(); 
    }

    @Override
    public void run() {
        System.out.printf("⭐ [%s] 스레드가 %d초 간격으로 가격 업데이트를 시작합니다.\n", 
            getName(), UPDATE_INTERVAL_SECONDS);

        while (running) {
            try {
                updateAllStockPrices();
                Thread.sleep(UPDATE_INTERVAL_SECONDS * 1000); 

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); 
                System.out.printf("🛑 [%s] 스레드가 외부 요청으로 종료됩니다.\n", getName());
                this.running = false; 
            } catch (Exception e) {
                System.err.printf("❌ [%s] 스레드 실행 중 예상치 못한 오류 발생: %s\n", getName(), e.getMessage());
                try { Thread.sleep(5000); } catch (InterruptedException ignored) { Thread.currentThread().interrupt(); }
            }
        }
        System.out.printf("✅ [%s] 스레드 실행이 완전히 종료되었습니다.\n", getName());
    }
    
    private void updateAllStockPrices() {
        List<Stock> stockList = repository.getAllStocks(); 
        
        if (stockList.isEmpty()) {
            // System.out.println("⚠️ 업데이트할 종목이 없습니다."); // 너무 빈번하면 시끄러우므로 주석 처리
            return;
        }
        
        // System.out.println("\n--- [" + getName() + "] 주식 가격 업데이트 시작 ---"); // 로그 줄이기
        
        for (Stock stock : stockList) {
            
            double oldPriceDouble = (double) stock.getCurrentPrice();
            double newPriceDouble = priceService.updatePrice(oldPriceDouble);
            int newPriceInt = (int) Math.round(newPriceDouble); 

            double change = newPriceInt - oldPriceDouble;
            double changePercent = (change / oldPriceDouble) * 100;
            
            // ⭐ 5% 이상 변동 알림 로직
            if (oldPriceDouble > 0 && Math.abs(changePercent) >= ALERT_PERCENTAGE) {
                System.out.printf("🚨🚨 [실시간 알림] %s: 5%% 이상 급격한 변동 발생! (%.2f%%) 🚨🚨\n",
                    stock.getStockName(), changePercent
                );
            }
            
            stock.setCurrentPrice(newPriceInt); 
            
            /* 로그 너무 많아서 주석 처리
            System.out.printf("   [코드: %s] %s: 이전가=%.0f -> **현재가=%d** (변동률: %.2f%%)\n",
                stock.getStockCode(), stock.getStockName(), oldPriceDouble, newPriceInt, changePercent
            );
            */
        }
        
        // System.out.println("--- 주식 가격 업데이트 완료 ---");
        
        // 파일 저장 로직 제거 (In-Memory 방식이므로 불필요)
    }
}
