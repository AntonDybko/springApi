package com.AntonDybko.ug.ap.lab01.zad3;

import java.text.DecimalFormat;

public class Main {

    public static void main(String[] args) {
        DecimalFormat df = new DecimalFormat("#.##");
        int age = 100000000;
        for(Planet p : Planet.values()){
            System.out.println("Your age on " + p.name() + " is " + df.format(p.countAge(age)) + " years.");
        }
    }
}
