package com.lis.mgr.gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {
    JPanel gamePanel;
    private JButton randomizeButton;
    private JPanel mainPanel;

    public MainFrame() {
        randomizeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    private void createUIComponents() {
        gamePanel = new GamePanel();
    }

    public GamePanel getGamePanel() {
        return (GamePanel) gamePanel;
    }
}
