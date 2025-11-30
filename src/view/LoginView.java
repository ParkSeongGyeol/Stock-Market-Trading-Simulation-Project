package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginView extends JPanel {
    private MainFrame mainFrame;
    private JTextField tfUser;
    private JPasswordField pfPass;

    public LoginView(MainFrame frame) {
        this.mainFrame = frame;
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        JLabel lblTitle = new JLabel("<html><h1>로그인</h1></html>");
        c.gridx = 0; c.gridy = 0; c.gridwidth = 2; c.insets = new Insets(10,10,20,10);
        add(lblTitle, c);

        c.gridwidth = 1; c.insets = new Insets(5,5,5,5);
        c.gridx = 0; c.gridy = 1; add(new JLabel("아이디:"), c);
        c.gridx = 1; tfUser = new JTextField(15); add(tfUser, c);

        c.gridx = 0; c.gridy = 2; add(new JLabel("비밀번호:"), c);
        c.gridx = 1; pfPass = new JPasswordField(15); add(pfPass, c);

        JButton btnLogin = new JButton("로그인");
        c.gridx = 0; c.gridy = 3; c.gridwidth = 2; c.insets = new Insets(15,5,5,5);
        add(btnLogin, c);

        btnLogin.addActionListener(e -> {
            String id = tfUser.getText().trim();
            String pw = new String(pfPass.getPassword());
            if (id.isEmpty() || pw.isEmpty()) {
                JOptionPane.showMessageDialog(this, "아이디와 비밀번호를 입력하세요");
            } else {
                // AuthService를 통한 로그인은 MainFrame에서 처리하거나 여기서 호출
                // 일단 UI 흐름상 MainFrame에 위임
                mainFrame.handleLogin(id, pw);
            }
        });
    }
}
