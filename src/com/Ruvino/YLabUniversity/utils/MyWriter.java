package com.Ruvino.YLabUniversity.utils;

import com.Ruvino.YLabUniversity.services.MyReader;
import com.Ruvino.YLabUniversity.services.GamePlay;
import com.Ruvino.YLabUniversity.model.Player;

public abstract class MyWriter implements MyReader {

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
