package com.Ruvino.YLabUniversity.model;

import com.Ruvino.YLabUniversity.services.GamePlay;
import com.Ruvino.YLabUniversity.utils.Adapter;
import com.Ruvino.YLabUniversity.services.MyReader;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import spark.Spark;

import javax.servlet.MultipartConfigElement;
import javax.servlet.http.Part;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

import static com.Ruvino.YLabUniversity.utils.MyWriter.*;
import static spark.Spark.*;


public class MyRestApi {

    public static void main(String[] args) {

        staticFiles.location("/public");
        port(8080);

        // Уведомление, что сервер запущен
        System.out.println("Server started on localhost:8080");

        // Создадим первого игрока
        Spark.get("/gameplay/player1", (request, response) -> {
            String player1Name = request.queryParams("playerName1");
            System.out.println("Имя первого игрока: " + player1Name);
           return new Player(player1Name);
        }, new JsonTransformer());

         //Создадим второго игрока
        get("/gameplay/player2", (request, response) -> {
            String player2Name = request.queryParams("playerName2");
            System.out.println("Имя второго игрока: " + player2Name);
            return new Player(player2Name, 'O');
        }, new JsonTransformer());

        //Ход игрока 1
        get("/gameplay/stepPlayer1", (request, response) -> {
            int step = Integer.parseInt(request.queryParams("player1Step"));
            GamePlay.playerMove(MyReader.gameBoard, step, 1);
            System.out.println();
            return new Step(1, step);
        }, new JsonTransformer());

        //Ход игрока 2
        get("/gameplay/stepPlayer2", (request, response) -> {
            int step = Integer.parseInt(request.queryParams("player2Step"));
            GamePlay.playerMove(MyReader.gameBoard, step, 2);
            System.out.println();
            return new Step(2, step);
        }, new JsonTransformer());

        //Загрузка файла игры
        post("/api/upload", (req, res) -> {
            req.attribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement((String) null));
            Part filePart = req.raw().getPart("myFile");

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
