package com.ug.edu.pl.ap.lab9.services;

import com.ug.edu.pl.ap.lab9.domain.Food;
import com.ug.edu.pl.ap.lab9.domain.Shop;
import com.ug.edu.pl.ap.lab9.repository.FoodRepository;
import com.ug.edu.pl.ap.lab9.repository.ShopRepository;
import jakarta.transaction.Transactional;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class FoodService {
    final FoodRepository foodRepository;
    final ShopRepository shopRepository;
    private static final String FOOD_FOUND_STATUS = "FOUND";
    private static final String FOOD_NOT_FOUND_STATUS = "NOT FOUND";
    public FoodService(FoodRepository foodRepository, ShopRepository shopRepository) {
        this.foodRepository = foodRepository;
        this.shopRepository = shopRepository;
    }
    public List<Food> getAll(){
        List<Food> allFood = new ArrayList<>();
        foodRepository.findAll().forEach(allFood::add);
        return allFood;
    }
    public Optional<Food> findById(Long id) {
        return foodRepository.findById(id);
    };
    public Optional<Food> findWholeById(Long id) {
        return foodRepository.findWholeById(id);
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
    public List<Food> findVegetarianFoodByMinExpirationDate(LocalDate expirationDate){
        return foodRepository.findVegetarianFoodByMinExpirationDate(expirationDate);
    }
    public List<Food> findNonVegetarianFoodByCalories(double calories){
        return foodRepository.findNonVegetarianFoodByCalories(calories);
    }
    public void learning() {
        LocalDate currDate = LocalDate.now();

        LocalDate dateTimeOfTomorrow = currDate.plusDays(1);

        Food veg = Food.builder()
                .name("MILK")
                .calories(50.22)
                .isVegetarian(true)
                .expirationDate(dateTimeOfTomorrow)
                .build();
        add(veg);

        Food meat = Food.builder()
                .name("DUCK")
                .calories(200.5)
                .isVegetarian(false)
                .expirationDate(dateTimeOfTomorrow)
                .build();
        ;
        add(meat);
    }
}


