package com.ug.edu.pl.ap.lab9.services;

import com.ug.edu.pl.ap.lab9.domain.Food;
import com.ug.edu.pl.ap.lab9.domain.Shop;
import com.ug.edu.pl.ap.lab9.repository.ShopRepository;
import com.ug.edu.pl.ap.lab9.repository.FoodRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

@Service
@Transactional
public class ShopService {
    final ShopRepository shopRepository;
    final FoodRepository foodRepository;
    private static final String SHOP_FOUND_STATUS = "SHOP FOUND";
    private static final String FOOD_NOT_FOUND_STATUS = "SHOP NOT FOUND";
    public ShopService(ShopRepository shopRepository, FoodRepository foodRepository) {
        this.shopRepository = shopRepository;
        this.foodRepository = foodRepository;
    }
    public List<Shop> getAll(){
        List<Shop> allShops = new ArrayList<>();
        shopRepository.findAll().forEach(allShops::add);
        return allShops;
    }
    public Optional<Shop> findById(Long id) {
        return shopRepository.findById(id);
    };
    public Optional<Shop> findWholeById(Long id) {
        return shopRepository.findWholeById(id);

    };
    public Shop add(Shop shop){
        return shopRepository.save(shop);
    }
    public Shop edit(Shop shop){
        return shopRepository.save(shop);
    }
    public Optional<Shop> delete(Long id){
        Optional<Shop> shop = this.findById(id);
        shopRepository.deleteById(id);
        return shop;
    }
    public Optional<Shop> addFood(Long shopId, Long foodId) {
        Optional<Shop> shop = this.findWholeById(shopId);
        Optional<Food> food = foodRepository.findById(foodId);
        if (shop.isPresent() && food.isPresent()) {
            Shop currentShop = shop.get();
            Food currentFood = food.get();

            currentFood.getCategories().size();

            List<Food> foodInShop = currentShop.getFood();
            if (foodInShop.stream().anyMatch(existingFood -> existingFood.equals(currentFood))){
                return Optional.empty();
            }

            foodInShop.add(currentFood);
            currentShop.setFood(foodInShop);

            shopRepository.save(currentShop);
            return Optional.of(currentShop);
        } else {
            return Optional.empty();
        }
    }

    public Optional<Shop> removeFood(Long shopId, Long foodId) {
        Optional<Shop> shop = this.findWholeById(shopId);
        Optional<Food> food = foodRepository.findById(foodId);
        if (shop.isPresent() && food.isPresent()) {
            Food currentFood = food.get();
            Shop currentShop = shop.get();

            List<Food> foodInShop = currentShop.getFood();
            if (!foodInShop.stream().anyMatch(existingFood -> existingFood.equals(currentFood))){
                return Optional.empty();
            }
            foodInShop.remove(currentFood);

            currentShop.setFood(foodInShop);

            shopRepository.save(currentShop);
            return Optional.of(currentShop);
        } else {
            return Optional.of(null);
        }
    }
    public List<Shop> findByAddressAndOpeningTime(String address, LocalTime openingTime){
        return shopRepository.findByAddressAndOpeningTime(address, openingTime);
    }
    public List<Shop> findByTwoAddresses(String address1, String address2){
        return shopRepository.findByTwoAddresses(address1, address2);
    }
    public List<Shop> findByAddressAndRevenue(String address, BigDecimal revenue){
        return shopRepository.findByAddressAndRevenue(address, revenue);
    }
    public List<Shop> findByRevenue(BigDecimal min, BigDecimal max){
        return shopRepository.findByRevenue(min, max);
    }

    public void learning() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime openingTime = LocalTime.parse("08:00", formatter);
        LocalTime closingTime = LocalTime.parse("21:00", formatter);

        Shop aushan = Shop.builder()
                .name("Aushan")
                .address("456 Broad Ave, Townsville, AnotherState, 54321")
                .revenue(new BigDecimal("123456.456"))
                .openingTime(openingTime)
                .closingTime(closingTime)
                .build();

        shopRepository.save(aushan);
    }
}

