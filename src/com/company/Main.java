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

        findAllSolutions(board.getSize());

        // solutions.forEach(Board::printChessBoard);
        out.println("Found " + solutions.size() + " possible solutions.");
    }

    public static void findAllSolutions(int boardSize) {
        backtrack(new int[boardSize], 0);
    }

    public static boolean isAccurate(int[] q, int pos) {
        for (int i = 0; i < pos; i++) {
            if (q[i] == q[pos]) { // same column
                return false;
            } else if ((q[i] - q[pos]) == (pos - i)) { // same major diagonal
                return false;
            }else if ((q[pos] - q[i]) == (pos - i)) { // same minor diagonal
                return false;
            }
        }

        return true;
    }


    public static void saveBoard(int[] q) {
        Board b = new Board(q.length);

        IntStream.range(0, q.length).boxed().forEach(i -> {
            IntStream.range(0, q.length).boxed().forEach(j -> {
                if (q[i] == j) {
                    b.addQueen(new int[]{i+1, j+1}, false);
                }
            });
        });
        solutions.add(b);
    }

    public static void backtrack(int[] q, int pos) {
        if (pos == q.length) {
            saveBoard(q);
        } else {
            IntStream.range(0, q.length).boxed().forEach(i -> {
                q[pos] = i;
                if (isAccurate(q, pos)) {
                    backtrack(q, pos+1);
                }
            });
        }
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
}
