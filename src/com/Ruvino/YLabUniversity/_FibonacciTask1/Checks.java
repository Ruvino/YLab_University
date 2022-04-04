package com.Ruvino.YLabUniversity._FibonacciTask1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * My class for checks validate args for Fibonacci class
 * @author      Timofei Proshunin
 * @since       1.1
 */

public class Checks {

    public static int correctIntNumber(int numCheck) throws IOException{

        int stopNumber = FibonacciSuperClass.intLimit;

        try {
            if (numCheck > stopNumber) throw new FibonacciException("Для типа \"int\" нельзя использовать число больше чем " + stopNumber + " из-за переполнения");
            return numCheck;
        }
        catch (FibonacciException ex){
            System.out.println(ex.getMessage());
            System.out.print("Введите другое число: ");
            int newNumOfFibonacci = Integer.parseInt(new BufferedReader(new InputStreamReader(System.in)).readLine());
            if (newNumOfFibonacci > stopNumber) correctIntNumber(newNumOfFibonacci);
            return newNumOfFibonacci;
        }
    }

    public static int correctBigIntegerNumber(int numCheck) throws IOException{

        int stopNumber = FibonacciSuperClassBigInteger.LIMIT;

        try {
            if (numCheck > stopNumber) throw new FibonacciException("Для типа \"int\" нельзя использовать число больше чем " + stopNumber + " из-за переполнения");
            return numCheck;
        }
        catch (FibonacciException ex){
            System.out.println(ex.getMessage());
            System.out.print("Введите другое число: ");
            int newNumOfFibonacci = Integer.parseInt(new BufferedReader(new InputStreamReader(System.in)).readLine());
            if (newNumOfFibonacci > stopNumber) correctBigIntegerNumber(newNumOfFibonacci);
            return newNumOfFibonacci;
        }
    }

}
