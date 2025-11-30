package main;

import javax.swing.SwingUtilities;
import view.MainFrame;

public class Main {
    public static void main(String[] args) {
        System.out.println("=================================");
        System.out.println("    모의 주식 투자 시스템 v1.0");
        System.out.println("=================================");
        
        SwingUtilities.invokeLater(() -> {
            new MainFrame();
        });
    }
}