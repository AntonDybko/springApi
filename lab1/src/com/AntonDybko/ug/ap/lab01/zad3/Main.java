package com.AntonDybko.ug.ap.lab01.zad3;

import java.util.Scanner;
import java.text.DecimalFormat;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("How old are you in second?");
        int age_in_seconds = scanner.nextInt();
        System.out.println("Where do you live?");
        System.out.println("0. Earth");
        System.out.println("1. Mercury");
        System.out.println("2. Venus");
        System.out.println("3. Mars");
        System.out.println("4. Jupiter");
        System.out.println("5. Saturn");
        System.out.println("6. Uranus");
        System.out.println("7. Neptune");

        int planet_number = scanner.nextInt();
        if(planet_number < 0 || planet_number > 7){
            System.out.println("Wrong number");
        }else{
            DecimalFormat df = new DecimalFormat("#.##");
            Planet selectedPlanet = Planet.values()[planet_number];
            System.out.println(selectedPlanet.getYear());
            double age = age_in_seconds / selectedPlanet.getYear();
            System.out.println("Your age on " + selectedPlanet.name() + " is " + df.format(age) + " years.");
        }
    }
}
