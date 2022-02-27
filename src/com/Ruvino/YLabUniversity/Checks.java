package com.Ruvino.YLabUniversity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * My class for checks validate args for Fibonacci class
 * @author      Timofei Proshunin
 * @since       1.0.1
 */

public class Checks {

    public static int correctIntNumber(int numCheck) throws IOException{

        int stopNumber = 47;

        try {
            if (numCheck >= stopNumber) throw new FibonacciException("Для типа \"int\" нельзя использовать число больше чем " + (stopNumber-1) + " из-за переполнения");
            return numCheck;
        }
        catch (FibonacciException ex){
            System.out.println(ex.getMessage());
            System.out.print("Введите другое число: ");
            int newNumOfFibonacci = Integer.parseInt(new BufferedReader(new InputStreamReader(System.in)).readLine());
            if (newNumOfFibonacci >= stopNumber) correctIntNumber(newNumOfFibonacci);
            return newNumOfFibonacci;
        }
    }

}
