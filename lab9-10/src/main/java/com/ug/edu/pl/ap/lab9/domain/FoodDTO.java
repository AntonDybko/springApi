package com.ug.edu.pl.ap.lab9.domain;

public class FoodDTO {

    private Long id;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public FoodDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
