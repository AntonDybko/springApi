package com.ug.edu.pl.ap.lab9.controllers.food.api;

import com.ug.edu.pl.ap.lab9.domain.Food;
import com.ug.edu.pl.ap.lab9.domain.FoodDTO;
import com.ug.edu.pl.ap.lab9.exceptions.NotFoundException;
import com.ug.edu.pl.ap.lab9.services.FoodService;
import jakarta.validation.Valid;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class FoodController {
    private final FoodService foodService;
    private final static String FOOD_NOT_FOUND_WITH_ID = "Food not found with ID: ";
    //private final static String FOOD_NOT_FOUND = "No food matches your criteria";
    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }

    @GetMapping("/api/food")
    public ResponseEntity<List<FoodDTO>> getAll() {
        List<FoodDTO> foodList = foodService.getAll()
                .stream().map(food -> new FoodDTO(food.getId(), food.getName()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(foodList);
    }

    @GetMapping("/api/food/findFreshVegetarianFood")
    public ResponseEntity<List<FoodDTO>> findFreshVegetarianFood() {
        LocalDate currentDate = LocalDate.now();
        List<FoodDTO> food = foodService.findVegetarianFoodByMinExpirationDate(currentDate)
                .stream().map(f-> new FoodDTO(f.getId(), f.getName()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(food);
    }

    @GetMapping("/api/food/findByShop")
    public ResponseEntity<List<FoodDTO>> findByShop(@RequestParam String shop) {
        System.out.println(shop);
        List<FoodDTO> food = foodService.findByShop(shop)
                .stream().map(f-> new FoodDTO(f.getId(), f.getName()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(food);
    }

    @GetMapping("/api/food/findNonVegetarianFoodByCalories")
    public ResponseEntity<List<FoodDTO>> findNonVegetarianFoodByCalories(@RequestParam double calories) {
        List<FoodDTO> food = foodService.findNonVegetarianFoodByCalories(calories)
                .stream().map(f-> new FoodDTO(f.getId(), f.getName()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(food);
    }

    @GetMapping("/api/food/{id}")
    public ResponseEntity<Food> getFoodById(@PathVariable Long id) {
        Optional<Food> food = foodService.findWholeById(id);
        if (!food.isEmpty()) {
            return ResponseEntity.ok(food.get());
        } else {
            throw new NotFoundException(FOOD_NOT_FOUND_WITH_ID + id);
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
            throw new NotFoundException(FOOD_NOT_FOUND_WITH_ID + id);
        }
    }

    @DeleteMapping("/api/food/{id}")
    public ResponseEntity<Void> deleteFood(@PathVariable Long id) {
        if(!foodService.delete(id).isEmpty()){
            return ResponseEntity.noContent().build(); //204
        }else{
            throw new NotFoundException(FOOD_NOT_FOUND_WITH_ID + id);
        }
    }


}
