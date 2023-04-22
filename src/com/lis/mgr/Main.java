package com.lis.mgr;


import com.lis.mgr.gui.GameGui;
import com.lis.mgr.logic.LifeBoard;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class Main extends JFrame {
    public static final int SIZE = 500;
    public static final double FACTOR = 0.5;
    public static final int SCALE = 1;

    private static final ExecutorService EXECUTOR = Executors.newCachedThreadPool();
    private GameGui gameGui;


    Main() {
        createUI("Game of Life");
    }


    private void createLayout(JComponent arg) {
        GroupLayout gl = new GroupLayout(getContentPane());
        getContentPane().setLayout(gl);

        gl.setAutoCreateContainerGaps(true);

        gl.setHorizontalGroup(
                gl.createSequentialGroup()
                        .addComponent(arg)
        );

        gl.setVerticalGroup(
                gl.createSequentialGroup()
                        .addComponent(arg)
        );

        pack();
    }

    private void createUI(String title) {
        setTitle(title);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);

        gameGui = new GameGui();

        createLayout(gameGui.getMainPanel());

        LifeBoard board = new LifeBoard(SIZE);

        gameGui.getRandomizeButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                board.randomize((int) (SIZE * SIZE * FACTOR));
                refreshBoard(board.getBoard());
            }
        });

        gameGui.getIterateButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < Integer.parseInt(gameGui.getIterationsNumber().getText()); i++) {
                    board.iterate();
                }
                refreshBoard(board.getBoard());
            }
        });
    }

    private void refreshBoard(boolean[] board) {
        gameGui.getGamePanel().setBoard(board);
        gameGui.getGamePanel().repaint();
        System.out.println("Population: " + calculatePopulation(board));
    }

    private long calculatePopulation(boolean[] board) {
        return IntStream.range(0, board.length).parallel().filter(value -> board[value]).count();
    }

    public static void main(String[] args) throws IOException {
//        LifeParser parser = new LifeParser("Life/Guns/period-52-glider-gun.rle").parse();
//
//        LifeBoard board = new LifeBoard(SIZE)
//                .load(parser.getData());

        Main frame = new Main();
        frame.setSize(SIZE * SCALE, SIZE * SCALE);
        frame.setVisible(true);

//        EXECUTOR.submit((Callable<Void>) () -> {
//            for (int i = 0; i < 100000; i++) {
//                boolean[] iterate = board.iterate();
//                int iteration = board.getIteration();
//                System.out.println(iteration);
//                if (iteration % 10 == 1) {
//                    SwingUtilities.invokeAndWait(() -> {
//
//                    });
//                }
//            }
////            SwingUtilities.invokeAndWait(() -> {
////                panel.setBoard(board.iterate());
////                panel.repaint();
////            });
//            return null;
//        });
    }
}

