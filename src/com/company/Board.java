package com.company;

import java.util.*;
import java.util.stream.IntStream;

import static java.lang.System.out;

public class Board {
    private List<String> positions = Arrays.asList("a", "b", "c", "d", "e", "f", "g", "h");

    private List<List<Cell>> board = new ArrayList<>();

    public Board(int numOfCells) {
        IntStream.range(0, numOfCells).boxed().forEach(i -> {List<Cell> t = new ArrayList<>(); board.add(t);});
        IntStream.range(0, numOfCells).boxed().forEach(i -> board.forEach(e -> e.add(new Cell(" ", new int[] {board.indexOf(e), i}))));
    }

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

    private void fillWithQueens() {
        IntStream.range(0, 8).boxed().forEach(i ->
                IntStream.rangeClosed(1, 8).boxed().forEach(j ->
                        IntStream.rangeClosed(1, 8).boxed().forEach(y ->
                                addQueen(new int[]{y, j}, false)
                        )
                )
        );
    }

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

    public int getSize() {
        return board.size();
    }


//
//    public boolean isAMatch(Board b) {
////        boolean tortrn = false;
////        this.board.forEach(c -> {
////            c.forEach(cell -> {
////                b.toList().forEach(x -> {
////                    x.
////                }
////            });
////
////
////                if (c.equals(cell.)) {
////                    tortrn = true;
////                }
////            });
////        });
//    }

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

    public List<List<Cell>> toList() {
        return this.board;
    }
}
