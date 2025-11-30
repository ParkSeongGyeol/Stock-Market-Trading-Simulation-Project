package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import model.Stock;
import service.OrderService;

public class TradeView extends JPanel {
    private MainFrame mainFrame;
    private OrderService orderService;
    private Stock stock;
    private JLabel lblName, lblPrice;
    private JTextField tfQty;

    public TradeView(MainFrame frame, OrderService orderService) {
        this.mainFrame = frame;
        this.orderService = orderService;
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(8,8,8,8);

        lblName = new JLabel();
        lblPrice = new JLabel();
        tfQty = new JTextField(10);
        JButton btnBuy = new JButton("매수");
        JButton btnSell = new JButton("매도");
        JButton btnBack = new JButton("뒤로가기");

        c.gridx=0; c.gridy=0; c.gridwidth=2; add(new JLabel("<html><h2>매수/매도 화면</h2></html>"), c);
        c.gridwidth=1; c.gridy=1; add(new JLabel("종목:"), c);
        c.gridx=1; add(lblName, c);
        c.gridx=0; c.gridy=2; add(new JLabel("가격:"), c);
        c.gridx=1; add(lblPrice, c);
        c.gridx=0; c.gridy=3; add(new JLabel("수량:"), c);
        c.gridx=1; add(tfQty, c);

        JPanel p = new JPanel();
        p.add(btnBuy); p.add(btnSell); p.add(btnBack);
        c.gridx=0; c.gridy=4; c.gridwidth=2; add(p, c);

        btnBuy.addActionListener(e -> tradeAction("매수"));
        btnSell.addActionListener(e -> tradeAction("매도"));
        btnBack.addActionListener(e -> mainFrame.showStockList());
    }

    public void setStock(Stock s) {
        this.stock = s;
        lblName.setText(s.getStockName() + " (" + s.getStockCode() + ")");
        lblPrice.setText(String.format("%,d 원", s.getCurrentPrice()));
        tfQty.setText("");
    }

    private void tradeAction(String type) {
        if (tfQty.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "수량을 입력하세요");
            return;
        }
        try {
            int qty = Integer.parseInt(tfQty.getText().trim());
            if (qty <= 0) throw new NumberFormatException();
            
            boolean success = false;
            if ("매수".equals(type)) {
                success = orderService.processBuyOrder(stock.getStockCode(), qty);
            } else if ("매도".equals(type)) {
                success = orderService.processSellOrder(stock.getStockCode(), qty);
            }

            if (success) {
                JOptionPane.showMessageDialog(this, type + " 주문 완료\n" +
                        stock.getStockName() + " " + qty + "주");
                mainFrame.showStockList();
            } else {
                JOptionPane.showMessageDialog(this, type + " 실패\n잔액 부족 또는 보유 수량 부족 등을 확인하세요.");
            }
            
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "유효한 수량을 입력하세요");
        }
    }
}
