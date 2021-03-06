package com.Ruvino.YLabUniversity.controller;

import com.Ruvino.YLabUniversity.services.MyXMLWriter;
import com.Ruvino.YLabUniversity.model.Player;
import com.Ruvino.YLabUniversity.services.GamePlay;

public class GameController {

    public static boolean isGameOver(char[][] gameBoard, Player player) {

        char playerCharacter = player.getCharacter();
        char side = '\u0000';

        //Horizontal Win
        if (gameBoard[0][0] != '_' && gameBoard[0][0] == gameBoard[0][2] && gameBoard[0][2] == gameBoard[0][4])
            side = playerCharacter;
        else if (gameBoard[1][0] != '_' && gameBoard[1][0] == gameBoard[1][2] && gameBoard[1][2] == gameBoard[1][4])
            side = playerCharacter;
        else if (gameBoard[2][0] != ' ' && gameBoard[2][0] == gameBoard[2][2] && gameBoard[2][2] == gameBoard[2][4])
            side = playerCharacter;

            // Vertical Win
        else if (gameBoard[0][0] != '_' && gameBoard[0][0] == gameBoard[1][0] && gameBoard[1][0] == gameBoard[2][0])
            side = playerCharacter;
        else if (gameBoard[0][2] != '_' && gameBoard[0][2] == gameBoard[1][2] && gameBoard[1][2] == gameBoard[2][2])
            side = playerCharacter;
        else if (gameBoard[0][4] != '_' && gameBoard[0][4] == gameBoard[1][4] && gameBoard[1][4] == gameBoard[2][4])
            side = playerCharacter;

            // Diagonal Win
        else if (gameBoard[0][0] != '_' && gameBoard[0][0] == gameBoard[1][2] && gameBoard[1][2] == gameBoard[2][4])
            side = playerCharacter;
        else if (gameBoard[2][0] != ' ' && gameBoard[2][0] == gameBoard[1][2] && gameBoard[1][2] == gameBoard[0][4])
            side = playerCharacter;

            // Tie
        else if (gameBoard[0][0] != '_' && gameBoard[0][2] != '_' && gameBoard[0][4] != '_'
                && gameBoard[1][0] != '_' && gameBoard[1][2] != '_' && gameBoard[1][4] != '_'
                && gameBoard[2][0] != ' ' && gameBoard[2][2] != ' ' && gameBoard[2][4] != ' ')
            side = 'T';

        return gameOver(side, player);
    }

    private static boolean gameOver(char side, Player player) {

        if (side == '\u0000') return false;

        String winText;

        if (side == 'T') {
            winText = "??????????";
            System.out.println(winText);
        } else {
            winText = player.getName() + " ??????????????";
            System.out.println(winText);
            addScore(player.getPlayerNumber());
        }

        if (!winText.equalsIgnoreCase("??????????")) MyXMLWriter.winPlayer = player;
        else MyXMLWriter.winPlayer = null;

        return true;
    }

    private static void addScore(int playerNumber) {
        if (playerNumber == 1) GamePlay.player1Score++;
        else GamePlay.player2Score++;
    }

    public static boolean isValidMove(int move, char[][] gameBoard) {

        return switch (move) {
            case 1 -> gameBoard[2][0] == ' ';
            case 2 -> gameBoard[2][2] == ' ';
            case 3 -> gameBoard[2][4] == ' ';
            case 4 -> gameBoard[1][0] == '_';
            case 5 -> gameBoard[1][2] == '_';
            case 6 -> gameBoard[1][4] == '_';
            case 7 -> gameBoard[0][0] == '_';
            case 8 -> gameBoard[0][2] == '_';
            case 9 -> gameBoard[0][4] == '_';
            default -> false;
        };
    }
}
