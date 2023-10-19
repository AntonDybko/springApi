package com.AntonDybko.ug.ap.lab01.zad4;

import java.util.Scanner;
public class Main {
    public static void main(String[] args){
        String result = Figure.getPattern(5, "$");
        System.out.println(result);

        String result2 = Figure.getPattern(5);
        System.out.println(result2);
    }
}
