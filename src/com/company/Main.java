package com.company;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Main {

    static String[] positions = {"a", "b", "c", "d", "e", "f", "g", "h"};

    static String[][] chessBoard;

//            = {
//            {" ", " ", " ", " ", " ", " ", " ", " "},
//            {" ", " ", " ", " ", " ", " ", " ", " "},
//            {" ", " ", " ", " ", " ", " ", " ", " "},
//            {" ", " ", " ", " ", " ", " ", " ", " "},
//            {" ", " ", " ", " ", " ", " ", " ", " "},
//            {" ", " ", " ", " ", " ", " ", " ", " "},
//            {" ", " ", " ", " ", " ", " ", " ", " "},
//            {" ", " ", " ", " ", " ", " ", " ", " "},
//    };

    public static void main(String[] args) {

        makeBoard(8, 8);
        printChessBoard();

        addQueen(readPositionFromConsole());
        printChessBoard();


    }

    public static void makeBoard(int x, int y) {
        chessBoard = new String[y][x];
        for (int i = 0; i < y; i++){
            for (int j = 0; j < x; j++) {
                chessBoard[i][j] = " ";
            }
        }
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

            if(x > 0 && x <= 8 && y <= 8) {
                int[] array = {x, y};
                return array;
            }

        }
        int[] array = {0, 0};
        return array;
    }

    public static void addQueen(int[] pos) {
        if (pos[0] != 0) {
            chessBoard[pos[1]-1][pos[0]-1] = "♛";
        }
    }

    public static void printChessBoard() {
        System.out.print(" ");
        for (String a : positions) {
            System.out.print("   " + a.toUpperCase());
        }

        System.out.print("\n  +");
        for (int i = 0; i < chessBoard.length; i++) {
            System.out.print("---+");
        }

        System.out.println();

        for (int i = 0; i < chessBoard.length; i++) {
            System.out.print(i+1 + " ");
            for (int j = 0; j < chessBoard[i].length; j++) {
                System.out.print("| " + chessBoard[i][j] + " ");
            }
            System.out.print("|\n  +");
            for (int y = 0; y < chessBoard.length; y++) {
                System.out.print("---+");
            }
            System.out.println();
        }
        System.out.println("\n\n\n");
    }
}
