package com.Ruvino.YLabUniversity.Week1;

import com.Ruvino.YLabUniversity.Checks;
import com.Ruvino.YLabUniversity.FibonacciException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
Отдельный класс Фибоначи для предварительной инициализации
 */

public class FibonacciSuperClass {

    private static final int intLimit = 46;

    static int[] fibArray = new int[intLimit+1];

    public static void fillArray(){

        fibArray[0] = 0;
        fibArray[1] = 1;
        for (int i = 2; i < fibArray.length; i++) {
            fibArray[i] = fibArray[i - 1] + fibArray[i - 2];
        }
    }

    public static void getFibonacci(int n) throws FibonacciException {
        if (fibArray[intLimit] != 0) System.out.println(fibArray[n]);
       else throw new FibonacciException("Массив не инициализирован, перед вызовом метода \"FibonacciSuperClass.getFibonacci\" выполните метод \"FibonacciSuperClass.fillArray\"");
    }
}


