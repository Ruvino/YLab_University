package com.Ruvino.YLabUniversity._FibonacciTask1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Версия 5 - предварительная инициализация массива с обработкой ошибок.
 */

public class Fibonacci_v5 {

    public static void main(String[] args) throws IOException, FibonacciException {

        FibonacciSuperClass.fillArray();

        System.out.print("Введите элемент последовательности, который хотите найти: ");

        int correctNumber = Checks.correctIntNumber(Integer.parseInt(new BufferedReader(new InputStreamReader(System.in)).readLine()));

        try {
            FibonacciSuperClass.getFibonacci(correctNumber);
        } catch (FibonacciException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Вы хотите выполнить процедуру инициализации массива?");

            while (true) {
                String answer = new BufferedReader(new InputStreamReader(System.in)).readLine();
                if (answer.equalsIgnoreCase("y") || answer.equalsIgnoreCase("д")) {
                    System.out.println("Инициализируем массив...");
                    FibonacciSuperClass.fillArray();
                    System.out.println("...массив готов");
                    FibonacciSuperClass.getFibonacci(correctNumber);
                    break;
                } else if (answer.equalsIgnoreCase("n") || answer.equalsIgnoreCase("н")) {
                    System.out.println("Вы вышли из программы");
                    break;
                } else System.out.println("Введён недопустимый символ, повторите попытку");
            }
        }
    }
}


