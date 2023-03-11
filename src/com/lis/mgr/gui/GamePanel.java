package com.lis.mgr.gui;

import com.lis.mgr.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static com.lis.mgr.logic.LifeBoard.toIndex;

public class GamePanel extends JPanel {
    private boolean[] board;

    public GamePanel() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (board != null) {
            for (int i = 0; i < Main.SIZE; i++)
                for (int j = 0; j < Main.SIZE; j++) {
                    if (board[toIndex(i, j, Main.SIZE)]) {
                        g.setColor(Color.WHITE);
                        g.fillRect(i * Main.SCALE, j * Main.SCALE, (i + 1) * Main.SCALE, (j + 1) * Main.SCALE);
                    } else {
                        g.setColor(Color.BLACK);
                        g.fillRect(i * Main.SCALE, j * Main.SCALE, (i + 1) * Main.SCALE, (j + 1) * Main.SCALE);
                    }
                }
        }
    }

    public void setBoard(boolean[] board) {
        this.board = board;
    }
}
