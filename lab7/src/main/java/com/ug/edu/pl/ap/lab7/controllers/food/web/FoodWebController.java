package com.ug.edu.pl.ap.lab7.controllers.food.web;


import com.ug.edu.pl.ap.lab7.domain.Food;
import com.ug.edu.pl.ap.lab7.services.FoodService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.Optional;

@Controller
@RequestMapping("/food")
public class FoodWebController {
    private final FoodService foodService;

    public FoodWebController(FoodService foodService) {
        this.foodService = foodService;
    }

    @GetMapping("/")
    public String showAllFood(Model model) {
        model.addAttribute("foodList", foodService.getAll());
        return "food/list";
    }

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
            model.addAttribute("errorMessage", "Food not found with ID " + id);
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
                () -> model.addAttribute("errorMessage", "Food not found with ID " + id)
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

