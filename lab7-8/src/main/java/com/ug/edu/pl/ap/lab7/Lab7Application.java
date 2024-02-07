package com.ug.edu.pl.ap.lab7;

import com.ug.edu.pl.ap.lab7.domain.Food;
import com.ug.edu.pl.ap.lab7.services.FoodService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class Lab7Application {

	public static void main(String[] args) {
		SpringApplication.run(Lab7Application.class, args);
	}

	@Bean
	public CommandLineRunner setUpApp(FoodService foodService) {
		return (args) -> {
			LocalDate curr = LocalDate.now();
			Food bread = new Food("BREAD", 23.22, true, curr);

			Food aushan_bread = foodService.add(bread);
			Long aushan_bread_id = aushan_bread.getId();
			Optional<Food> foundFood = foodService.findById(aushan_bread_id);
			foundFood.ifPresentOrElse(
					System.out::println,
					() -> System.out.println("NOT FOUND")
			);

			bread.setCalories(30.33);
			Food updatedFood = foodService.edit(bread);
			System.out.println(updatedFood);

			List<Food> allFood = foodService.getAll();
			System.out.println(allFood);

			Optional<Food> deletedFood = foodService.delete(aushan_bread_id);
			deletedFood.ifPresentOrElse(
					System.out::println,
					() -> System.out.println("NOT FOUND")
			);

/*
        this.id = id;
        this.name = name;
        this.calories = calories;
        this.isVegetarian = isVegetarian;
        this.expirationDate = expirationDate;
 */

		};
	}

}
