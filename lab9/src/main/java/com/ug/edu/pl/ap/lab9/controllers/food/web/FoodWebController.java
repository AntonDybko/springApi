package com.ug.edu.pl.ap.lab9.controllers.food.web;


import com.ug.edu.pl.ap.lab9.domain.Food;
import com.ug.edu.pl.ap.lab9.exceptions.NotFoundException;
import com.ug.edu.pl.ap.lab9.services.FoodService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/food")
public class FoodWebController {
    private final FoodService foodService;
    private final static String FOOD_NOT_FOUND_WITH_ID = "Food not found with ID: ";
    private final static String FOOD_NOT_FOUND = "No food matches your criteria";

    public FoodWebController(FoodService foodService) {
        this.foodService = foodService;
    }

    @GetMapping("/")
    public String showAllFood(Model model) {
        model.addAttribute("foodList", foodService.getAll());
        return "food/list";
    }

    @GetMapping("/findFood")
    public String getFindFoodPage(Model model) {
        return "food/findFuncList";
    }

    @GetMapping("/fvg")
    public String findFreshVegetarianFood(Model model) {
        LocalDate currentDate = LocalDate.now();
        List<Food> food = foodService.findVegetarianFoodByMinExpirationDate(currentDate);
        if (!food.isEmpty()) {
            model.addAttribute("foodList", food);
        } else {
            model.addAttribute("errorMessage", FOOD_NOT_FOUND);
            model.addAttribute("foodList", foodService.getAll());
        }
        return "food/list";
    }

    @GetMapping("/fbc")
    public String findByShop(@RequestParam String shop, Model model) {
        List<Food> food = foodService.findByShop(shop);
        if (!food.isEmpty()) {
            model.addAttribute("foodList", food);
        } else {
            model.addAttribute("errorMessage", FOOD_NOT_FOUND);
            model.addAttribute("foodList", foodService.getAll());
        }
        return "food/list";
    }

    @GetMapping("/nvfwc")
    public String findNonVegetarianFoodByCalories(@RequestParam double calories, Model model) {
        List<Food> food = foodService.findNonVegetarianFoodByCalories(calories);
        if (!food.isEmpty()) {
            model.addAttribute("foodList", food);
        } else {
            model.addAttribute("errorMessage", FOOD_NOT_FOUND);
            model.addAttribute("foodList", foodService.getAll());
        }
        return "food/list";
    }

    //!!
    @GetMapping("/{id}")
    public String showDetailsOfFood(@PathVariable Long id, Model model) {
        Optional<Food> foundFood = foodService.findById(id);
        /*foundFood.ifPresentOrElse(
                food -> model.addAttribute("food", food),
                () -> model.addAttribute("errorMessage", "Food not found with ID " + id)
        );
        return "food/details";*/
        if (foundFood.isPresent()) {
            model.addAttribute("food", foundFood.get());
            return "food/details";
        } else {
            model.addAttribute("errorMessage", FOOD_NOT_FOUND_WITH_ID + id);
            return "errors/error";
        }
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("food", new Food());
        return "food/add";
    }

    @PostMapping("/add")
    public String addFood(@ModelAttribute @Valid Food food, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("food", food);
            return "food/add";
        }

        foodService.add(food);

        model.addAttribute("successMessage", "Food added");
        model.addAttribute("foodList", foodService.getAll());
        return "food/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteFood(@PathVariable Long id, Model model) {
        Optional<Food> deletedFood = foodService.delete(id);
        deletedFood.ifPresentOrElse(
                food -> model.addAttribute("successMessage", "Food deleted"),
                () -> model.addAttribute("errorMessage", FOOD_NOT_FOUND_WITH_ID + id)
        );
        model.addAttribute("foodList", foodService.getAll());
        return "food/list";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Optional<Food> existingFood = foodService.findById(id);
        if (!existingFood.isEmpty()) {
            model.addAttribute("food", existingFood.get());
            return "food/edit";
        } else {
            model.addAttribute("errorMessage", "Food not found");
            model.addAttribute("foodList", foodService.getAll());
            return "food/list";
        }
    }

    @PostMapping("/edit/{id}")
    public String editFood(@PathVariable Long id, @ModelAttribute @Valid Food updatedFood, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("food", updatedFood);
            return "food/edit";
        }
        foodService.edit(updatedFood);
        model.addAttribute("successMessage", "Food updated");
        model.addAttribute("foodList", foodService.getAll());
        return "food/list";
    }
}

