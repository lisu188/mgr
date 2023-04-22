package com.lis.mgr;


import com.lis.mgr.gui.GamePanel;
import com.lis.mgr.gui.MainFrame;
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


    init {
        createUI(title)
    }


    private fun createLayout(vararg arg: JComponent) {
        val gl = GroupLayout(contentPane)
        contentPane.layout = gl

        gl.autoCreateContainerGaps = true

        gl.setHorizontalGroup(
                gl.createSequentialGroup()
                        .addComponent(arg[0])
        )

        gl.setVerticalGroup(
                gl.createSequentialGroup()
                        .addComponent(arg[0])
        )

        pack()
    }

    private fun createUI(title: String) {
        setTitle(title)
        defaultCloseOperation = EXIT_ON_CLOSE
        setSize(300, 200)
        setLocationRelativeTo(null)

        clashGUI = ClashGUI()

        selectionController = SelectionController().withBytesTable(clashGUI.bytesTable)

        clashGUI.loadButton.addActionListener {
            withFile("E:/Gry/Clash/save") {
                save = parseFile(it.readBytes())

                initializeUnits()

                initializeTiles()

                initializePlayers()

                initializeCastles()

                initializeMap()
            }
        }

        initializeScripts()

        clashGUI.saveButton.addActionListener {
            withFile("E:/Gry/Clash/save") {
                it.writeBytes(save.bytes.toByteArray())
            }
        }

        createLayout(clashGUI.mainPanel)
    }

    public static void main(String[] args) throws IOException {
        LifeParser parser = new LifeParser("Life/Guns/period-52-glider-gun.rle").parse();

        LifeBoard board = new LifeBoard(SIZE)
                .load(parser.getData());

        MainFrame frame = new MainFrame();
        frame.setSize(SIZE * SCALE, SIZE * SCALE);
        frame.setVisible(true);

        EXECUTOR.submit((Callable<Void>) () -> {
            for (int i = 0; i < 100000; i++) {
                boolean[] iterate = board.iterate();
                int iteration = board.getIteration();
                System.out.println(iteration);
                if (iteration % 10 == 1) {
                    SwingUtilities.invokeAndWait(() -> {
                        frame.getGamePanel().setBoard(iterate);
                        frame.getGamePanel().repaint();
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

