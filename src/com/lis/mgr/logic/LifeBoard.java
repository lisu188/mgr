package com.lis.mgr.logic;

import java.util.Arrays;
import java.util.Random;
import java.util.Set;
import java.util.stream.IntStream;

public class LifeBoard {
    private boolean[] board;
    private final boolean toroid = false;    //TODO: toroid
    private final int size;
    private int iteration = 0;

    public void setRules(Set<Integer> survives, Set<Integer> born) {
        this.survives = survives;
        this.born = born;
    }

    private Set<Integer> survives = Set.of(2, 3);
    private Set<Integer> born = Set.of(3);


    public LifeBoard(int size) {
        this.size = size;
        clear(size);
    }

    private void clear(int size) {
        board = new boolean[size * size];
    }

    public LifeBoard randomize(int count) {
        Random random = new Random();
        for (int i = 0; i < count; i++) {
            int index = random.nextInt(size * size);
            board[index] = !board[index];
        }
        return this;
    }


    public String print() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[toIndex(i, j, size)]) {
                    result.append("x");
                } else {
                    result.append("  ");
                }
            }
            result.append("\n");
        }
        result.append("\n");
        return result.toString();
    }

    public static int toIndex(int x, int y, int size) {
        return x * size + y;
    }

    public boolean[] getBoard() {
        return Arrays.copyOf(board, board.length);
    }

    public boolean[] iterate() {
        iteration++;
        boolean[] newBoard = Arrays.copyOf(board, board.length);

        IntStream.range(0, size * size).parallel().forEach(index -> {
            int i = index / size;
            int j = index % size;

            int count = countNext(i, j);

            if (board[index]) {
                if (!survives.contains(count)) {
                    newBoard[index] = false;
                }
            } else {
                if (born.contains(count)) {
                    newBoard[index] = true;
                }
            }
        });

        boolean[] tmpBoard = board;
        board = newBoard;
        return tmpBoard;
    }

    private int countNext(int i, int j) {
        int count = 0;
        for (int _i = -1; _i <= 1; _i++)
            for (int _j = -1; _j <= 1; _j++) {
                if (_i == 0 && _j == 0) {
                    continue;
                }
                if (!isInBounds(i + _i, j + _j)) {
                    continue;
                }
                if (board[toIndex(i + _i, j + _j, size)]) {
                    count++;
                }
            }
        return count;
    }

    private boolean isInBounds(int i, int j) {
        return !(i < 0 || i >= size || j < 0 || j >= size);
    }

    public int getIteration() {
        return iteration;
    }

    public LifeBoard load(boolean[][] data) {
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                if (data[i][j]) {
                    addAlive(i, j);
                }
            }
        }
        return this;
    }

    private void addAlive(int i, int j) {
        board[toIndex(i, j, size)] = true;
    }

    public void clear() {
        clear(size);
    }
}
