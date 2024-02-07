package com.ug.edu.pl.ap.lab7.domain;

import jakarta.persistence.*;

@Entity
public class Category {
    private long id;
    private String name;
    private String description;
    //private Collection<Food> food;
    public Category(long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
    public Category() {}
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    /*@ManyToMany
    public Collection<Food> getFood() {
        return food;
    }

    public void setFood(Collection<Food> food) {
        this.food = food;
    }*/
}
