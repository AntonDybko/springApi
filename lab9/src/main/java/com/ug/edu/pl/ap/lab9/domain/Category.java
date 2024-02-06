package com.ug.edu.pl.ap.lab9.domain;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Category {
    private Long id;
    private String name;
    private String description;
    private List<Food> food;
    public Category(String name, String description) {
        this.name = name;
        this.description = description;
    }
    public Category() {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
                ", description='" + food + '\'' +
                '}';
    }

    @ManyToMany(fetch = FetchType.LAZY) //tutaj??
    public List<Food> getFood() {
        return food;
    }

    public void setFood(List<Food> food) {
        this.food = food;
    }
}
