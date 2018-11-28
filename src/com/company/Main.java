package com.company;

import java.util.*;
import java.util.stream.IntStream;

import static java.lang.System.out;

public class Main {
    private static List<String> positions = Arrays.asList("a", "b", "c", "d", "e", "f", "g", "h");

    private static List<List<Cell>> board = new ArrayList<>();

    public static void main(String[] args) {
        makeBoard();
        printChessBoard();

        addQueen(readPositionFromConsole(), true);
        printChessBoard();

        fillWithQueens();
        printChessBoard();
    }

    private static void makeBoard() {
        IntStream.range(0, 8).boxed().forEach(i -> {List<Cell> t = new ArrayList<>(); board.add(t);});
        IntStream.range(0, 8).boxed().forEach(i -> board.forEach(e -> e.add(new Cell(" "))));
    }

    private static int[] readPositionFromConsole() {
        Scanner sc = new Scanner(System.in);
        out.println("Enter a position on the board.  (i.e. E2)");
        String pos = sc.nextLine();

        return validatePosition(pos);
    }

    private static int[] validatePosition(String position) {
        char[] pos = position.toCharArray();

        if (pos.length == 2) {
            int x = positions.indexOf(Character.toString(pos[0]).toLowerCase())+1;
            int y = Integer.parseInt(Character.toString(pos[1]));

            if (x > 0 && x <= 8 && y <= 8) {
                return new int[]{x, y};
            }
        }

        return new int[]{0, 0};
    }

    private static void addQueen(int[] pos, boolean byUser) {
        int x = pos[0]-1;
        int y = pos[1]-1;

        if (pos[0] != 0 && board.get(y).get(x).isAvailable()) {
            board.get(y).get(x).setContent(byUser ? "♛" : "♕");
            IntStream.range(0, 8).boxed().forEach(i ->
                    IntStream.range(0, 8).boxed().forEach(j ->
                            IntStream.range(0, 8).boxed().forEach(k -> {
                                if (((i == y || j == x)
                                        || (i == y + k && j == x + k)
                                        || (i == y + k && j == x - k)
                                        || (i == y - k && j == x + k)
                                        || (i == y - k && j == x - k))
                                        && board.get(i).get(j).isAvailable()) {
                                    // board.get(i).get(j).setContent(".");
                                    board.get(i).get(j).setAvailable(false);
                                }
                            })
                    )
            );
        }
    }

    private static void fillWithQueens() {
        IntStream.range(0, 8).boxed().forEach(i ->
                IntStream.rangeClosed(1, 8).boxed().forEach(j ->
                        IntStream.rangeClosed(1, 8).boxed().forEach(y ->
                                addQueen(new int[]{y, j}, false)
                        )
                )
        );
    }

    private static void printChessBoard() {
        out.print(" ");
        positions.forEach(a -> out.print("   " + a.toUpperCase()));

        out.print("\n  +");
        IntStream.range(0, board.size()).boxed().forEach(e -> out.print("---+"));

        out.println();

        board.forEach(a -> {
                out.print(board.indexOf(a)+1 + " ");

                a.forEach(e -> out.print("| " + e + " "));

                out.print("|");
                out.print("\n  +");

                IntStream.range(0, board.size()).boxed().forEach(e -> out.print("---+"));

                out.println();
        });

        out.println("\n");
    }
}
