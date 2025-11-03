package view;

import java.util.ArrayList;

import team.BorderLayout;
import team.DefaultTableModel;
import team.Font;
import team.JButton;
import team.JLabel;
import team.JPanel;
import team.JScrollPane;
import team.JTable;
import team.MainFrame;
import team.Stock;

public class StockListView {
	class StockListView extends JPanel {
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
	        JButton btnLogout = new JButton("로그아웃");
	        JPanel btnPanel = new JPanel();
	        btnPanel.add(btnBuy);
	        btnPanel.add(btnLogout);
	        add(btnPanel, BorderLayout.SOUTH);

	        // 더미 데이터
	        stocks = new ArrayList<>();
	        stocks.add(new Stock("005930", "삼성전자", 72000));
	        stocks.add(new Stock("000660", "SK하이닉스", 145000));
	        stocks.add(new Stock("035720", "카카오", 56000));
	        stocks.add(new Stock("051910", "LG화학", 440000));

	        refreshTable();

	        btnBuy.addActionListener(e -> {
	            int row = table.getSelectedRow();
	            if (row == -1) {
	                JOptionPane.showMessageDialog(this, "종목을 선택하세요");
	                return;
	            }
	            mainFrame.showTrade(stocks.get(row));
	        });

	        btnLogout.addActionListener(e -> mainFrame.showLogin());
	    }

	    private void refreshTable() {
	        model.setRowCount(0);
	        for (Stock s : stocks)
	            model.addRow(new Object[]{s.getStockCode(), s.getStockName(), s.getCurrentPrice()});
	    }
	}


}
