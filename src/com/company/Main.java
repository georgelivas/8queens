package com.company;

import java.util.*;
import java.util.stream.IntStream;

import static java.lang.System.out;

public class Main {
    private static List<String> positions = Arrays.asList("a", "b", "c", "d", "e", "f", "g", "h");

    private static List<List<Cell>> board = new ArrayList<>();
    static int count = 0;

    public static void main(String[] args) {
//        makeBoard();
//        printChessBoard();
//
//        addQueen(readPositionFromConsole(), true);
//        printChessBoard();
//
//        fillWithQueens();
//        printChessBoard();

        calculate(8);
        out.println(count);
    }

    /////////////////////

    public static boolean isConsistent(int[] q, int n) {
        for (int i = 0; i < n; i++) {
            if (q[i] == q[n]) { // same column
                return false;
            } else if ((q[i] - q[n]) == (n - i)) { // same major diagonal
                return false;
            }else if ((q[n] - q[i]) == (n - i)) { // same minor diagonal
                return false;
            }
        }
        return true;
    }


    public static void printQueens(int[] q) {
        int n = q.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (q[i] == j) {
                    out.print("Q ");
                } else {
                    out.print("* ");
                }
            }
            out.println();
        }
        out.println();
        count++;
    }

    public static void calculate(int n) {
        int[] a = new int[n];
        backtrack(a, 0);
    }

    public static void backtrack(int[] q, int k) {
        if (k == q.length) {
            printQueens(q);
        } else {
            for (int i = 0; i < q.length; i++) {
                q[k] = i;
                if (isConsistent(q, k)) {
                    backtrack(q, k+1);
                }
            }
        }
    }

    /////////////////////

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

    private static void fillWithQueens() {
        IntStream.range(0, 8).boxed().forEach(i ->
                IntStream.rangeClosed(1, 8).boxed().forEach(j ->
                        IntStream.rangeClosed(1, 8).boxed().forEach(y ->
                                addQueen(new int[]{y, j}, false)
                        )
                )
        );
    }
}
