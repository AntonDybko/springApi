package com.ug.edu.pl.ap.lab9.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/*public class ShopDTO {
    private Long id;
    private String name;
    public ShopDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}*/
public class ShopDTO {
    private long id;
    private String name;
    private String address;
    //456 Broad Ave, Townsville, AnotherState, 54321
    private BigDecimal revenue;
    private LocalTime openingTime;
    private LocalTime closingTime;
    private List<FoodDTO> food;

    public ShopDTO(long id, String name, String address, BigDecimal revenue, LocalTime openingTime, LocalTime closingTime, List<FoodDTO> food) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.revenue = revenue;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
        this.food = food;
    }

    public static List<ShopDTO> listToShopDTO(List<Shop> shopList){
        return shopList
                .stream().map(shop -> new ShopDTO(
                        shop.getId(),
                        shop.getName(),
                        shop.getAddress(),
                        shop.getRevenue(),
                        shop.getOpeningTime(),
                        shop.getClosingTime(),
                        shop.getFood().stream().map(f -> new FoodDTO(
                                f.getId(),
                                f.getName(),
                                f.getCalories(),
                                f.getIsVegetarian(),
                                f.getExpirationDate(),
                                f.getLicense()))
                                .collect(Collectors.toList())
                ))
                .collect(Collectors.toList());
    }

    public static List<ShopDTO> listToShopDTOWithoutFood(List<Shop> shopList){
        return shopList
                .stream().map(shop -> new ShopDTO(
                        shop.getId(),
                        shop.getName(),
                        shop.getAddress(),
                        shop.getRevenue(),
                        shop.getOpeningTime(),
                        shop.getClosingTime(),
                        null
                ))
                .collect(Collectors.toList());
    }

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

    public void setAddress(String address) {
        this.address = address;
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

    public List<FoodDTO> getFood() {
        return food;
    }

    public void setFood(List<FoodDTO> food) {
        this.food = food;
    }

    public static Optional<ShopDTO> shopToShopDTO(Optional<Shop> shop){
         if(shop.isPresent()){
             return shop.map(s -> new ShopDTO(
                     s.getId(),
                     s.getName(),
                     s.getAddress(),
                     s.getRevenue(),
                     s.getOpeningTime(),
                     s.getClosingTime(),
                     s.getFood().
                             stream().
                             map(f -> new FoodDTO(
                                     f.getId(),
                                     f.getName(),
                                     f.getCalories(),
                                     f.getIsVegetarian(),
                                     f.getExpirationDate(),
                                     f.getLicense()))
                             .collect(Collectors.toList())
             ));
         }else{
             return Optional.empty();
         }
    }


}
