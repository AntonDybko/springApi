package com.ap.lab04.zad1.services;
import com.ap.lab04.zad1.domain.Food;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
public class FoodManagerInMemoryImpl implements FoodManager {
    List<Food> db = Collections.synchronizedList(new ArrayList<>());
    private Food findFoodById(UUID targetId) {
        for (Food food : db) {
            if (food.getId().equals(targetId)) {
                return food;
            }
        }
        return null;
    }
    @Override
    public Food addFood(Food food){
        Food foodToAdd = new Food(food.getName(), food.getCalories(), food.getIsVegetarian(), food.getExpirationDate());
        db.add(foodToAdd);
        return foodToAdd;
    };
    @Override
    public Food putFood(UUID id, Food food){
        Food foodToEdit = findFoodById(id);
        foodToEdit.setName(food.getName());
        foodToEdit.setCalories(food.getCalories());
        foodToEdit.setIsVegetarian(food.getIsVegetarian());
        foodToEdit.setExpirationDate(food.getExpirationDate());
        return foodToEdit;
    };
    @Override
    public Food get(UUID id){
        return findFoodById(id);
    };
    @Override
    public List<Food> getAllFood(){
        return db;
    };
    @Override
    public boolean deleteFood(UUID id){
        Food foodToDelete = findFoodById(id);
        if(foodToDelete != null){
            db.remove(foodToDelete);
            return true;
        }
        return false;
    };
}
