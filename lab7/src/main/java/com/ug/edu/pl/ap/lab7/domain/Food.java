package com.ug.edu.pl.ap.lab7.domain;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import jakarta.persistence.*;

@Entity
public class Food {
    //Shop - OneToMany - Food
    //Category - ManyToMany - Food
    //License - OneToOne - Food
    //@NotNull(message = "Field id cannot be empty")
    private long id;
    //private final UUID id;
    @Pattern(regexp = "^[a-zA-Z, ]+$", message = "Wrong name format")
    @NotNull(message = "Field name cannot be empty")
    private String name;
    @Min(value = 0, message = "The number of calories must be greater than 0")
    private double calories;
    @NotNull(message = "Field isVegetarian cannot be empty")
    private Boolean isVegetarian;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @NotNull(message = "Field expirationDate cannot be empty")
    @FutureOrPresent(message = "The expiration date must be future or today")
    private LocalDate expirationDate ;
    //private License license;
    //private Collection<Category> categories;
    //private Shop shop;

    //License license, Collection<Category> categories, Shop shop
    public Food(String name, double calories, Boolean isVegetarian, LocalDate expirationDate) {
        this.id = id;
        this.name = name;
        this.calories = calories;
        this.isVegetarian = isVegetarian;
        this.expirationDate = expirationDate;
        //this.license = license;
        //this.categories = categories;
        //this.shop = shop;
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

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", calories='" + calories + '\'' +
                ", isVegetarian='" + isVegetarian + '\'' +
                ", expirationDate='" + expirationDate + '\'' +
                '}';
    }

    /*@OneToOne(mappedBy = "food")
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
    }*/
}
