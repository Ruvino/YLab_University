package com.Ruvino.YLabUniversity.Week5;

public class Step {

    static int countStep = 0;

    private int num;
    private int playerId;
    private int value;

    public Step(int playerId, int value) {
        num = ++countStep;
        this.playerId = playerId;
        this.value = value;
    }

    public int getNum() {
        return num;
    }

    public int getPlayerId() {
        return playerId;
    }

    public int getValue() {
        return value;
    }
}
