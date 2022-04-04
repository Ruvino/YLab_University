package com.Ruvino.YLabUniversity._FibonacciTask1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
Версия 1 - просто обычная рекурсия
 */

public class Fibonacci_v1 {

    public static void main(String[] args) throws IOException {

        System.out.print("Введите элемент последовательности, который хотите найти: ");

        int correctNumber = Checks.correctIntNumber(Integer.parseInt(new BufferedReader(new InputStreamReader(System.in)).readLine()));

        System.out.println(fib_v1(correctNumber));

    }

    public static int fib_v1(int n){
        if(n == 0) return 0;
        else if(n == 1) return 1;
        else {
            return fib_v1(n - 1) + fib_v1(n - 2);
        }
    }
}


