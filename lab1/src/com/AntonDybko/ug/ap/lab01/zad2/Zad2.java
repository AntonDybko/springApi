package com.AntonDybko.ug.ap.lab01.zad2;

import java.util.Scanner;

public class Zad2 {
    public static boolean isArmstrong(int x){
        char[] figures = Integer.toString(x).toCharArray();
        int result = 0;
        for( int i = 0; i < figures.length; i++){
            result += Math.pow(figures[i] - '0', figures.length);
        }

        if( x == result){
            return true;
        }else{
            return false;
        }
    }

}
