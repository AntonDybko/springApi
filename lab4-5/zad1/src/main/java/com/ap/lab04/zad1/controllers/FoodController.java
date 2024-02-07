package com.ap.lab04.zad1.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ap.lab04.zad1.services.FoodManager;
import com.ap.lab04.zad1.domain.Food;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
public class FoodController {
    private final FoodManager foodManager;
    public FoodController(FoodManager foodManager) {
        this.foodManager = foodManager;
    }

    @GetMapping("/api/food")
    public ResponseEntity<List<Food>> getAll() {
        List<Food> foodList = foodManager.getAllFood();
        return ResponseEntity.ok(foodList);
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
    public ResponseEntity<Food> addFood(@Valid @RequestBody Food foodToAdd) {
        Food addedFood = foodManager.addFood(foodToAdd);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(addedFood.getId())
                .toUri();

        return ResponseEntity.status(HttpStatus.CREATED).location(location).body(addedFood);
    }

    @PutMapping("/api/food/{id}")
    public ResponseEntity<Food> editFood(@PathVariable UUID id, @Valid @RequestBody Food newFood) {
        Food editedFood = foodManager.putFood(id, newFood);
        if(editedFood != null){
            return ResponseEntity.ok(editedFood);
        }else{
            throw new FoodNotFoundException("Food not found with ID: " + id);
        }
    }

    @DeleteMapping("/api/food/{id}")
    public ResponseEntity<Void> deleteFood(@PathVariable UUID id) {
        if(foodManager.deleteFood(id)){
            return ResponseEntity.noContent().build(); //204
        }else{
            throw new FoodNotFoundException("Food not found with ID: " + id);
        }
    }


}
