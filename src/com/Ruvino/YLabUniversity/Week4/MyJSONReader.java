package com.Ruvino.YLabUniversity.Week4;


import com.Ruvino.YLabUniversity.Week2.GamePlay;
import com.Ruvino.YLabUniversity.Week3.Adapter;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MyJSONReader extends MyWriter {

    public static void main(String[] args) {

        try {
            // create a reader
            Reader reader = Files.newBufferedReader(Paths.get("game_3.json"));

            // create JsonObject instance
            JsonObject parser = JsonParser.parseReader(reader).getAsJsonObject();

            // create JsonArray steps
            JsonArray steps = parser.get(rootElementName.toLowerCase()).getAsJsonObject().get(gameElementName.toLowerCase()).getAsJsonObject().get(stepElementName.toLowerCase()).getAsJsonArray();

            // read steps
            for (JsonElement step : steps) {
                JsonObject obj = step.getAsJsonObject();
               int playerNumber = obj.get(attrPlayerIdStepElement).getAsInt();
               int move = Adapter.convertValue(obj.get("value").getAsString());

                System.out.println();
                GamePlay.playerMove(gameBoard, move, playerNumber);
            }

            printPlayerWin(parser);

            //close reader
            reader.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void printPlayerWin(JsonObject parser) {

        try {
            JsonObject playerWin = parser.get(rootElementName.toLowerCase()).getAsJsonObject().get(gameResultElementNameJSON).getAsJsonObject().get(playerElementName.toLowerCase()).getAsJsonObject();
            String playerId = playerWin.get(attrIdPlayerElement).getAsString();
            String playerName = playerWin.get(attrNamePlayerElement).getAsString();
            String playerSymbol = playerWin.get(attrSymbolPlayerElement).getAsString();
            System.out.printf("\nPlayer %s -> %s is winner as '%s'!", playerId, playerName, playerSymbol);
        }catch (Exception e){
            System.out.println("\nDraw!");
        }
    }
}
