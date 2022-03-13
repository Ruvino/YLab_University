package com.Ruvino.YLabUniversity.Week3;


import com.Ruvino.YLabUniversity.Week2.GamePlay;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class MyXMLReader {

    private static final String FILENAME = "game_1.xml";

    public static void main(String[] args) {

        char[][] gameBoard = {
                {'_', '|', '_', '|', '_'},
                {'_', '|', '_', '|', '_'},
                {' ', '|', ' ', '|', ' '}
        };

        try {
            // parse XML file
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new File(FILENAME));

            doc.getDocumentElement().normalize();

            // get <Step>
            NodeList list = doc.getElementsByTagName(MyXMLWriter.stepElementName);

            for (int temp = 0; temp < list.getLength(); temp++) {

                Node node = list.item(temp);

                if (node.getNodeType() == Node.ELEMENT_NODE) {

                    Element element = (Element) node;

                    int playerNumber = Integer.parseInt(element.getAttribute(MyXMLWriter.attrPlayerIdStepElement));

                    // get value
                    int move = Integer.parseInt(element.getTextContent());

                    System.out.println();
                    GamePlay.playerMove(gameBoard, move, playerNumber);

                }
            }

            printPlayerWin(doc);

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }

    private static void printPlayerWin(Document doc) {

        Node gameResult = doc.getElementsByTagName(MyXMLWriter.gameResultElementName).item(0);

        NodeList nodes = gameResult.getChildNodes();

        if (nodes.getLength() == 1){
            Element node = (Element) nodes;
            System.out.println("\n" + node.getTextContent());
        }
        else {
            Element playerWin = (Element) nodes.item(1);
            String playerId = playerWin.getAttribute(MyXMLWriter.attrIdPlayerElement);
            String playerName = playerWin.getAttribute(MyXMLWriter.attrNamePlayerElement);
            String playerSymbol = playerWin.getAttribute(MyXMLWriter.attrSymbolPlayerElement);
            System.out.printf("\nPlayer %s -> %s is winner as '%s'!", playerId, playerName, playerSymbol);
        }

    }
}
