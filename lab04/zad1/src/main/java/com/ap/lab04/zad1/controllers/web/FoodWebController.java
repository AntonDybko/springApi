package com.ap.lab04.zad1.controllers.web;


import com.ap.lab04.zad1.domain.Food;
import com.ap.lab04.zad1.services.FoodManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.UUID;

@Controller
public class FoodWebController {
    private final FoodManager foodManager;

    public FoodWebController(FoodManager foodManager) {
        this.foodManager = foodManager;
    }

    @GetMapping("/")
    public String showAllFood(Model model) {
        model.addAttribute("foodList", foodManager.getAllFood());
        return "food/list";
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

        foodManager.addFood(food);

        model.addAttribute("successMessage", "Food added");
        model.addAttribute("foodList", foodManager.getAllFood());
        return "food/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteFood(@PathVariable UUID id, Model model) {
        if(foodManager.deleteFood(id)) {
            model.addAttribute("successMessage", "Food deleted");
        }else{
            model.addAttribute("errorMessage", "Food not found");
        }
        model.addAttribute("foodList", foodManager.getAllFood());
        return "food/list";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable UUID id, Model model) {
        Food existingFood = foodManager.get(id);
        if (existingFood != null) {
            model.addAttribute("food", existingFood);
            return "food/edit";
        } else {
            model.addAttribute("errorMessage", "Food not found");
            model.addAttribute("foodList", foodManager.getAllFood());
            return "food/list";
        }
    }

    @PostMapping("/edit/{id}")
    public String editFood(@PathVariable UUID id, @ModelAttribute @Valid Food updatedFood, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("food", updatedFood);
            return "food/edit";
        }
        foodManager.putFood(id, updatedFood);
        model.addAttribute("successMessage", "Food updated");
        model.addAttribute("foodList", foodManager.getAllFood());
        return "food/list";
    }
}

