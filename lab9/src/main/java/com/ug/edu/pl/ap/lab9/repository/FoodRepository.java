package com.ug.edu.pl.ap.lab9.repository;

import com.ug.edu.pl.ap.lab9.domain.Food;
import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface FoodRepository extends JpaRepository<Food, Long> {
    @Query("SELECT f FROM Food f WHERE " +
            "f.isVegetarian = true AND " +
            "f.expirationDate > ?1 " +
            "ORDER BY f.expirationDate DESC")
    List<Food> findVegetarianFoodByMinExpirationDate(LocalDate expirationDate);

    @Query("SELECT f FROM Food f JOIN f.shop s WHERE s.name = ?1")
    List<Food> findByShop(String shopName);

    @Query("SELECT f FROM Food f  WHERE " +
            "f.isVegetarian = false AND f.calories >= ?1")
    List<Food> findNonVegetarianFoodByCalories(double calories);
}
