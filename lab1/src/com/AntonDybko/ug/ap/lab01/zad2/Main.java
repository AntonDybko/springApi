package com.AntonDybko.ug.ap.lab01.zad2;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a number for Armstrong test:");
        int number = scanner.nextInt();
        boolean result = Zad2.isArmstrong(number);
        if( result == true){
            System.out.println("This is Armstrong's number.");
        }else{
            System.out.println("This is not Armstrong's number.");
        }
    }
}
