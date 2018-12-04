package com.company;

import java.util.*;
import java.util.stream.IntStream;

import static java.lang.System.out;

public class Main {
    private static List<String> positions = Arrays.asList("a", "b", "c", "d", "e", "f", "g", "h");

    private static List<Board> solutions = new ArrayList<>();
    private static Board board = new Board(8);

    public static void main(String[] args) {
//        makeBoard();
//        printChessBoard();
//
//        addQueen(readPositionFromConsole(), true);
//        printChessBoard();
//
//        fillWithQueens();
//        printChessBoard();

        findAllSolutions(8);

        solutions.forEach(Board::printChessBoard);
        out.println(solutions.size());
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


    public static void saveBoard(int[] q) {
        int n = q.length;
        Board b = new Board(q.length);
        IntStream.range(0, n).boxed().forEach(i -> {
            IntStream.range(0, n).boxed().forEach(j -> {
                if (q[i] == j) {
                    b.addQueen(new int[]{i+1, j+1}, false);
                }
            });
        });
        solutions.add(b);
    }

    public static void findAllSolutions(int n) {
        int[] a = new int[n];
        backtrack(a, 0);
    }

    public static void backtrack(int[] q, int pos) {
        if (pos == q.length) {
            saveBoard(q);
        } else {
            IntStream.range(0, q.length).boxed().forEach(i -> {
                q[pos] = i;
                if (isConsistent(q, pos)) {
                    backtrack(q, pos+1);
                }
            });
        }
    }

    /////////////////////

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
}
