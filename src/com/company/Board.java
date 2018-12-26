package com.company;

import java.util.*;
import java.util.stream.IntStream;

import static java.lang.System.out;

// Class Board contains a 2D array of Cells

public class Board {
    private List<String> positions = Arrays.asList("a", "b", "c", "d", "e", "f", "g", "h");

    private List<List<Cell>> board;

    // the constructor fills the array with Cell objects.
    public Board(int numOfCells) {
        board = new ArrayList<>();
        IntStream.range(0, numOfCells).boxed().forEach(i -> {List<Cell> t = new ArrayList<>(); board.add(t);});
        IntStream.range(0, numOfCells).boxed().forEach(i -> board.forEach(e -> e.add(new Cell(" "))));
    }

    // the method addQueen receives as parameters an array of type: [x, y],
    // and adds a queen to the corresponding cell if there are no conflicts.
    public void addQueen(int[] pos, boolean byUser) {
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
    // the method prints the board array,
    // when printed it looks like this:
    //      A   B   C   D   E   F   G   H
    //   +---+---+---+---+---+---+---+---+
    // 1 | ♕ |   |   |   |   |   |   |   |
    //   +---+---+---+---+---+---+---+---+
    // 2 |   |   |   |   |   |   | ♕ |   |
    //   +---+---+---+---+---+---+---+---+
    // 3 |   |   |   |   | ♕ |   |   |   |
    //   +---+---+---+---+---+---+---+---+
    // 4 |   |   |   |   |   |   |   | ♕ |
    //   +---+---+---+---+---+---+---+---+
    // 5 |   | ♕ |   |   |   |   |   |   |
    //   +---+---+---+---+---+---+---+---+
    // 6 |   |   |   | ♕ |   |   |   |   |
    //   +---+---+---+---+---+---+---+---+
    // 7 |   |   |   |   |   | ♕ |   |   |
    //   +---+---+---+---+---+---+---+---+
    // 8 |   |   | ♕ |   |   |   |   |   |
    //   +---+---+---+---+---+---+---+---+
    public void printChessBoard() {
        out.print(" ");

        positions.forEach(a -> out.print("   " + a.toUpperCase()));
        out.print("\n  +");

        IntStream.range(0, board.size()).boxed().forEach(e -> out.print("---+"));
        out.println();

        board.forEach(a -> {
            out.print(board.indexOf(a)+1 + " ");

            a.forEach(e -> out.print("| " + e + " "));
            out.print("|\n  +");

            IntStream.range(0, board.size()).boxed().forEach(e -> out.print("---+"));
            out.println();
        });
        out.println("\n");
    }

    // the class contains 2 getters getSize that returns the length of the list board
    // and getPositions, which returns the list positions containing [a, b, c, ..., h].
    protected int getSize() {
        return board.size();
    }

    protected List<String> getPositions() {
        return positions;
    }

    // the method toList returns the board in the form of ArrayList
    public List<List<Cell>> toList() {
        return this.board;
    }

    // isAMatch checks if there is a queen in the cell received as a parameter, then it returns a boolean.
    protected boolean isAMatch(int[] pos) {
        return this.board.get(pos[1]-1).get(pos[0]-1).getContent().equals("♕");
    }

    // the method equals is overridden from class Object, it compares 2 boards and returns a boolean.
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Board)) return false;
        Board board1 = (Board) o;
        return Objects.equals(board, board1.board);
    }

    @Override
    public int hashCode() {
        return Objects.hash(board);
    }
}
