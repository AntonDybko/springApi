package com.AntonDybko.ug.ap.lab01.zad4;

public class Figure {
    private final static String DEFAULT = "*";
    public static String getPattern(int n) {
        StringBuilder result = new StringBuilder();
        for( int i  = 0; i < n; i++){
            for( int j = 0; j < i+1; j++){
                result.append(DEFAULT);
            }
            result.append("\n");
        }
        for( int i  = 0; i < n; i++){
            for( int j  = n-i; j > 0; j--){
                result.append(DEFAULT);
            }
            result.append("\n");
        }
        for( int i  = 0; i < n; i++){
            for( int j  = n-i; j <n ; j++){
                result.append(" ");
            }
            for( int j  = n-i; j > 0; j--){
                result.append(DEFAULT);
            }
            result.append("\n");
        }
        for( int i  = 0; i < n; i++){
            for( int j =n-i-1; j > 0; j--){
                result.append(" ");
            }
            for( int j  = i+1; j > 0; j--){
                result.append(DEFAULT);
            }
            result.append("\n");
        }
        return result.toString();
    }
}
