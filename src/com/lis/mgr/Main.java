package com.lis.mgr;


import com.lis.mgr.gui.GamePanel;
import com.lis.mgr.logic.LifeBoard;
import com.lis.mgr.res.LifeParser;

import javax.swing.*;
import java.io.IOException;
import java.util.concurrent.*;

public class Main {
    public static final int SIZE = 500;
    public static final double FACTOR = 0.5;
    public static final int SCALE = 3;

    private static final ExecutorService EXECUTOR = Executors.newCachedThreadPool();

    public static void main(String[] args) throws IOException {
        LifeParser parser = new LifeParser("Life/Guns/period-52-glider-gun.rle").parse();

        GamePanel panel = new GamePanel();

        LifeBoard board = new LifeBoard(SIZE)
                .load(parser.getData());

        JFrame frame = new JFrame();
        panel.setSize(SIZE * SCALE, SIZE * SCALE);
        frame.setContentPane(panel);
        frame.setSize(SIZE * SCALE, SIZE * SCALE);
        frame.setVisible(true);

        EXECUTOR.submit((Callable<Void>) () -> {
            for (int i = 0; i < 100000; i++) {
                boolean[] iterate = board.iterate();
                int iteration = board.getIteration();
                System.out.println(iteration);
                if (iteration % 10 == 1) {
                    SwingUtilities.invokeAndWait(() -> {
                        panel.setBoard(iterate);
                        panel.repaint();
                    });
                }
            }
//            SwingUtilities.invokeAndWait(() -> {
//                panel.setBoard(board.iterate());
//                panel.repaint();
//            });
            return null;
        });
    }
}

