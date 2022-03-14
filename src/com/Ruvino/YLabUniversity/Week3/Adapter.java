package com.Ruvino.YLabUniversity.Week3;

public class Adapter {

    public static int convertValue(String value) {

        String _value = value.replaceAll("\\s", "");
        _value = _value.replaceAll("\\D+", "");

        return switch (_value) {
            case "1", "13" -> 1;
            case "2", "23" -> 2;
            case "3", "33" -> 3;
            case "4", "12" -> 4;
            case "5", "22" -> 5;
            case "6", "32" -> 6;
            case "7", "31" -> 7;
            case "8", "21" -> 8;
            case "9", "11" -> 9;
            default -> 0;
        };
    }
}

