package com.ap.lab04.zad1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ap.lab04.zad1.services.FoodManager;
import com.ap.lab04.zad1.domain.Food;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import java.util.List;
import java.util.UUID;

@RestController
public class FoodController {
    private final FoodManager foodManager;
    public FoodController(FoodManager foodManager) {
        this.foodManager = foodManager;
    }

    @GetMapping("/api/food")
    public List<Food> getAll() {
        return foodManager.getAllFood();
    }

    @GetMapping("/api/food/{id}")
    public ResponseEntity<Food> getFoodById(@PathVariable UUID id) {
        Food food = foodManager.get(id);
        if (food != null) {
            return ResponseEntity.ok(food);
        } else {
            throw new FoodNotFoundException("Food not found with ID: " + id);
        }
    }

    @PostMapping("/api/food")
    public ResponseEntity<Void> addFood(@RequestBody Food foodToAdd) {
        UUID id = foodManager.addFood(foodToAdd);

        return ResponseEntity.status(HttpStatus.CREATED)
                .header("Location", "/api/food/" + id)
                .build();
    }

    @PutMapping("/api/food/{id}")
    public ResponseEntity<Void> editFood(@PathVariable UUID id, @RequestBody Food newFood) {
        Food editedFood = foodManager.putFood(id, newFood);
        if(editedFood != null){
            return ResponseEntity.noContent().build();
        }else{
            throw new FoodNotFoundException("Food not found with ID: " + id);
        }
    }

    @DeleteMapping("/api/food/{id}")
    public ResponseEntity<Void> deleteFood(@PathVariable UUID id) {
        Food deletedFood = foodManager.deleteFood(id);
        if(deletedFood != null){
            return ResponseEntity.noContent().build();
        }else{
            throw new FoodNotFoundException("Food not found with ID: " + id);
        }
    }


}
