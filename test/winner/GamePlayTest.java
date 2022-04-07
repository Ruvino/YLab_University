package winner;

import com.Ruvino.YLabUniversity.controller.GameController;
import com.Ruvino.YLabUniversity.model.Player;
import com.Ruvino.YLabUniversity.services.GamePlay;
import com.Ruvino.YLabUniversity.services.MyReader;
import com.Ruvino.YLabUniversity.services.MyXMLWriter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class GamePlayTest {

    ArrayList<Integer> listOfStepPlayer1, listOfStepPLayer2;
    char[][] gameBord;
    Player _winPlayer;
    private Player[] players = new Player[2];

    @Before
    public void setupWinnerCombination() {
        listOfStepPlayer1 = new ArrayList<>(3);
        listOfStepPlayer1.add(1);
        listOfStepPlayer1.add(4);
        listOfStepPlayer1.add(7);
    }

    @Before
    public void setupLoserCombination() {
        listOfStepPLayer2 = new ArrayList<>(3);
        listOfStepPLayer2.add(5);
        listOfStepPLayer2.add(6);
        listOfStepPLayer2.add(9);
    }

    @Before
    public void setUpPlayers() {
        players[0] = new Player("Alex");
        players[1] = new Player("NotAlex", 'O');
    }

    @Before
    public void setUpGameBoard() {
        gameBord = MyReader.gameBoard;
    }

    @After
    public void tearDown() {
        players = null;
        listOfStepPlayer1 = null;
        listOfStepPLayer2 = null;
        _winPlayer = null;
    }

    @Test
    public void winnersTest() {

        Player expected = players[0];

        for (int i = 0; i <= 2; i++) {
            GamePlay.playerMove(gameBord, listOfStepPlayer1.get(i), 1);
            System.out.println();
        }

        GameController.isGameOver(gameBord, players[0]);

        _winPlayer = MyXMLWriter.winPlayer;
        assertEquals(expected, _winPlayer);

    }


}