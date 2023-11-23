package com.ap.lab04.zad1.domain;

import java.time.LocalDate;
import java.util.UUID;

public class Food {
    private UUID id;
    private String name;
    private LocalDate term ;
    public Food(String name, LocalDate term) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.term = term;
    }
    public Food() {}

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getTerm() {
        return term;
    }

    public void setTerm(LocalDate term) {
        this.term = term;
    }
}
