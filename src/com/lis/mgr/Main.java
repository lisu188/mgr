package com.lis.mgr;


import com.lis.mgr.gui.GameGui;
import com.lis.mgr.logic.LifeBoard;

import javax.swing.*;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class Main extends JFrame {
    public static final int SIZE = 1000;

    public static final int SCALE = 1;

    private static final ExecutorService EXECUTOR = Executors.newCachedThreadPool();
    private GameGui gameGui;


    Main() {
        createUI();
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

    private void createUI() {
        setTitle("Game of Life");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
//        setSize(300, 200);
        setLocationRelativeTo(null);

        gameGui = new GameGui();

        createLayout(gameGui.getMainPanel());

        LifeBoard board = new LifeBoard(SIZE);

        gameGui.getRandomizeButton().addActionListener(e -> {
            board.randomize((int) (SIZE * SIZE * Integer.parseInt(gameGui.getRandomizePercent().getText()) / 100.0));
            refreshBoard(board);
        });

        gameGui.getIterateButton().addActionListener(e -> {
            for (int i = 0; i < Integer.parseInt(gameGui.getIterationsNumber().getText()); i++) {
                board.iterate();
            }
            refreshBoard(board);
        });

        gameGui.getApplyRulesButton().addActionListener(e -> applyRules(board));

        gameGui.getClearButton().addActionListener(e -> {
            board.clear();
            refreshBoard(board);
        });
    }


    private void refreshBoard(LifeBoard board) {
        boolean[] rawBoard = board.getBoard();
        gameGui.getGamePanel().setBoard(rawBoard);
        gameGui.getGamePanel().repaint();
        gameGui.getPopulationBar().setMaximum(rawBoard.length);
        gameGui.getPopulationBar().setValue(calculatePopulation(rawBoard));
    }

    private void applyRules(LifeBoard board) {
        Set<Integer> born = new HashSet<>();
        if (gameGui.getBorn0().isSelected()) {
            born.add(0);
        }
        if (gameGui.getBorn1().isSelected()) {
            born.add(1);
        }
        if (gameGui.getBorn2().isSelected()) {
            born.add(2);
        }
        if (gameGui.getBorn3().isSelected()) {
            born.add(3);
        }
        if (gameGui.getBorn4().isSelected()) {
            born.add(4);
        }
        if (gameGui.getBorn5().isSelected()) {
            born.add(5);
        }
        if (gameGui.getBorn6().isSelected()) {
            born.add(6);
        }
        if (gameGui.getBorn7().isSelected()) {
            born.add(7);
        }
        if (gameGui.getBorn8().isSelected()) {
            born.add(8);
        }
        Set<Integer> survives = new HashSet<>();
        if (gameGui.getSurvive0().isSelected()) {
            survives.add(0);
        }
        if (gameGui.getSurvive1().isSelected()) {
            survives.add(1);
        }
        if (gameGui.getSurvive2().isSelected()) {
            survives.add(2);
        }
        if (gameGui.getSurvive3().isSelected()) {
            survives.add(3);
        }
        if (gameGui.getSurvive4().isSelected()) {
            survives.add(4);
        }
        if (gameGui.getSurvive5().isSelected()) {
            survives.add(5);
        }
        if (gameGui.getSurvive6().isSelected()) {
            survives.add(6);
        }
        if (gameGui.getSurvive7().isSelected()) {
            survives.add(7);
        }
        if (gameGui.getSurvive8().isSelected()) {
            survives.add(8);
        }
        board.setRules(survives, born);
    }

    private int calculatePopulation(boolean[] board) {
        return Math.toIntExact(IntStream.range(0, board.length).parallel().filter(value -> board[value]).count());
    }

    public static void main(String[] args) throws IOException {
//        LifeParser parser = new LifeParser("Life/Guns/period-52-glider-gun.rle").parse();
//
//        LifeBoard board = new LifeBoard(SIZE)
//                .load(parser.getData());

        Main frame = new Main();
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

