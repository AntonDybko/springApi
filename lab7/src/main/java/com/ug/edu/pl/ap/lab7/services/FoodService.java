package com.ug.edu.pl.ap.lab7.services;

import com.ug.edu.pl.ap.lab7.domain.Food;
import com.ug.edu.pl.ap.lab7.repository.FoodRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class FoodService {
    final FoodRepository foodRepository;
    private static final String FOOD_FOUND_STATUS = "FOOD FOUND";
    private static final String FOOD_NOT_FOUND_STATUS = "FOOD NOT FOUND";
    public FoodService(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }
    public List<Food> getAll(){
        List<Food> allFood = new ArrayList<>();
        foodRepository.findAll().forEach(allFood::add);
        return allFood;
    }
    public Optional<Food> findById(Long id) {
        return foodRepository.findById(id);
    };
    public Food add(Food food){
        return foodRepository.save(food);
    }
    public Food edit(Food food){
        return foodRepository.save(food);
    }
    public Optional<Food> delete(Long id){
        Optional<Food> food = this.findById(id);
        foodRepository.deleteById(id);
        return food;
    }
}
