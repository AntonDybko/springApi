package com.ug.edu.pl.ap.lab9.domain;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.*;

@Entity
public class Food {
    private Long id;
    //private final UUID id;
    //@Column(unique = true)
    @Pattern(regexp = "^[a-zA-Z, ]+$", message = "Wrong name format")
    private String name;
    @Min(value = 0, message = "The number of calories must be greater than 0")
    private double calories;
    @NotNull(message = "Field isVegetarian cannot be empty")
    private Boolean isVegetarian;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @NotNull(message = "Field expirationDate cannot be empty")
    @FutureOrPresent(message = "The expiration date must be future or today")
    private LocalDate expirationDate ;
    private License license;
    private List<Category> categories;
    private Shop shop;

    public Food(String name, double calories, Boolean isVegetarian, LocalDate expirationDate) {
        this.name = name;
        this.calories = calories;
        this.isVegetarian = isVegetarian;
        this.expirationDate = expirationDate;
    }
    public Food() {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

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

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToOne(mappedBy = "food", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public License getLicense() {
        return license;
    }

    public void setLicense(License license) {
        this.license = license;
    }
    @ManyToMany(mappedBy = "food", cascade = { CascadeType.MERGE, CascadeType.PERSIST }, fetch = FetchType.LAZY)
    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", calories='" + calories + '\'' +
                ", isVegetarian='" + isVegetarian + '\'' +
                ", expirationDate='" + expirationDate + '\'' +
                ", license='" + license + '\'' +
                ", categories='" + categories + '\'' +
                ", shop='" + shop + '\'' +
                '}';
    }
}
