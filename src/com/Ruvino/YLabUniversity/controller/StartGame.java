package com.Ruvino.YLabUniversity.controller;

import com.Ruvino.YLabUniversity.services.GamePlay;
import com.Ruvino.YLabUniversity.model.Player;
import com.Ruvino.YLabUniversity.repository.DataBaseActions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;


public class StartGame {

    public static final String BEGIN_ANSI_RED = "\u001B[31m";
    public static final String END_ANSI_RED = "\u001B[0m";

    public static void main(String[] args) throws IOException, SQLException {

        DataBaseActions.initDB();

        System.out.println("Добро пожаловать в Крестики-Нолики (Ultimate Edition)");

        printRules();

        System.out.print("Игрок 1, представьтесь, пожалуйста, вы будете играть за \"X\": ");
        String namePlayer1 = new BufferedReader(new InputStreamReader(System.in)).readLine();

        System.out.print("Игрок 2, представьтесь, пожалуйста, вы будете играть за \"O\": ");
        String namePlayer2 = new BufferedReader(new InputStreamReader(System.in)).readLine();

        Player player1 = new Player(namePlayer1);
        Player player2 = new Player(namePlayer2, 'O');

        ArrayList<Player> players = new ArrayList<>(2);

        players.add(player1);
        players.add(player2);

        GamePlay.start(players);
    }

    private static void printRules() {
        System.out.println(BEGIN_ANSI_RED);
        System.out.print("Внимание! Правила хода как на NumPad клавиатуре!\n");
        System.out.print("""
                7 | 8 | 9
                4 | 5 | 6
                1 | 2 | 3
                        """);
        System.out.println(END_ANSI_RED);
    }
}