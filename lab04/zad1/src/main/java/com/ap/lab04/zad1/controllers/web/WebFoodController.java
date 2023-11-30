package com.ap.lab04.zad1.controllers;

import com.ap.lab04.zad1.domain.Food;
import com.ap.lab04.zad1.services.FoodManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@Controller
@RequestMapping("/foods")
public class WebFoodController {

    private final FoodManager foodManager;

    @Autowired
    public WebFoodController(FoodManager foodManager) {
        this.foodManager = foodManager;
    }

    @GetMapping
    public String showAllFoods(Model model) {
        model.addAttribute("foods", foodManager.getAllFood());
        return "food/list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("food", new Food());
        return "food/add";
    }

    @PostMapping("/add")
    public String addFood(@ModelAttribute @Valid Food food, BindingResult result) {
        if (result.hasErrors()) {
            return "food/add";
        }

        foodManager.addFood(food);
        return "redirect:/foods";
    }

    @GetMapping("/delete/{id}")
    public String deleteFood(@PathVariable UUID id) {
        foodManager.deleteFood(id);
        return "redirect:/foods";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable UUID id, Model model) {
        Food existingFood = foodManager.get(id);
        if (existingFood != null) {
            model.addAttribute("food", existingFood);
            return "food/edit";
        } else {
            return "redirect:/foods";
        }
    }

    @PostMapping("/edit/{id}")
    public String editFood(@PathVariable UUID id, @ModelAttribute @Valid Food updatedFood, BindingResult result) {
        if (result.hasErrors()) {
            return "food/edit";
        }

        foodManager.putFood(id, updatedFood);
        return "redirect:/foods";
    }
}