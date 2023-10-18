package com.AntonDybko.ug.ap.lab01.zad4;

import java.util.Scanner;
public class Main {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter width of figure: ");
        boolean n_checker = false;
        while(n_checker == false){
            int n = scanner.nextInt();
            if( n >= 0){
                n_checker = true;
                String result = Figure.getPattern(n);
                System.out.println(result);
            }
        }
    }
}
