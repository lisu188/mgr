package com.lis.mgr.res;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class LifeParser {
    private boolean stop;

    private record Options(int y, int x) {
        //TODO: rules
    }

    private record Entry(String key, String value) {

    }

    private final String file;

    private Options options;

    private int currentRow = 0;
    private int currentColumn = 0;

    private boolean[][] data;
    private String buffer = "";

    public LifeParser(String file) {
        this.file = file;
    }

    public boolean[][] getData() {
        return data;
    }

    public LifeParser parse() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(System.getProperty("user.home") + "/" + file));

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            onLine(line);
        }
        return this;
    }

    private void onLine(String line) {
        if (line.startsWith("#")) {
            return;
        }
        if (options == null) {
            options = parseOptions(line);
            optionsDone();
        } else {
            for (String character : line.split("")) {
                onSign(character);
                if (stop) {
                    return;
                }
            }
        }
    }

    private void onSign(String character) {
        switch (character) {
            case "b":
                if (buffer.isEmpty()) {
                    addDead(1);
                } else {
                    addDead(Integer.parseInt(buffer));
                    clearBuffer();
                }
                break;
            case "o":
                if (buffer.isEmpty()) {
                    addAlive(1);
                } else {
                    addAlive(Integer.parseInt(buffer));
                    clearBuffer();
                }
                break;
            case "$":
                if (buffer.isEmpty()) {
                    moveRow(1);
                } else {
                    moveRow(Integer.parseInt(buffer));
                    clearBuffer();
                }
                break;
            case "!":
                assertEmptyBuffer();
                stop = true;
                break;
            default:
                if (isNumber(character)) {
                    buffer += character;
                } else {
                    throw new RuntimeException("Unexpected sign: " + character + "!");
                }

        }
    }

    private void moveRow(int i) {
        currentRow += i;
        currentColumn = 0 ;
    }

    private void assertEmptyBuffer() {
        if (!buffer.isEmpty()) {
            throw new RuntimeException("Not empty buffer!");
        }
    }

    private void addAlive(int no) {
        for (int i = currentColumn; i < currentColumn + no; i++) {
            data[currentRow][i] = true;
        }
        currentColumn += no;
    }

    private void addDead(int no) {
        currentColumn += no;
    }

    private void clearBuffer() {
        buffer = "";
    }

    private boolean isNumber(String character) {
        try {
            Integer.parseInt(character);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    private void optionsDone() {
        data = new boolean[options.x][];
        for (int i = 0; i < options.x; i++) {
            data[i] = new boolean[options.y];
        }
    }

    private Options parseOptions(String line) {
        Map<String, List<Entry>> collect = Arrays.stream(line.split(",")).map(String::trim)
                .map(this::parseKeyValue).collect(Collectors.groupingBy(entry -> entry.key));
        return new Options(Integer.parseInt(collect.get("x").stream().findAny().orElseThrow().value),
                Integer.parseInt(collect.get("y").stream().findAny().orElseThrow().value));

    }

    private Entry parseKeyValue(String keyValue) {
        List<String> collect = Arrays.stream(keyValue.split("=")).map(String::trim).toList();
        return new Entry(collect.get(0), collect.get(1));
    }
}
