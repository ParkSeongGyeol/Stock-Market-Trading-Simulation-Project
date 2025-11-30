package view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import model.Stock;

public class StockListView extends JPanel {
    private MainFrame mainFrame;
    private JTable table;
    private DefaultTableModel model;
    private java.util.List<Stock> stocks;

    public StockListView(MainFrame frame) {
        this.mainFrame = frame;
        setLayout(new BorderLayout());

        JLabel lbl = new JLabel("📈 종목 리스트", SwingConstants.CENTER);
        lbl.setFont(new Font("Dialog", Font.BOLD, 20));
        add(lbl, BorderLayout.NORTH);

        model = new DefaultTableModel(new Object[]{"코드", "이름", "현재가"}, 0);
        table = new JTable(model);
        JScrollPane sp = new JScrollPane(table);
        add(sp, BorderLayout.CENTER);

        JButton btnBuy = new JButton("매수/매도");
        JButton btnPortfolio = new JButton("내 포트폴리오");
        JButton btnHistory = new JButton("거래 내역");
        JButton btnLogout = new JButton("로그아웃");
        JPanel btnPanel = new JPanel();
        btnPanel.add(btnBuy);
        btnPanel.add(btnPortfolio);
        btnPanel.add(btnHistory);
        btnPanel.add(btnLogout);
        add(btnPanel, BorderLayout.SOUTH);

        // 초기화 시점에는 빈 리스트, 나중에 setStocks 등으로 업데이트 가능
        // 여기서는 MainFrame에서 주입받거나 Service를 통해 가져와야 함
        // 일단 더미 데이터 유지하되 model.Stock 사용
        stocks = new ArrayList<>();
        // stocks.add(new Stock("005930", "삼성전자", 72000)); // Stock 생성자 확인 필요
        
        // MainFrame이 데이터를 제공하도록 변경하는 것이 좋음
        // 우선은 빈 상태로 두고 refreshTable 호출
        refreshTable();

        btnBuy.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "종목을 선택하세요");
                return;
            }
            mainFrame.showTrade(stocks.get(row));
        });
        
        btnPortfolio.addActionListener(e -> mainFrame.showPortfolio());
        
        btnHistory.addActionListener(e -> mainFrame.showHistory());

        btnLogout.addActionListener(e -> mainFrame.showLogin());
    }

    public void setStocks(java.util.List<Stock> stocks) {
        this.stocks = stocks;
        refreshTable();
    }

    private void refreshTable() {
        model.setRowCount(0);
        if (stocks != null) {
            for (Stock s : stocks)
                model.addRow(new Object[]{s.getStockCode(), s.getStockName(), s.getCurrentPrice()});
        }
    }
}
