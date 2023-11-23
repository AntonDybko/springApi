package com.ap.lab04.zad1.domain;

import java.util.Date;
import java.util.UUID;

public class Food {
    private UUID id;
    private String name;
    private Date term ;
    public Food(String name, Date term) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.term = term;
    }
    public Food() {}
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getTerm() {
        return term;
    }

    public void setTerm(Date term) {
        this.term = term;
    }
}
