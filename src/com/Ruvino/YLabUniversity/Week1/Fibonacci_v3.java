package com.Ruvino.YLabUniversity.Week1;

import com.Ruvino.YLabUniversity.Checks;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 Версия 3 - формула Бине
 */

public class Fibonacci_v3 {

    public static void main(String[] args) throws IOException {

        System.out.print("Введите элемент последовательности, который хотите найти: ");

        int correctNumber = Checks.correctIntNumber(Integer.parseInt(new BufferedReader(new InputStreamReader(System.in)).readLine()));

        System.out.println(fib_v3(correctNumber));

    }

    public static int fib_v3(int n) {
        double squareRootOf5 = Math.sqrt(5);
        double phi = (1 + squareRootOf5)/2;
        int nthTerm = (int) ((Math.pow(phi, n) - Math.pow(-phi, -n))/squareRootOf5);
        return nthTerm;
    }
}


