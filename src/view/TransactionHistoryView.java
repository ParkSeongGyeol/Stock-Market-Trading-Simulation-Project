package view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import model.Transaction;
import service.OrderService;

public class TransactionHistoryView extends JPanel {
    private MainFrame mainFrame;
    private OrderService orderService;
    private JTable table;
    private DefaultTableModel model;

    public TransactionHistoryView(MainFrame frame, OrderService orderService) {
        this.mainFrame = frame;
        this.orderService = orderService;
        setLayout(new BorderLayout());

        // 상단: 제목
        JLabel title = new JLabel("📜 거래 내역", SwingConstants.CENTER);
        title.setFont(new Font("Dialog", Font.BOLD, 20));
        add(title, BorderLayout.NORTH);

        // 중앙: 거래 내역 테이블
        // 컬럼: 주문번호, 날짜, 유형, 종목코드, 수량, 가격
        String[] columns = {"주문번호", "날짜", "유형", "종목코드", "수량", "가격"};
        model = new DefaultTableModel(columns, 0);
        table = new JTable(model);
        JScrollPane sp = new JScrollPane(table);
        add(sp, BorderLayout.CENTER);

        // 하단: 뒤로가기 버튼
        JButton btnBack = new JButton("뒤로가기");
        add(btnBack, BorderLayout.SOUTH);

        btnBack.addActionListener(e -> mainFrame.showStockList());
    }

    public void updateHistory(String userId) {
        model.setRowCount(0); // 기존 데이터 초기화
        
        List<Transaction> transactions = orderService.getOrderHistory();
        
        for (Transaction tx : transactions) {
            model.addRow(new Object[]{
                tx.getOrderId(),
                tx.getTransactionDate().format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
                tx.getTransactionType(),
                tx.getStockCode(),
                tx.getQuantity(),
                String.format("%,.0f", tx.getPrice())
            });
        }
    }
}
