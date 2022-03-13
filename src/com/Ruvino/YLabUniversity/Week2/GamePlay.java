package com.Ruvino.YLabUniversity.Week2;

import com.Ruvino.YLabUniversity.Week3.MyXMLWriter;

import java.util.*;

public class GamePlay {

    static int player1Score = 0;
    static int player2Score = 0;
    static Scanner input = new Scanner(System.in);

    public static LinkedHashMap<Integer, Integer> listOfStep = new LinkedHashMap<>(9);

    public static void start(ArrayList<Player> players){

        Player player1 = players.get(0);
        Player player2 = players.get(1);

        char[][] gameBoard = {
                {'_', '|', '_', '|', '_'},
                {'_', '|', '_', '|', '_'},
                {' ', '|', ' ', '|', ' '}
        };

        GameBoard.printBoard(gameBoard);

        boolean gameOver = false;
        boolean playAgain = true;

        while (playAgain) {
            while (!gameOver) {

                playerMove(gameBoard, player1);
                gameOver = GameController.isGameOver(gameBoard, player1);
                if (gameOver) break;

                playerMove(gameBoard, player2);
                gameOver = GameController.isGameOver(gameBoard, player2);
                if (gameOver) break;
            }

            MyXMLWriter.createLogFile(listOfStep, players);
            MyFileWriter.writeFile(player1, player2);

            System.out.println("Хотите сыграть ещё раз? Д/Н");
            input.nextLine();
            String result = input.nextLine();

            switch (result) {
                case "Д", "д" -> {
                    System.out.println("Отлично, сыграем ещё раз!");
                    GameBoard.resetBoard(gameBoard);
                    gameOver = false;
                    GameBoard.printBoard(gameBoard);
                }
                case "Н", "н" -> {
                    playAgain = false;
                    System.out.println("Спасибо за игру, увидимся в следующий раз");
                }
                default -> {
                    playAgain = false;
                    System.out.println("Неопознаный символ, программа аварийно закрывается");
                }
            }
        }
    }

    public static void playerMove(char[][] gameBoard, Player player) {

        System.out.println(player.getName() + ", Ваш ход. (Введите число от 1 до 9)");

        int move = GamePlay.input.nextInt();

        boolean result = GameController.isValidMove(move, gameBoard);

        while (!result) {
            System.out.println("Нельзя поставить. Повторите попытку.");
            move = GamePlay.input.nextInt();
            result = GameController.isValidMove(move, gameBoard);
        }

        System.out.printf("%s поставил \"%c\" на позицию %d\n", player.getName(), player.getCharacter(), move);

        listOfStep.put(move, player.getPlayerNumber());

        GameBoard.updateBoard(move, player.getPlayerNumber(), gameBoard);
    }

    public static void playerMove(char[][] gameBoard, int move, int playerNumber){

        GameBoard.updateBoard(move, playerNumber, gameBoard);

    }
}
