package view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import model.Holding;
import model.Portfolio;

public class PortfolioView extends JPanel {
    private MainFrame mainFrame;
    private JLabel lblTotalAsset;
    private JLabel lblCash;
    private JLabel lblTotalProfit;
    private JTable table;
    private DefaultTableModel model;

    public PortfolioView(MainFrame frame) {
        this.mainFrame = frame;
        setLayout(new BorderLayout());

        // 상단: 제목 및 요약 정보
        JPanel topPanel = new JPanel(new BorderLayout());
        JLabel title = new JLabel("💰 나의 포트폴리오", SwingConstants.CENTER);
        title.setFont(new Font("Dialog", Font.BOLD, 20));
        topPanel.add(title, BorderLayout.NORTH);

        JPanel summaryPanel = new JPanel(new GridLayout(3, 1));
        lblTotalAsset = new JLabel("총 자산: 0원");
        lblCash = new JLabel("보유 현금: 0원");
        lblTotalProfit = new JLabel("총 평가 손익: 0원");
        
        // 스타일링
        Font font = new Font("Dialog", Font.PLAIN, 14);
        lblTotalAsset.setFont(font);
        lblCash.setFont(font);
        lblTotalProfit.setFont(font);
        
        summaryPanel.add(lblTotalAsset);
        summaryPanel.add(lblCash);
        summaryPanel.add(lblTotalProfit);
        
        // 여백 추가
        JPanel paddedSummary = new JPanel(new BorderLayout());
        paddedSummary.add(summaryPanel, BorderLayout.CENTER);
        // Padding logic omitted for simplicity, using basic layout
        
        topPanel.add(paddedSummary, BorderLayout.CENTER);
        add(topPanel, BorderLayout.NORTH);

        // 중앙: 보유 주식 테이블
        model = new DefaultTableModel(new Object[]{"종목명", "보유수량", "평균단가", "현재가", "평가손익"}, 0);
        table = new JTable(model);
        JScrollPane sp = new JScrollPane(table);
        add(sp, BorderLayout.CENTER);

        // 하단: 뒤로가기 버튼
        JButton btnBack = new JButton("뒤로가기");
        add(btnBack, BorderLayout.SOUTH);

        btnBack.addActionListener(e -> mainFrame.showStockList());
    }

    public void updatePortfolio(Portfolio portfolio, Map<String, String> stockNames, Map<String, Integer> currentPrices) {
        if (portfolio == null) return;

        // 1. 요약 정보 업데이트
        double cash = portfolio.getCashBalance();
        double totalAsset = cash;
        double totalInvested = 0;

        // 테이블 데이터 준비
        model.setRowCount(0);
        
        for (Holding h : portfolio.getHoldings().values()) {
            String name = stockNames.getOrDefault(h.getStockCode(), h.getStockCode());
            int currentPrice = currentPrices.getOrDefault(h.getStockCode(), 0);
            
            double valuation = (double) currentPrice * h.getQuantity();
            double invested = h.getAvgPrice() * h.getQuantity();
            double profit = valuation - invested;
            
            totalAsset += valuation;
            totalInvested += invested;

            model.addRow(new Object[]{
                name,
                h.getQuantity(),
                String.format("%,.0f", h.getAvgPrice()),
                String.format("%,d", currentPrice),
                String.format("%,.0f", profit)
            });
        }

        // 총 평가 손익은 (주식 평가액 - 주식 매수금액) 합계로 표시하는 것이 직관적
        double stocksProfit = totalAsset - cash - totalInvested;
        
        lblTotalAsset.setText(String.format("총 자산: %,.0f 원", totalAsset));
        lblCash.setText(String.format("보유 현금: %,.0f 원", cash));
        lblTotalProfit.setText(String.format("주식 평가 손익: %,.0f 원", stocksProfit));
    }
}
