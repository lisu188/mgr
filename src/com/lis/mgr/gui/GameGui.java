package com.lis.mgr.gui;

import javax.swing.*;

public class GameGui {
    JPanel gamePanel;
    private JButton randomizeButton;
    private JPanel mainPanel;
    private JButton iterateButton;
    private JFormattedTextField iterationsNumber;
    private JProgressBar populationBar;
    private JFormattedTextField randomizePercent;
    private JLabel populationLabel;
    private JRadioButton born7;
    private JRadioButton born6;
    private JRadioButton born5;
    private JRadioButton born4;
    private JRadioButton born3;
    private JRadioButton born2;
    private JRadioButton born1;
    private JRadioButton born0;
    private JRadioButton survive4;
    private JRadioButton survive3;
    private JRadioButton survive2;
    private JRadioButton survive1;
    private JRadioButton survive0;
    private JRadioButton survive5;
    private JRadioButton survive6;
    private JRadioButton survive7;
    private JRadioButton survive8;
    private JRadioButton born8;
    private JButton applyRulesButton;
    private JButton clearButton;

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

    public JFormattedTextField getIterationsNumber() {
        return iterationsNumber;
    }

    public JProgressBar getPopulationBar() {
        return populationBar;
    }

    public JFormattedTextField getRandomizePercent() {
        return randomizePercent;
    }

    public JButton getApplyRulesButton() {
        return applyRulesButton;
    }

    public JLabel getPopulationLabel() {
        return populationLabel;
    }

    public JRadioButton getBorn7() {
        return born7;
    }

    public JRadioButton getBorn6() {
        return born6;
    }

    public JRadioButton getBorn5() {
        return born5;
    }

    public JRadioButton getBorn4() {
        return born4;
    }

    public JRadioButton getBorn3() {
        return born3;
    }

    public JRadioButton getBorn2() {
        return born2;
    }

    public JRadioButton getBorn1() {
        return born1;
    }

    public JRadioButton getBorn0() {
        return born0;
    }

    public JRadioButton getSurvive4() {
        return survive4;
    }

    public JRadioButton getSurvive3() {
        return survive3;
    }

    public JRadioButton getSurvive2() {
        return survive2;
    }

    public JRadioButton getSurvive1() {
        return survive1;
    }

    public JRadioButton getSurvive0() {
        return survive0;
    }

    public JRadioButton getSurvive5() {
        return survive5;
    }

    public JRadioButton getSurvive6() {
        return survive6;
    }

    public JRadioButton getSurvive7() {
        return survive7;
    }

    public JRadioButton getSurvive8() {
        return survive8;
    }

    public JRadioButton getBorn8() {
        return born8;
    }

    public JButton getClearButton() {
        return clearButton;
    }
}
