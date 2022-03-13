package com.Ruvino.YLabUniversity.Week3;

import com.Ruvino.YLabUniversity.Week2.GamePlay;
import com.Ruvino.YLabUniversity.Week2.Player;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class MyXMLWriter {

    public static String XMLFileName = "game_%d.xml";
    public static Player winPlayer = null;
    public static int numberXMLFile = 0;

    public static String

            rootElementName,

            playerElementName,
            attrIdPlayerElement,
            attrNamePlayerElement,
            attrSymbolPlayerElement,

            gameElementName,

            stepElementName,
            attrNumStepElement,
            attrPlayerIdStepElement,

            gameResultElementName;

    static {
        rootElementName = GamePlay.class.getSimpleName();

        playerElementName = Player.class.getSimpleName();
        attrIdPlayerElement = "id";
        attrNamePlayerElement = "name";
        attrSymbolPlayerElement = "symbol";

        gameElementName = "Game";

        stepElementName = "Step";
        attrNumStepElement = "num";
        attrPlayerIdStepElement = "playerId";

        gameResultElementName = "GameResult";
    }

    public static void createLofFile(LinkedHashMap<Integer, Integer> listOfStep, ArrayList<Player> players) {

        String fileName = String.format(XMLFileName, ++numberXMLFile);

        try {

            // create new XML document
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();

            // create root element
            Element rootElement = doc.createElement(rootElementName);
            doc.appendChild(rootElement);

            // create player element
            Element playerElement = doc.createElement(playerElementName);

            for (int i = 1; i <= players.size(); i++) {

                rootElement.appendChild(playerElement);

                playerElement.setAttribute(attrIdPlayerElement, String.valueOf(i));
                playerElement.setAttribute(attrNamePlayerElement, players.get(i - 1).getName());
                playerElement.setAttribute(attrSymbolPlayerElement, String.valueOf(players.get(i - 1).getCharacter()));

                playerElement = doc.createElement(playerElementName);

            }

            //create game element
            Element game = doc.createElement(gameElementName);
            rootElement.appendChild(game);


            //create step element
            Element step = doc.createElement(stepElementName);

            ArrayList<Integer> move = new ArrayList<>(listOfStep.keySet());
            ArrayList<Integer> idPlayers = new ArrayList<>(listOfStep.values());

            for (int i = 1; i <= listOfStep.size(); i++) {

                game.appendChild(step);

                step.setAttribute(attrNumStepElement, String.valueOf(i));
                step.setAttribute(attrPlayerIdStepElement, String.valueOf(idPlayers.get(i - 1)));
                step.setTextContent(String.valueOf(move.get(i - 1)));

                step = doc.createElement(stepElementName);

            }

            listOfStep.clear();

            //create gameResult element
            Element gameResultElement = doc.createElement(gameResultElementName);
            rootElement.appendChild(gameResultElement);

            //create playerWin element
            if (winPlayer != null) {
                Element playerWinElement = doc.createElement(playerElementName);
                gameResultElement.appendChild(playerWinElement);
                playerWinElement.setAttribute(attrIdPlayerElement, String.valueOf(winPlayer.getPlayerNumber()));
                playerWinElement.setAttribute(attrNamePlayerElement, winPlayer.getName());
                playerWinElement.setAttribute(attrSymbolPlayerElement, String.valueOf(winPlayer.getCharacter()));
                winPlayer = null;
            } else gameResultElement.setTextContent("Draw!");

            //write the content into xml file
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            DOMSource source = new DOMSource(doc);

            StreamResult result = new StreamResult(new File(fileName));
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(source, result);

        } catch (ParserConfigurationException | TransformerException e) {
            e.printStackTrace();
        }
    }
}
