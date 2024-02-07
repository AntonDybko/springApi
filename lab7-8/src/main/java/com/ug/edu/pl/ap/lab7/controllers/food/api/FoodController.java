package com.ug.edu.pl.ap.lab7.controllers.food.api;

import com.ug.edu.pl.ap.lab7.domain.Food;
import com.ug.edu.pl.ap.lab7.exceptions.NotFoundException;
import com.ug.edu.pl.ap.lab7.services.FoodService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
@RestController
public class FoodController {
    private final FoodService foodService;
    private final static String FOOD_NOT_FOUND = "Food not found with ID: ";
    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }

    @GetMapping("/api/food")
    public ResponseEntity<List<Food>> getAll() {
        List<Food> foodList = foodService.getAll();
        return ResponseEntity.ok(foodList);
    }

    @GetMapping("/api/food/{id}")
    public ResponseEntity<Food> getFoodById(@PathVariable Long id) {
        Optional<Food> food = foodService.findById(id);
        if (!food.isEmpty()) {
            return ResponseEntity.ok(food.get());
        } else {
            throw new NotFoundException(FOOD_NOT_FOUND + id);
        }
    }

    @PostMapping("/api/food")
    public ResponseEntity<Food> addFood(@Valid @RequestBody Food foodToAdd) {
        Food newFood = foodService.add(foodToAdd);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newFood.getId())
                .toUri();

        return ResponseEntity.status(HttpStatus.CREATED).location(location).body(newFood);
    }

    @PutMapping("/api/food/{id}")
    public ResponseEntity<Food> editFood(@PathVariable Long id, @Valid @RequestBody Food newFood) {
        Food editedFood = foodService.edit(newFood);
        if(editedFood != null){
            return ResponseEntity.ok(editedFood);
        }else{
            throw new NotFoundException(FOOD_NOT_FOUND + id);
        }
    }

    @DeleteMapping("/api/food/{id}")
    public ResponseEntity<Void> deleteFood(@PathVariable Long id) {
        if(!foodService.delete(id).isEmpty()){
            return ResponseEntity.noContent().build(); //204
        }else{
            throw new NotFoundException(FOOD_NOT_FOUND + id);
        }
    }


}
