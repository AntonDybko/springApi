package com.ug.edu.pl.ap.lab6.domain;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import jakarta.persistence.*;

import java.util.Collection;
import java.util.UUID;

@Entity
public class Food {
    //Shop - OneToMany - Food
    //Category - ManyToMany - Food
    //License - OneToOne - Food
    //@NotNull(message = "Field id cannot be empty")
    private long id;
    //private final UUID id;
    private String name;
    private double calories;
    private Boolean isVegetarian;
    private License license;
    private Collection<Category> categories;
    private Shop shop;

    public Food(long id, String name, double calories, Boolean isVegetarian, LocalDate expirationDate, License license, Collection<Category> categories, Shop shop) {
        this.id = id;
        this.name = name;
        this.calories = calories;
        this.isVegetarian = isVegetarian;
        this.expirationDate = expirationDate;
        this.license = license;
        this.categories = categories;
        this.shop = shop;
    }
    public Food() {}
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
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
    @OneToOne(mappedBy = "food")
    public License getLicense() {
        return license;
    }

    public void setLicense(License license) {
        this.license = license;
    }
    @ManyToMany(mappedBy = "food")
    public Collection<Category> getCategories() {
        return categories;
    }

    public void setCategories(Collection<Category> categories) {
        this.categories = categories;
    }
    @ManyToOne
    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }
}
