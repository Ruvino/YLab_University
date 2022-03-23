package com.Ruvino.YLabUniversity.Week4;

import com.google.gson.JsonObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import static com.Ruvino.YLabUniversity.Week4.MyWriter.*;

public interface MyReader {

    char[][] gameBoard = {
            {'_', '|', '_', '|', '_'},
            {'_', '|', '_', '|', '_'},
            {' ', '|', ' ', '|', ' '}
    };

    static void printPlayerWin(Object winPlayer) {

        if (winPlayer instanceof JsonObject){
            try {
                JsonObject playerWin = ((JsonObject) winPlayer).get(rootElementName.toLowerCase()).getAsJsonObject().get(gameResultElementNameJSON).getAsJsonObject().get(playerElementName.toLowerCase()).getAsJsonObject();
                String playerId = playerWin.get(attrIdPlayerElement).getAsString();
                String playerName = playerWin.get(attrNamePlayerElement).getAsString();
                String playerSymbol = playerWin.get(attrSymbolPlayerElement).getAsString();
                System.out.printf("\nPlayer %s -> %s is winner as '%s'!", playerId, playerName, playerSymbol);
            }catch (Exception e){
                System.out.println("\nDraw!");
            }
        }
        else if (winPlayer instanceof Document){
            Node gameResult = ((Document) winPlayer).getElementsByTagName(gameResultElementName).item(0);

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

    static String printPlayerWinRequest(Object winPlayer) {
        if (winPlayer instanceof JsonObject){
            try {
                JsonObject playerWin = ((JsonObject) winPlayer).get(rootElementName.toLowerCase()).getAsJsonObject().get(gameResultElementNameJSON).getAsJsonObject().get(playerElementName.toLowerCase()).getAsJsonObject();
                String playerId = playerWin.get(attrIdPlayerElement).getAsString();
                String playerName = playerWin.get(attrNamePlayerElement).getAsString();
                String playerSymbol = playerWin.get(attrSymbolPlayerElement).getAsString();
                return String.format("\nPlayer %s -> %s is winner as '%s'!", playerId, playerName, playerSymbol);
            }catch (Exception e){
                return "\nDraw!";
            }
        }
        else if (winPlayer instanceof Document){
            Node gameResult = ((Document) winPlayer).getElementsByTagName(gameResultElementName).item(0);

            NodeList nodes = gameResult.getChildNodes();

            if (nodes.getLength() == 1) return ("\n" + ((Element)nodes).getTextContent());
            else {
                Element playerWin = (Element) nodes.item(1);
                String playerId = playerWin.getAttribute(attrIdPlayerElement);
                String playerName = playerWin.getAttribute(attrNamePlayerElement);
                String playerSymbol = playerWin.getAttribute(attrSymbolPlayerElement);
                return String.format("\nPlayer %s -> %s is winner as '%s'!", playerId, playerName, playerSymbol);
            }
        }
        return "Невозможно прочитать файл";
    }

}
