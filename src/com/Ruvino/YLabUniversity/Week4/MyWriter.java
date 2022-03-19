package com.Ruvino.YLabUniversity.Week4;

import com.Ruvino.YLabUniversity.Week2.GamePlay;
import com.Ruvino.YLabUniversity.Week2.Player;

public abstract class MyWriter {

    public static String FileName = "game_%d.%s";
    public static Player winPlayer = null;
    public static int numberFile = 0;

    public static final String

            rootElementName,

            playerElementName,
            attrIdPlayerElement,
            attrNamePlayerElement,
            attrSymbolPlayerElement,

            gameElementName,

            stepElementName,
            attrNumStepElement,
            attrPlayerIdStepElement,

            gameResultElementName,
            gameResultElementNameJSON;

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
        gameResultElementNameJSON = "gameResult";
    }

    public enum ExtensionFile {xml, json}

}
