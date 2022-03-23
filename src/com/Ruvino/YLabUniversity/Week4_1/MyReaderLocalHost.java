package com.Ruvino.YLabUniversity.Week4_1;

import com.Ruvino.YLabUniversity.Week2.GamePlay;
import com.Ruvino.YLabUniversity.Week3.Adapter;
import com.Ruvino.YLabUniversity.Week4.MyReader;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.servlet.MultipartConfigElement;
import javax.servlet.http.Part;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

import static com.Ruvino.YLabUniversity.Week4.MyWriter.*;
import static spark.Spark.post;
import static spark.Spark.staticFiles;

public class MyReaderLocalHost {

    public static void main(String[] argv) {
        staticFiles.location("/public");

        post("/api/upload", (req, res) -> {
            req.attribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement((String) null));
            Part filePart = req.raw().getPart("myfile");

            // create a reader
            Reader reader = Files.newBufferedReader(Paths.get(filePart.getSubmittedFileName()));

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

            //close reader
            reader.close();

            return MyReader.printPlayerWinRequest(parser);

        });
    }

}
