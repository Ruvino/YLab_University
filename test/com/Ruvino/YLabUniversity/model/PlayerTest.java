package com.Ruvino.YLabUniversity.model;

import org.junit.*;
import static org.hamcrest.CoreMatchers.is;

import static org.junit.Assert.*;

public class PlayerTest {

    private Player player;

    @After
    public void tearDown() throws Exception {
        player = null;
    }

    @Test
    public void constructorTest(){
        String name = "Alex";
        player = new Player(name);
        assertThat(name, is(player.getName()));
        assertThat('X', is(player.getCharacter()));
        assertThat(1, is(player.getPlayerNumber()));

        name = "notAlex";
        player = new Player(name, 'O');
        assertThat(name, is(player.getName()));
        assertThat('O', is(player.getCharacter()));
        assertThat(2, is(player.getPlayerNumber()));
    }

}