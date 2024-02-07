package com.ug.edu.pl.ap.lab9.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class FoodDTO {

    private Long id;
    private String name;
    private double calories;
    private Boolean isVegetarian;
    private LocalDate expirationDate ;
    private License license;

    public FoodDTO(Long id, String name, double calories, Boolean isVegetarian, LocalDate expirationDate, License license) {
        this.id = id;
        this.name = name;
        this.calories = calories;
        this.isVegetarian = isVegetarian;
        this.expirationDate = expirationDate;
        this.license = license;
    }

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

    public double getCalories() {
        return calories;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }

    public Boolean getisVegetarian() {
        return isVegetarian;
    }

    public void setisVegetarian(Boolean vegetarian) {
        isVegetarian = vegetarian;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public License getLicense() {
        return license;
    }

    public void setLicense(License license) {
        this.license = license;
    }

    public static List<FoodDTO> listToFoodDTOWihtoutCategories(List<Food> foodList){
        return foodList
                .stream().map(f-> new FoodDTO(
                        f.getId(),
                        f.getName(),
                        f.getCalories(),
                        f.getIsVegetarian(),
                        f.getExpirationDate(),
                        f.getLicense()))
                .collect(Collectors.toList());
    }
}
