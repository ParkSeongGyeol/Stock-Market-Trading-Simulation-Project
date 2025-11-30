package view;

import java.awt.CardLayout;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import model.Stock;
import model.User;
import repository.OrderRepository;
import repository.PortfolioRepository;
import repository.StockRepository;
import repository.UserRepository;
import service.AuthService;
import service.OrderService;
import service.PortfolioService;
import service.StockService;
import service.UserService;

public class MainFrame extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainPanel;

    private LoginView loginView;
    private StockListView stockListView;
    private TradeView tradeView;
    private PortfolioView portfolioView;
    private TransactionHistoryView historyView;

    // Services
    private UserService userService;
    private StockService stockService;
    private OrderService orderService;
    private PortfolioService portfolioService;
    private AuthService authService;
    private util.PriceUpdateThread priceUpdateThread;

    public MainFrame() {
        setTitle("주식 거래 시뮬레이션");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 600);
        setLocationRelativeTo(null);

        initServices();
        initViews();

        // 애플리케이션 종료 시 스레드 중지
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                if (priceUpdateThread != null) {
                    priceUpdateThread.stopRunning();
                }
            }
        });

        setVisible(true);
    }

    private void initServices() {
        // Repository 초기화
        UserRepository userRepo = new UserRepository();
        StockRepository stockRepo = new StockRepository();
        OrderRepository orderRepo = new OrderRepository();
        PortfolioRepository portfolioRepo = new PortfolioRepository();

        // Service 초기화
        authService = new AuthService();
        userService = new UserService(userRepo, authService);
        stockService = new StockService(stockRepo);
        portfolioService = new PortfolioService(portfolioRepo);
        orderService = new OrderService(orderRepo, stockService, portfolioService, authService);
        
        // Market Data 초기화 및 스레드 시작
        service.PriceService priceService = new service.PriceService();
        priceUpdateThread = new util.PriceUpdateThread(stockRepo, priceService);
        
        // 알림 리스너 등록
        priceUpdateThread.setPriceAlertListener(msg -> {
            SwingUtilities.invokeLater(() -> {
                JOptionPane.showMessageDialog(this, msg, "시장 경보", JOptionPane.WARNING_MESSAGE);
            });
        });
        
        priceUpdateThread.start();

        // 테스트 데이터 생성
        initTestData(userRepo, portfolioRepo);
    }

    private void initTestData(UserRepository userRepo, PortfolioRepository portfolioRepo) {
        // 테스트용 사용자 생성
        if (userRepo.findUser("testUser") == null) {
            User user = new User("testUser", "1234", "테스터", null);
            user.setBalance(10000000);
            userRepo.addUser(user);
            portfolioRepo.createPortfolio(user.getUserId(), user.getBalance());
        }
    }

    private void initViews() {
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        loginView = new LoginView(this);
        stockListView = new StockListView(this);
        tradeView = new TradeView(this, orderService);
        portfolioView = new PortfolioView(this);
        historyView = new TransactionHistoryView(this, orderService);

        mainPanel.add(loginView, "Login");
        mainPanel.add(stockListView, "StockList");
        mainPanel.add(tradeView, "Trade");
        mainPanel.add(portfolioView, "Portfolio");
        mainPanel.add(historyView, "History");

        add(mainPanel);
        
        showLogin();
    }

    public void showLogin() {
        cardLayout.show(mainPanel, "Login");
        setTitle("로그인");
    }

    public void showStockList() {
        // 종목 리스트 갱신
        List<Stock> stocks = stockService.searchStocks("");
        stockListView.setStocks(stocks);
        
        cardLayout.show(mainPanel, "StockList");
        setTitle("종목 목록");
    }

    public void showTrade(Stock stock) {
        tradeView.setStock(stock);
        cardLayout.show(mainPanel, "Trade");
        setTitle("매수/매도 - " + stock.getStockName());
    }
    
    public void showPortfolio() {
        User user = authService.getCurrentUser();
        if (user == null) {
            JOptionPane.showMessageDialog(this, "로그인이 필요합니다.");
            showLogin();
            return;
        }
        
        // 데이터 준비
        model.Portfolio portfolio = portfolioService.getPortfolio(user.getUserId());
        List<Stock> stocks = stockService.searchStocks("");
        
        // 종목명 및 현재가 맵핑
        java.util.Map<String, String> stockNames = new java.util.HashMap<>();
        java.util.Map<String, Integer> currentPrices = new java.util.HashMap<>();
        
        for (Stock s : stocks) {
            stockNames.put(s.getStockCode(), s.getStockName());
            currentPrices.put(s.getStockCode(), s.getCurrentPrice());
        }
        
        portfolioView.updatePortfolio(portfolio, stockNames, currentPrices);
        
        cardLayout.show(mainPanel, "Portfolio");
        setTitle("나의 포트폴리오");
    }
    
    public void showHistory() {
        User user = authService.getCurrentUser();
        if (user == null) {
            JOptionPane.showMessageDialog(this, "로그인이 필요합니다.");
            showLogin();
            return;
        }
        
        historyView.updateHistory(user.getUserId());
        cardLayout.show(mainPanel, "History");
        setTitle("거래 내역");
    }

    public void handleLogin(String id, String pw) {
        if (userService.login(id, pw)) {
            JOptionPane.showMessageDialog(this, id + "님 환영합니다!");
            showStockList();
        } else {
            JOptionPane.showMessageDialog(this, "로그인 실패: 아이디 또는 비밀번호를 확인하세요.");
        }
    }
}
