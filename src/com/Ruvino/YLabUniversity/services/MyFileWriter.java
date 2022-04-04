package com.Ruvino.YLabUniversity.services;

import com.Ruvino.YLabUniversity.model.Player;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import static com.Ruvino.YLabUniversity.services.GamePlay.player1Score;
import static com.Ruvino.YLabUniversity.services.GamePlay.player2Score;

public class MyFileWriter {

    public static void writeFile(Player player1, Player player2) {

        String string1 = player1.getName() + ", ваше количество побед: " + player1Score;
        String string2 = player2.getName() + ", ваше количество побед: " + player2Score;
        System.out.println(string1);
        System.out.println(string2);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("rating.txt"))) {
            String text = string1 + "\n" + string2;
            bw.write(text);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}