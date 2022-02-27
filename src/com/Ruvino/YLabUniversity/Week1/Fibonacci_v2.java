package com.Ruvino.YLabUniversity.Week1;

import com.Ruvino.YLabUniversity.Checks;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 Версия 2 - с использованием цикла
 */

public class Fibonacci_v2 {

    public static void main(String[] args) throws IOException {

        System.out.print("Введите элемент последовательности, который хотите найти: ");

        int correctNumber = Checks.correctIntNumber(Integer.parseInt(new BufferedReader(new InputStreamReader(System.in)).readLine()));

        System.out.println(fib_v2(correctNumber));

    }

    public static int fib_v2(int n) {
        if(n == 0) return 0;
        else if(n == 1) return 1;

        int fibo1 = 1, fibo2 = 1, fibonacci = 1;

        for(int i = 3; i <= n; i++){
            fibonacci = fibo1 + fibo2;
            fibo1 = fibo2;
            fibo2 = fibonacci;
        }
        return fibonacci;
    }

}
