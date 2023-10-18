package com.AntonDybko.ug.ap.lab01.zad3;

public enum Planet {
    EARTH   (31557600),
    MERCURY (EARTH.year * 0.2408467),
    VENUS   (EARTH.year * 0.61519726),
    MARS    (EARTH.year * 1.8808158),
    JUPITER (EARTH.year * 11.862615),
    SATURN  (EARTH.year * 29.447498),
    URANUS  (EARTH.year * 84.016846),
    NEPTUNE (EARTH.year * 164.79132);
    private final double year;
    Planet(double year){
        this.year = year;
    }

    public double getYear(){
        return year;
    }
}
