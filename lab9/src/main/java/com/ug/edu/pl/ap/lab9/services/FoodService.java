package com.ug.edu.pl.ap.lab9.services;

import com.ug.edu.pl.ap.lab9.domain.Food;
import com.ug.edu.pl.ap.lab9.domain.Shop;
import com.ug.edu.pl.ap.lab9.repository.FoodRepository;
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
    private static final String FOOD_FOUND_STATUS = "FOUND";
    private static final String FOOD_NOT_FOUND_STATUS = "NOT FOUND";
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
    public Optional<Food> findWholeById(Long id) {
        Optional<Food> foundFood = foodRepository.findById(id);
        if(!foundFood.isEmpty()){
            //Food extractedFood = foundFood.get();
            return foundFood.map(food -> {
                // Load the lazy-loaded collection if needed
                Hibernate.initialize(food.getCategories());
                return food;
            });
            //extractedFood.getCategories().size();
            //Hibernate.initialize(extractedFood.getCategories());
            //return Optional.of(extractedFood);
        }else{
            return Optional.empty();
        }
    };
    public Food add(Food food){
        return foodRepository.save(food);
    }
    public Food edit(Food food){
        return foodRepository.save(food);
    }//?? czy po prostu findById, a potem ustalam pola seterami
    public Optional<Food> delete(Long id){
        Optional<Food> food = this.findById(id);
        foodRepository.deleteById(id);
        return food;
    }
    public List<Food> findVegetarianFoodByMinExpirationDate(LocalDate expirationDate){
        return foodRepository.findVegetarianFoodByMinExpirationDate(expirationDate);
    }
    public List<Food> findByShop(String shop){
        return foodRepository.findByShop(shop);
    }
    public List<Food> findNonVegetarianFoodByCalories(double calories){
        return foodRepository.findNonVegetarianFoodByCalories(calories);
    }
    public Optional<Food> addShop(Long foodId, Shop shop) {
        Optional<Food> food = foodRepository.findById(foodId);
        Optional<Shop> newShop = Optional.of(shop);
        if (food.isPresent() && newShop.isPresent()) {
            Food currentFood = food.get();

            currentFood.setShop(shop);

            foodRepository.save(food.get());
            return food;
        } else {
            return Optional.of(null);
        }
    }
    public void learning() {
        LocalDate currDate = LocalDate.now();
        Food bread = new Food("BREAD", 23.22, true, currDate);
        Food aushan_bread = add(bread);

        Long aushan_bread_id = aushan_bread.getId();
        Optional<Food> foundFood = findById(aushan_bread_id);
        foundFood.ifPresentOrElse(
                System.out::println,
                () -> System.out.println("NOT FOUND")
        );

        bread.setCalories(30.33);
        Food updatedFood = edit(bread);
        System.out.println(updatedFood);

        List<Food> allFood = getAll();
        System.out.println(allFood);

        Optional<Food> deletedFood = delete(aushan_bread_id);
        deletedFood.ifPresentOrElse(
                System.out::println,
                () -> System.out.println("NOT FOUND")
        );

        LocalDate dateTimeOfTomorrow = currDate.plusDays(1);
        Food veg = new Food("MILK", 50.22, true, dateTimeOfTomorrow);
        add(veg);
        Food meat = new Food("DUCK", 50.22, false, dateTimeOfTomorrow);
        add(meat);
        Food veg2 = new Food("MILKtwo", 50.22, true, dateTimeOfTomorrow);
        add(veg2);
        Food meat2 = new Food("DUCKtwo", 60.22, false, dateTimeOfTomorrow);
        add(meat2);
        Food meat3 = new Food("DUCKthree", 70.22, false, dateTimeOfTomorrow);
        add(meat3);
    }
}


