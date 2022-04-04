package com.Ruvino.YLabUniversity.model;

public class Player {

    private final String name;
    private char character = 'X';
    private int playerNumber = 1;

    public Player(String name) {
        this.name = name;
    }

    public Player(String name, char character) {
        this(name);
        this.character = character;
        playerNumber = 2;
    }

    public char getCharacter() {
        return character;
    }

    public String getName() {
        return name;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }
}
