package stockgame;

import stockgame.StockRepository.Stock; 
import java.util.List;
import java.util.Scanner; 
import java.util.stream.Collectors;

public class StockService {

    private final StockRepository repository;

    public StockService(StockRepository repository) {
        this.repository = repository;
    }

    public Stock getStockByCode(String code) {
        List<Stock> allStocks = repository.getAllStocks();
        if (allStocks.isEmpty()) {
            return null;
        }

        return allStocks.stream()
                .filter(stock -> stock.getCode().equals(code))
                .findFirst()
                .orElse(null);
    }

    public int getStockPrice(String code) {
        Stock stock = getStockByCode(code);
        return (stock != null) ? stock.getCurrentPrice() : -1;
    }

    public List<Stock> searchStocks(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            // 키워드가 없으면 전체 리스트 반환
            return repository.getAllStocks();
        }

        String lowerCaseKeyword = keyword.trim().toLowerCase();

        return repository.getAllStocks().stream()
                .filter(stock -> 
                    stock.getName().toLowerCase().contains(lowerCaseKeyword) || // 종목명 부분 일치
                    stock.getCode().contains(lowerCaseKeyword)                  // 종목 코드 부분 일치
                )
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        StockRepository repository = new StockRepository();
        StockService service = new StockService(repository);
        Scanner scanner = new Scanner(System.in);

        // 로드된 종목이 없으면 안내 후 종료
        if (repository.getAllStocks().isEmpty()) {
            System.out.println("\n[안내] 주식 초기화 조건(최소 10개 종목)이 충족되지 않아 검색을 진행할 수 없습니다.");
            return;
        }

        System.out.println("\n================ 주식 종목 검색 서비스 ==================");
        System.out.println("종목명 또는 종목 코드를 입력하여 검색할 수 있습니다. (예: 삼성, 221133)");
        System.out.println("검색 없이 전체 목록을 보려면 Enter를 누르세요.");
        System.out.println("종료하려면 '종료'를 입력하세요.");
        System.out.println("---------------------------------------------------------");

        while (true) {
            System.out.print("\n검색 키워드를 입력하세요: ");
            String keyword = scanner.nextLine().trim();

            if (keyword.equalsIgnoreCase("종료")) {
                System.out.println("검색 서비스를 종료합니다.");
                break;
            }

            // 검색 실행
            List<Stock> searchResults = service.searchStocks(keyword);

            System.out.printf("\n🔍 검색 결과 (%s, 총 %d개):\n", keyword.isEmpty() ? "전체" : keyword, searchResults.size());

            if (searchResults.isEmpty()) {
                System.out.println("   --> 검색된 종목이 없습니다.");
            } else {
                for (int i = 0; i < searchResults.size(); i++) {
                    Stock stock = searchResults.get(i);
                    System.out.printf("   %d. %s\n", (i + 1), stock.toString());
                }
            }
            System.out.println("---------------------------------------------------------");
        }
        scanner.close();
    }
}
