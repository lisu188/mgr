package com.lis.mgr.gui;

import javax.swing.*;

public class GameGui {
    JPanel gamePanel;
    private JButton randomizeButton;
    private JPanel mainPanel;
    private JButton iterateButton;
    private JTextPane iterationsNumber;

    public GameGui() {

    }

    private void createUIComponents() {
        gamePanel = new GamePanel();
    }

    public GamePanel getGamePanel() {
        return (GamePanel) gamePanel;
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public AbstractButton getRandomizeButton() {
        return randomizeButton;
    }

    public JButton getIterateButton() {
        return iterateButton;
    }

    public JTextPane getIterationsNumber() {
        return iterationsNumber;
    }
}
