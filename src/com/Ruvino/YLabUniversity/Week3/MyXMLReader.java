package com.Ruvino.YLabUniversity.Week3;


import com.Ruvino.YLabUniversity.Week2.GamePlay;
import com.Ruvino.YLabUniversity.Week4.MyWriter;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class MyXMLReader extends MyWriter {

    public static void main(String[] args) {

        try {
            // parse XML file
            String FILENAME = "game_1.xml";
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new File(FILENAME));

            doc.getDocumentElement().normalize();

            // get <Step>
            NodeList list = doc.getElementsByTagName(stepElementName);

            for (int temp = 0; temp < list.getLength(); temp++) {

                Node node = list.item(temp);

                if (node.getNodeType() == Node.ELEMENT_NODE) {

                    Element element = (Element) node;

                    int playerNumber = Integer.parseInt(element.getAttribute(attrPlayerIdStepElement));

                    // get value
                    int move = Adapter.convertValue(element.getTextContent());

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

        Node gameResult = doc.getElementsByTagName(gameResultElementName).item(0);

        NodeList nodes = gameResult.getChildNodes();

        if (nodes.getLength() == 1) System.out.println("\n" + ((Element)nodes).getTextContent());
        else {
            Element playerWin = (Element) nodes.item(1);
            String playerId = playerWin.getAttribute(attrIdPlayerElement);
            String playerName = playerWin.getAttribute(attrNamePlayerElement);
            String playerSymbol = playerWin.getAttribute(attrSymbolPlayerElement);
            System.out.printf("\nPlayer %s -> %s is winner as '%s'!", playerId, playerName, playerSymbol);
        }

    }
}
