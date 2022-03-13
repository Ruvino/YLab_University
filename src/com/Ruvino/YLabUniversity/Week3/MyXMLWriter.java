package com.Ruvino.YLabUniversity.Week3;

import com.Ruvino.YLabUniversity.Week2.GamePlay;
import com.Ruvino.YLabUniversity.Week2.Player;
import com.Ruvino.YLabUniversity.Week2.StartGame;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Map;

public class MyXMLWriter {

    public static void createTemplate(Player[] players, int numberXMLFile, String rootElementName, String playerElementName) {

        try {

            // create new XML document
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();

            // create root element
            Element rootElement = doc.createElement(rootElementName);
            doc.appendChild(rootElement);

            // create player element
            Element playerElement = doc.createElement(playerElementName);

            for (int i = 1; i <= players.length; i++) {

                rootElement.appendChild(playerElement);

                playerElement.setAttribute("id", String.valueOf(i));
                playerElement.setAttribute("name", players[i - 1].getName());
                playerElement.setAttribute("symbol", String.valueOf(players[i - 1].getCharacter()));

                playerElement = doc.createElement(playerElementName);

            }

            //create game elements
            Element game = doc.createElement("Game");
            rootElement.appendChild(game);

            //create gameResult elements
            Element gameResult = doc.createElement("GameResult");
            rootElement.appendChild(gameResult);

            //write the content into xml file
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            DOMSource source = new DOMSource(doc);

            StreamResult result = new StreamResult(new File(StartGame.XMLFileName+ numberXMLFile +".xml"));
            transformer.transform(source, result);

        } catch (ParserConfigurationException | TransformerException e) {
            e.printStackTrace();
        }
    }

    public static void updateSteps(String FILENAME, Map<Integer, Integer> listOfStep, int numberXMLFile) {

        ArrayList<Integer> move = new ArrayList<>(listOfStep.keySet());
        ArrayList<Integer> idPlayers = new ArrayList<>(listOfStep.values());

        try (InputStream is = new FileInputStream(FILENAME)) {

            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(is);

            NodeList listOfGame = doc.getElementsByTagName("Game");

            for (int i = 0; i < listOfGame.getLength(); i++) {
                
                // get first staff
                Node game = listOfGame.item(i);

                //create step elements
                Element step = doc.createElement("Step");

                for (int j = 1; j <= listOfStep.size(); j++) {

                    game.appendChild(step);

                    step.setAttribute("num", String.valueOf(j));
                    step.setAttribute("playerId", String.valueOf(idPlayers.get(j-1)));
                    step.setTextContent(String.valueOf(move.get(j-1)));

                    step = doc.createElement("Step");

                }

            }

            listOfStep.clear();

            //write the content into xml file
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            DOMSource source = new DOMSource(doc);

            StreamResult result = new StreamResult(new File(StartGame.XMLFileName+ numberXMLFile +".xml"));
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(source, result);
        } catch (IOException | ParserConfigurationException | TransformerException | SAXException e) {
            e.printStackTrace();
        }

    }

    public static void updateGameResult(String FILENAME, Player winPlayer, int numberXMLFile, String winText) {

        try (InputStream is = new FileInputStream(FILENAME)) {

            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(is);

            Node gameResult = doc.getElementsByTagName("GameResult").item(0);

            if (winText.equalsIgnoreCase("Ничья")) {
                gameResult.setTextContent("Draw!");
            }
            else {
                Element player = doc.createElement(Player.class.getSimpleName());

                gameResult.appendChild(player);

                player.setAttribute("id", String.valueOf(winPlayer.getPlayerNumber()));
                player.setAttribute("name", winPlayer.getName());
                player.setAttribute("symbol", String.valueOf(winPlayer.getCharacter()));
            }

            //write the content into xml file
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            DOMSource source = new DOMSource(doc);

            StreamResult result = new StreamResult(new File(StartGame.XMLFileName+numberXMLFile+".xml"));
            transformer.transform(source, result);
        } catch (IOException | ParserConfigurationException | TransformerException | SAXException e) {
            e.printStackTrace();
        }
    }

}
