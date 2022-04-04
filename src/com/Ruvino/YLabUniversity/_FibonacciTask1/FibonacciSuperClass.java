package com.Ruvino.YLabUniversity._FibonacciTask1;

/**
 * Отдельный класс Фибоначи для предварительной инициализации
 */

public class FibonacciSuperClass {

    public static final int intLimit = 46;

    static int[] fibArray = new int[intLimit+1];

    public static void fillArray() {
        fibArray[0] = 0;
        fibArray[1] = 1;
        for (int i = 2; i < fibArray.length; i++) {
            fibArray[i] = fibArray[i - 1] + fibArray[i - 2];
        }
    }

    public static void getFibonacci(int n) throws FibonacciException {
        if (fibArray[intLimit] != 0) System.out.println(fibArray[n]);
        else
            throw new FibonacciException("Массив не инициализирован, перед вызовом метода \"FibonacciSuperClass.getFibonacci(int n)\" выполните метод \"FibonacciSuperClass.fillArray()\"");
    }
}


