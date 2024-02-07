package com.ug.edu.pl.ap.lab9.repository;

import com.ug.edu.pl.ap.lab9.domain.Food;
import com.ug.edu.pl.ap.lab9.domain.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface FoodRepository extends JpaRepository<Food, Long> {
    @Query("SELECT f FROM Food f WHERE " +
            "f.isVegetarian = true AND " +
            "f.expirationDate > ?1 " +
            "ORDER BY f.expirationDate DESC")
    List<Food> findVegetarianFoodByMinExpirationDate(LocalDate expirationDate);

    @Query("SELECT f FROM Food f  WHERE " +
            "f.isVegetarian = false AND f.calories >= ?1")
    List<Food> findNonVegetarianFoodByCalories(double calories);
    @Query("SELECT f FROM Food f LEFT JOIN FETCH f.categories")
    List<Food> findAll();
    @Query("SELECT f FROM Food f LEFT JOIN FETCH f.categories c WHERE f.id = ?1")
    Optional<Food> findWholeById(Long id);
}
