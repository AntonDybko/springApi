package com.ug.edu.pl.ap.lab7.domain;

import com.ug.edu.pl.ap.lab7.validation.ClosingTimeAfterOpeningTime;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.math.BigDecimal;
import java.time.LocalTime;

@Entity
@ClosingTimeAfterOpeningTime
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
    //"456 Broad Ave, Townsville, AnotherState, 54321"
    //456 Broad Ave, Townsville, AnotherState, 54321
    @Min(value = 0, message = "The number of calories must be greater than 0")
    private BigDecimal revenue;
    @NotNull(message = "Opening time cannot be null")
    private LocalTime openingTime;
    @NotNull(message = "Closing time cannot be null")
    private LocalTime closingTime;
    //private Collection<Food> food;
    public Shop(long id, String name, String location, BigDecimal revenue, LocalTime openingTime, LocalTime closingTime) {
        this.id = id;
        this.name = name;
        this.address = location;
        this.revenue = revenue;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
    }
    public Shop() {}
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
                ", address='" + address + '\'' +
                ", revenue='" + revenue + '\'' +
                ", openingTime='" + openingTime + '\'' +
                ", closingTime='" + closingTime + '\'' +
                '}';
    }


    /*@OneToMany(mappedBy = "shop")
    public Collection<Food> getFood() {
        return food;
    }

    public void setFood(Collection<Food> food) {
        this.food = food;
    }*/
}
