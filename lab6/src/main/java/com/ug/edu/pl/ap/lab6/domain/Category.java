package com.ug.edu.pl.ap.lab6.domain;

import jakarta.persistence.ManyToMany;

import java.util.Collection;

public class Category {
    private long id;
    private String name;
    private String description;
    private Collection<Food> food;
    public Category(long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
    public Category() {}
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @ManyToMany(mappedBy = "categories")
    public Collection<Food> getFood() {
        return food;
    }

    public void setFood(Collection<Food> food) {
        this.food = food;
    }
}
