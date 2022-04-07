package com.Ruvino.YLabUniversity.model;

import com.Ruvino.YLabUniversity.controller.GameController;
import com.Ruvino.YLabUniversity.repository.DataBaseActions;
import com.Ruvino.YLabUniversity.services.*;
import com.Ruvino.YLabUniversity.utils.Adapter;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.servlet.MultipartConfigElement;
import javax.servlet.http.Part;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import static com.Ruvino.YLabUniversity.services.GamePlay.listOfStep;
import static com.Ruvino.YLabUniversity.utils.MyWriter.*;
import static spark.Spark.*;


public class MyRestApi {

    static Player player1, player2;
    static ArrayList<Player> players = new ArrayList<>(2);
    static boolean gameOver;
    static int numId;

    public static void main(String[] args) {

        DataBaseActions.initDB();

        staticFiles.location("/public");
        port(8080);

        // Уведомление, что сервер запущен
        System.out.println("Server started on localhost:8080");

        // Создадим первого игрока
        get("/gameplay/player1", (request, response) -> {
            String player1Name = request.queryParams("playerName1");
            System.out.println("Имя первого игрока: " + player1Name);
            player1 = new Player(player1Name);
            players.add(player1);
           return player1;
        }, new JsonTransformer());

         //Создадим второго игрока
        get("/gameplay/player2", (request, response) -> {
            String player2Name = request.queryParams("playerName2");
            System.out.println("Имя второго игрока: " + player2Name);
            player2 = new Player(player2Name, 'O');
            players.add(player2);
            return player2;
        }, new JsonTransformer());

        //Ход игрока 1
        get("/gameplay/stepPlayer1", (request, response) -> {
            int step = Integer.parseInt(request.queryParams("player1Step"));
            GamePlay.playerMove(MyReader.gameBoard, step, player1.getPlayerNumber());
            gameOver = GameController.isGameOver(gameBoard, player1);
            if (gameOver) {
                // создание файла XML
                MyXMLWriter.createLogFile(listOfStep, players);
                // создание файла JSON
                MyJSONWriter.createLogFile(listOfStep, players);
                // добавление в БД
                DataBaseActions.addFieldToDataBase(++numId, players);
                // чтение из БД для наглядности
                DataBaseActions.readDB();
                // запись в файл
                MyFileWriter.writeFile(player1, player2);

                DataBaseActions.deleteTable();

            }
            System.out.println();
            return new Step(player1.getPlayerNumber(), step);
        }, new JsonTransformer());

        //Ход игрока 2
        get("/gameplay/stepPlayer2", (request, response) -> {
            int step = Integer.parseInt(request.queryParams("player2Step"));
            GamePlay.playerMove(MyReader.gameBoard, step, player2.getPlayerNumber());
            gameOver = GameController.isGameOver(gameBoard, player2);
            if (gameOver) {
                // создание файла XML
                MyXMLWriter.createLogFile(listOfStep, players);
                // создание файла JSON
                MyJSONWriter.createLogFile(listOfStep, players);
                // добавление в БД
                DataBaseActions.addFieldToDataBase(++numId, players);
                // чтение из БД для наглядности
                DataBaseActions.readDB();
                // запись в файл
                MyFileWriter.writeFile(player1, player2);

                DataBaseActions.deleteTable();

            }
            System.out.println();
            return new Step(player2.getPlayerNumber(), step);
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
