package com.AntonDybko.ug.ap.lab01.zad2;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        boolean actual = Armstrong.isArmstrong(9);
        boolean expected = true;
        if( actual == expected){
            System.out.println("isArmstrong method works alright");
        }else{
            System.out.println("isArmstrong method have some issues");
        }
    }
}
//Junit tests check!!!
