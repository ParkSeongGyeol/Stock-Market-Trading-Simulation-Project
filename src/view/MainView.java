package view;

import javax.swing.SwingUtilities;

public class MainView {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MainFrame();
        });
    }
}
