package com.ap.lab04.zad1.services;

import com.ap.lab04.zad1.domain.Food;

import java.util.List;
import java.util.UUID;

public interface FoodManager {
    Food addFood(Food food);
    Food putFood(UUID id, Food food);
    Food get(UUID id);
    List<Food> getAllFood();
    boolean deleteFood(UUID id);
}
