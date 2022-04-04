package com.Ruvino.YLabUniversity.services;


public class GameBoard {

    public static void printBoard(char[][] gameBoard) {

        for (char[] row : gameBoard) {
            for (char c : row) {
                System.out.print(c);
            }
            System.out.println();
        }
    }

    public static void updateBoard(int position, int playerNumber, char[][] gameBoard) {

        char character = 'X';

        if (playerNumber == 2) character = 'O';


        switch (position) {
            case 1 -> {
                gameBoard[2][0] = character;
                printBoard(gameBoard);
            }
            case 2 -> {
                gameBoard[2][2] = character;
                printBoard(gameBoard);
            }
            case 3 -> {
                gameBoard[2][4] = character;
                printBoard(gameBoard);
            }
            case 4 -> {
                gameBoard[1][0] = character;
                printBoard(gameBoard);
            }
            case 5 -> {
                gameBoard[1][2] = character;
                printBoard(gameBoard);
            }
            case 6 -> {
                gameBoard[1][4] = character;
                printBoard(gameBoard);
            }
            case 7 -> {
                gameBoard[0][0] = character;
                printBoard(gameBoard);
            }
            case 8 -> {
                gameBoard[0][2] = character;
                printBoard(gameBoard);
            }
            case 9 -> {
                gameBoard[0][4] = character;
                printBoard(gameBoard);
            }
        }
    }

    public static void resetBoard(char[][] gameBoard) {
        gameBoard[0][0] = '_';
        gameBoard[0][2] = '_';
        gameBoard[0][4] = '_';
        gameBoard[1][0] = '_';
        gameBoard[1][2] = '_';
        gameBoard[1][4] = '_';
        gameBoard[2][0] = ' ';
        gameBoard[2][2] = ' ';
        gameBoard[2][4] = ' ';
    }
}
