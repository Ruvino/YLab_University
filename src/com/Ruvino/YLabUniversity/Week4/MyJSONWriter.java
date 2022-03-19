package com.Ruvino.YLabUniversity.Week4;

import com.Ruvino.YLabUniversity.Week2.Player;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

public class MyJSONWriter extends MyWriter {

    public static void createLogFile(LinkedHashMap<Integer, Integer> listOfStep, ArrayList<Player> players) {

        String fileName = String.format(FileName, numberFile, ExtensionFile.json);

        try {
            BufferedWriter writer = Files.newBufferedWriter(Paths.get(fileName));

            // create var tag
            Map<String, Object> rootTag = new LinkedHashMap<>();
            Map<String, Object> playerTag = new LinkedHashMap<>();
            Map<String, Object> gameTag = new LinkedHashMap<>();
            Map<String, Object> gameResultTag = new LinkedHashMap<>();

            // add players
            Object[] arrPlayers = new Object[players.size()];

            for (int i = 1; i <= players.size(); i++) {
                Map<String, Object> player = new LinkedHashMap<>();
                player.put(attrIdPlayerElement, players.get(i - 1).getPlayerNumber());
                player.put(attrNamePlayerElement, players.get(i - 1).getName());
                player.put(attrSymbolPlayerElement, players.get(i - 1).getCharacter());
                arrPlayers[i - 1] = player;
            }

            // add steps
            Object[] arrSteps = new Object[listOfStep.size()];

            ArrayList<Integer> move = new ArrayList<>(listOfStep.keySet());
            ArrayList<Integer> idPlayers = new ArrayList<>(listOfStep.values());

            for (int i = 1; i <= listOfStep.size(); i++) {
                Map<String, Object> steps = new LinkedHashMap<>();
                steps.put(attrNumStepElement, i);
                steps.put(attrPlayerIdStepElement, idPlayers.get(i - 1));
                steps.put("value", move.get(i - 1));
                arrSteps[i - 1] = steps;
            }

            listOfStep.clear();

            var addPlayer = playerTag.put(playerElementName.toLowerCase(), Arrays.asList(arrPlayers));

            rootTag.put(rootElementName.toLowerCase(), playerTag);
            rootTag.put(playerElementName.toLowerCase(), addPlayer);

            playerTag.put(gameElementName.toLowerCase(), gameTag);
            gameTag.put(stepElementName.toLowerCase(), Arrays.asList(arrSteps));

            if (winPlayer != null) {
                playerTag.put(gameResultElementNameJSON, gameResultTag);
                gameResultTag.put(playerElementName.toLowerCase(), arrPlayers[winPlayer.getPlayerNumber() - 1]);
            } else playerTag.put(gameResultElementNameJSON, "Draw!");

            winPlayer = null;

            // create Gson instance
            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            // write JSON to file
            writer.write(gson.toJson(rootTag));

            //close the writer
            writer.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
