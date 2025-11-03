package util;

public class PriceUpdateThread extends Thread {

    @Override
    public void run() {
        while (true) {
            try {
                // 가격 업데이트 로직 실행
                updatePrice();

                // 30초 대기
                Thread.sleep(30000); 

            } catch (InterruptedException e) {
                // 스레드 중단 요청 시 종료
                System.out.println("PriceUpdateThread가 중단되었습니다.");
                Thread.currentThread().interrupt(); 
                break;
            } catch (Exception e) {
                System.err.println("가격 업데이트 중 오류 발생: " + e.getMessage());
            }
        }
    }

    private void updatePrice() {
        
        String currentTime = java.time.LocalTime.now().toString();
        System.out.println("가격 업데이트 수행 시간: " + currentTime);
    }

    public static void main(String[] args) {
        PriceUpdateThread thread = new PriceUpdateThread();
        thread.start();
        
        try {
            // 90초 후 스레드 종료 예시
            Thread.sleep(90000); 
            thread.interrupt(); 
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
