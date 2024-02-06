package com.ug.edu.pl.ap.lab9.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
public class Shop {
    private long id;
    @Pattern(regexp = "^[a-zA-Z, ]+$", message = "Wrong name format")
    @NotNull(message = "Field name cannot be empty")
    private String name;
    @Pattern(regexp = "^(\\d+\\s)?([\\w\\s,.#-]+),\\s*([\\w\\s-]+),\\s*([A-Za-z]+),\\s*(\\d{5})(?:-(\\d{4}))?$"
            , message = "Please enter a valid address in the following format:\n" +
            "[Street Address], [City], [State], [ZIP Code]\n" +
            "Optional: [Apartment/Unit], [Extended ZIP Code]\n" +
            "Example: 123 Main St, Cityville, CA, 12345\n" +
            "Optional: Apt 4, 6789\n")
    @NotNull(message = "Field address cannot be empty")
    private String address;
    //456 Broad Ave, Townsville, AnotherState, 54321
    @Min(value = 0, message = "The number of calories must be greater than 0")
    private BigDecimal revenue;
    @NotNull(message = "Opening time cannot be null")
    private LocalTime openingTime;
    @NotNull(message = "Closing time cannot be null")
    private LocalTime closingTime;
    private List<Food> food;
    public Shop(String name, String address, BigDecimal revenue, LocalTime openingTime, LocalTime closingTime) {
        this.name = name;
        this.address = address;
        this.revenue = revenue;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
        //this.food = Collections.synchronizedList(new ArrayList<>());
        this.food = new ArrayList<>();
        System.out.println("food" + this.food);
    }
    public Shop() {}

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String location) {
        this.address = location;
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

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", location='" + address + '\'' +
                ", revenue='" + revenue + '\'' +
                ", openingTime='" + openingTime + '\'' +
                ", closingTime='" + closingTime + '\'' +
                ", food='" + food + '\'' +
                '}';
    }


    @OneToMany(mappedBy = "shop", cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
    public List<Food> getFood() {
        return food;
    }

    public void setFood(List<Food> food) {
        this.food = food;
        //this.food = Collections.synchronizedList(new ArrayList<>(food));
    }

    /*public void setFood(Food food) {
        //System.out.println();
        this.food.add(food);
    }*/
}
