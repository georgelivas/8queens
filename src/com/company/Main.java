package com.company;

public class Main {

    static char[] positions = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};

    static char[][] chessBoard = {
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
    };

    public static void main(String[] args) {


        printChessBoard();

        addQueen(4, 3);
        System.out.println("adding a queen  at pos. e4");
        printChessBoard();
    }

    // public static

    public static void addQueen(int x, int y) {
        chessBoard[y][x] = '*';
    }

    public static void printChessBoard() {
        System.out.print(" ");
        for (char a : positions) {
            System.out.print("   " + a);
        }

        System.out.print("\n  +");
        for (int i = 0; i < chessBoard.length*4-1; i++) {
            System.out.print('-');
        }

        System.out.println('+');

        for (int i = 0; i < chessBoard.length; i++) {
            System.out.print(i+1 + " ");
            for (int j = 0; j < chessBoard[i].length; j++) {
                if(i%2 ==0) {
                    if (j % 2 == 0) {
                        System.out.print("| " + "\033[40m" + chessBoard[i][j] + "\033[0m" + " ");
                    } else {
                        System.out.print("| " + chessBoard[i][j] + " ");
                    }
                } else {
                    if (j % 2 == 1) {
                        System.out.print("| " + "\033[40m" + chessBoard[i][j] + "\033[0m" + " ");
                    } else {
                        System.out.print("| " + chessBoard[i][j] + " ");
                    }
                }
            }
            System.out.print("|\n  +");
            for (int y = 0; y < chessBoard.length*4-1; y++) {
                System.out.print('-');
            }
            System.out.print("+\n");
        }
        System.out.println("\n\n\n");
    }
}
