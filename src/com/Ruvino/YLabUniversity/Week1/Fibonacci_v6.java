package com.Ruvino.YLabUniversity.Week1;

import com.Ruvino.YLabUniversity.Checks;
import com.Ruvino.YLabUniversity.FibonacciException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Версия 6 - предварительная инициализация массива с обработкой ошибок c Типом BigInteger.
 */

public class Fibonacci_v6 {

    public static void main(String[] args) throws IOException, FibonacciException {

        FibonacciSuperClassBigInteger.fillArray();

        System.out.print("Введите элемент последовательности, который хотите найти: ");

        int correctNumber = Checks.correctBigIntegerNumber(Integer.parseInt(new BufferedReader(new InputStreamReader(System.in)).readLine()));

        try {
            FibonacciSuperClassBigInteger.getFibonacci(correctNumber);
        } catch (FibonacciException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Вы хотите выполнить процедуру инициализации массива?");

            while (true) {
                String answer = new BufferedReader(new InputStreamReader(System.in)).readLine();
                if (answer.equalsIgnoreCase("y") || answer.equalsIgnoreCase("д")) {
                    System.out.println("Инициализируем массив...");
                    FibonacciSuperClassBigInteger.fillArray();
                    System.out.println("...массив готов");
                    FibonacciSuperClassBigInteger.getFibonacci(correctNumber);
                    break;
                } else if (answer.equalsIgnoreCase("n") || answer.equalsIgnoreCase("н")) {
                    System.out.println("Вы вышли из программы");
                    break;
                } else System.out.println("Введён недопустимый символ, повторите попытку");
            }
        }
    }
}


