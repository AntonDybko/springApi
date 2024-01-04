package com.ug.edu.pl.ap.lab6.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;

@Entity
public class Shop {
    private long id;
    private String name;
    private String location;
    private BigDecimal revenue;
    private LocalTime openingTime;
    private LocalTime closingTime;
    private Collection<Food> food;
    public Shop(long id, String name, String location, BigDecimal revenue, LocalTime openingTime, LocalTime closingTime) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.revenue = revenue;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
    }
    public Shop() {}
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public BigDecimal getRevenue() {
        return revenue;
    }

    public void setRevenue(BigDecimal revenue) {
        this.revenue = revenue;
    }

    public LocalTime getOpeningTime() {
        return openingTime;
    }

    public void setOpeningTime(LocalTime openingTime) {
        this.openingTime = openingTime;
    }

    public LocalTime getClosingTime() {
        return closingTime;
    }

    public void setClosingTime(LocalTime closingTime) {
        this.closingTime = closingTime;
    }

    @OneToMany(mappedBy = "shop")
    public Collection<Food> getFood() {
        return food;
    }

    public void setFood(Collection<Food> food) {
        this.food = food;
    }
}
