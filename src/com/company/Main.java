package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Main {
    static String[] positions = {"a", "b", "c", "d", "e", "f", "g", "h"};

    static List<List<Cell>> board = new ArrayList<>();

    public static void main(String[] args) {
        makeBoard(8);
        printChessBoard();

        addQueen(readPositionFromConsole());
        printChessBoard();
    }

    public static void makeBoard(int y) {
        IntStream.range(0, y).boxed().forEach(i -> {List<Cell> t = new ArrayList<Cell>(); board.add(t);});
        IntStream.range(0, y).boxed().forEach(i -> board.stream().forEach(e -> e.add(new Cell(" "))));
    }

    public static int[] readPositionFromConsole() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter a position on the board.  (i.e. E2)");

        String pos = sc.nextLine();

        return validatePosition(pos);
    }

    public static int[] validatePosition(String position) {
        char[] pos = position.toCharArray();

        if (pos.length == 2) {
            String xs = "" + pos[0];
            xs = xs.toLowerCase();
            String ys = "" + pos[1];
            int x = Arrays.asList(positions).indexOf(xs) == -1 ? -1 : Arrays.asList(positions).indexOf(xs)+1;
            int y = Integer.parseInt(ys);

            if (x > 0 && x <= 8 && y <= 8) {
                int[] array = {x, y};
                return array;
            }
        }
        int[] array = {0, 0};
        return array;
    }

    public static void addQueen(int[] pos) {
        int x = pos[0]-1;
        int y = pos[1]-1;

        if (pos[0] != 0) {
            board.get(y).get(x).setContent("♛");
            // code in development
//            for (int i = 0; i < 8; i++) {
//                for (int j = 0; j < 8; j++) {
//                    for (int k = 0; k < 8; k++) {
//                        if (((i == y || j == x )
//                                || (i == y + k && j == x + k)
//                                || (i == y + k && j == x - k)
//                                || (i == y - k && j == x + k)
//                                || (i == y - k && j == x - k))
//                                && board.get(i).get(j).isAvailable()) {
//                            board.get(i).get(j).setContent(".");
//                        }
//                    }
//                }
//            }

            IntStream.range(0, 8).boxed().forEach(i ->
                    IntStream.range(0, 8).boxed().forEach(j ->
                            IntStream.range(0, 8).boxed().forEach(k -> {
                                if (((i == y || j == x)
                                        || (i == y + k && j == x + k)
                                        || (i == y + k && j == x - k)
                                        || (i == y - k && j == x + k)
                                        || (i == y - k && j == x - k))
                                        && board.get(i).get(j).isAvailable()) {
                                    board.get(i).get(j).setContent(".");
                                }
                            })
                            )
            );
            ////
        }
    }

    public static void printChessBoard() {
        System.out.print(" ");
        Arrays.asList(positions).stream().forEach(a -> System.out.print("   " + a.toUpperCase()));

        System.out.print("\n  +");

        IntStream.range(0, board.size()).boxed().forEach(e -> System.out.print("---+"));

        System.out.println();

            board.stream()
                .forEach(a -> {
                    System.out.print(board.indexOf(a)+1 + " ");

                    a.stream().forEach(e -> System.out.print("| " + e + " "));

                    System.out.print("|");
                    System.out.print("\n  +");

                    IntStream.range(0, board.size()).boxed().forEach(e -> System.out.print("---+"));

                    System.out.println();
                });
        System.out.println("\n");
    }
}
