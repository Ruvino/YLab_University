package com.Ruvino.YLabUniversity.Week1;

import com.Ruvino.YLabUniversity.FibonacciException;

import java.math.BigInteger;

/**
 * Отдельный класс Фибоначи для предварительной инициализации
 */

public class FibonacciSuperClassBigInteger {

    public static final int LIMIT = 1_000;

    static BigInteger[] fibArray = new BigInteger[LIMIT + 1];

    public static void fillArray() {
        fibArray[0] = BigInteger.valueOf(0);
        fibArray[1] = BigInteger.valueOf(1);
        for (int i = 2; i < fibArray.length; i++) {
            fibArray[i] = fibArray[i - 1].add(fibArray[i - 2]);
        }
    }

    public static void getFibonacci(int n) throws FibonacciException {
        if (fibArray[LIMIT] != null) System.out.println(fibArray[n]);
        else
            throw new FibonacciException("Массив не инициализирован, перед вызовом метода \"FibonacciSuperClass.getFibonacci(int n)\" выполните метод \"FibonacciSuperClass.fillArray()\"");
    }
}


