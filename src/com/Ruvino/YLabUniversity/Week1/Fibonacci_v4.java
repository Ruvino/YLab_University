package com.Ruvino.YLabUniversity.Week1;

import com.Ruvino.YLabUniversity.Checks;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 Версия 4 - возврат используемых значений через массив.
 */

public class Fibonacci_v4 {

    public static int fibArray[];

    public static void main(String[] args) throws IOException {

        System.out.print("Введите элемент последовательности, который хотите найти: ");

        int correctNumber = Checks.correctIntNumber(Integer.parseInt(new BufferedReader(new InputStreamReader(System.in)).readLine()));

        fibArray = new int[correctNumber + 1];

        System.out.println(fib_v4(correctNumber));

    }

    public static int fib_v4(int n){

        int fibValue = 0;

        if(n == 0) return 0;
        else if(n == 1) return 1;
        else if(fibArray[n] != 0) return fibArray[n];
        else{
            fibValue = fib_v4(n-1) + fib_v4(n-2);
            fibArray[n]=fibValue;
            return fibValue;
        }
    }
}


