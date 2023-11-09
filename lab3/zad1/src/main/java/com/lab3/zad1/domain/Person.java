package com.lab3.zad1.domain;

import java.util.UUID;

public class Person {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private int birthYear;

    public Person(String id, String firstName, String lastName, String email, int birthYear) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.birthYear = birthYear;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", birthYear=" + birthYear +
                '}';
    }
}
