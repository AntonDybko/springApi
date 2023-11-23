package com.ap.lab04.zad1.services;
import com.ap.lab04.zad1.domain.Food;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;


public class FoodManagerInMemoryImpl implements FoodManager {
    List<Food> db = Collections.synchronizedList(new ArrayList<>());
    @Override
    public UUID addFood(Food food){
        Food foodToAdd = new Food(food.getName(), food.getTerm());
        db.add(foodToAdd);
        return foodToAdd.getId();
    };
    @Override
    public Food putFood(UUID id, Food food){
        Food foodToEdit = findFoodById(id);
        foodToEdit.setName(food.getName());
        foodToEdit.setTerm(food.getTerm());
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
    public Food deleteFood(UUID id){
        Food foodToDelete = findFoodById(id);
        if(foodToDelete != null){
            db.remove(foodToDelete);
        }
        return foodToDelete;
    };
    private Food findFoodById(UUID targetId) {
        for (Food food : db) {
            if (food.getId() == targetId) {
                return food;
            }
        }
        return null;
    }
}
