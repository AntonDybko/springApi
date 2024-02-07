package com.ap.lab04.zad1.domain;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import java.util.UUID;
import jakarta.validation.constraints.*;

public class Food {
    //@NotNull(message = "Field id cannot be empty")
    private UUID id;
    //private final UUID id;
    @Pattern(regexp = "^[a-zA-Z, ]+$", message = "Wrong name format")
    private String name;
    @Min(value = 0, message = "The number of calories must be greater than 0")
    private double calories;
    @NotNull(message = "Field isVegetarian cannot be empty")
    private Boolean isVegetarian;
    @NotNull(message = "Field expirationDate cannot be empty")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @FutureOrPresent(message = "The expiration date must be future or today")
    private LocalDate expirationDate ;

    public Boolean getIsVegetarian() {
        return isVegetarian;
    }

    public void setIsVegetarian(Boolean isVegetarian) {
        this.isVegetarian = isVegetarian;
    }

    public double getCalories() {
        return calories;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }

    public Food(String name, double calories, Boolean isVegetarian, LocalDate expirationDate) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.calories = calories;
        this.isVegetarian = isVegetarian;
        this.expirationDate = expirationDate;
    }
    public Food() {}

    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }
}
