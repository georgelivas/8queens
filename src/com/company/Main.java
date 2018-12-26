package com.company;

import java.util.*;
import java.util.stream.IntStream;

import static java.lang.System.out;

public class Main {
    // Solutions is an array of Boards that will be filled with all 92 possible solutions/
    private static List<Board> solutions = new ArrayList<>();
    private static Board board = new Board(8);

    public static void main(String[] args) {
        // call findAllSolutions to find all (92) possible solutions.
        findAllSolutions(board.getSize());

        out.println("Found " + solutions.size() + " possible solutions.\n");

        // read position from user.
        board.printChessBoard();
        int[] pos = readPositionFromConsole();

        board.addQueen(pos, true);


        // inform the user of the amount of solutions matching the user's input.
        int length = solutions.stream().filter(b -> b.isAMatch(pos)).toArray().length;
        solutions.stream().filter(b -> b.isAMatch(pos)).forEach(Board::printChessBoard);
        out.println("There are "
                + length
                + " possible solutions with a queen in the position "
                + board.getPositions().get(pos[0]-1).toUpperCase()
                + pos[1]
                + "."
        );

    }

    // findAllSolutions calls the backtrack function to find every possible solution.
    private static void findAllSolutions(int boardSize) {
        backtrack(new int[boardSize], 0);
    }

    // isAccurate receives an array of integers and checks if there are any conflicts.
    private static boolean isAccurate(int[] q, int pos) {
        return IntStream.range(0, pos).boxed().noneMatch(i ->
            q[i] == q[pos]
                    || (q[i] - q[pos]) == (pos - i)
                    || (q[pos] - q[i]) == (pos - i)
        );
    }
    // saveBoard takes as a parameter an array of integers that correspond
    // to x and y and adds a queen to an object board, then it adds the
    // board to the array solutions.
    private static void saveBoard(int[] q) {
        Board b = new Board(q.length);

        IntStream.range(0, q.length).boxed().forEach(i ->
            IntStream.range(0, q.length).boxed().forEach(j -> {
                if (q[i] == j) {
                    b.addQueen(new int[]{i+1, j+1}, false);
                }
            })
        );
        solutions.add(b);
    }
    // the method backtrack calls itself (recursion) until the board has 8 queens,
    // if there is a conflict it won't add a queen and it will proceed to the next column.
    private static void backtrack(int[] q, int pos) {
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
    // readPositionFromConsole reads a position from the console and returns it
    // in the form of array: [x, y] after it validates it.
    private static int[] readPositionFromConsole() {
        Scanner sc = new Scanner(System.in);
        out.println("Enter a position on the board.  (i.e. E2)");
        String pos = sc.nextLine();

        return validatePosition(pos);
    }

    // validatePosition receives a string in a form "e3" and translates it to array [x, y].
    private static int[] validatePosition(String position) {
        char[] pos = position.toCharArray();

        if (pos.length == 2) {
            int x = board.getPositions().indexOf(Character.toString(pos[0]).toLowerCase())+1;
            int y = Integer.parseInt(Character.toString(pos[1]));

            if (x > 0 && x <= 8 && y <= 8) {
                return new int[]{x, y};
            }
        }

        return new int[]{0, 0};
    }
}
