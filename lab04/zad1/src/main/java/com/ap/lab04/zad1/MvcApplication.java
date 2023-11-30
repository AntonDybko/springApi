package com.ap.lab04.zad1;

import com.ap.lab04.zad1.domain.Food;
import com.ap.lab04.zad1.services.FoodManager;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@SpringBootApplication
public class MvcApplication {
    public static void main(String[] args) {
        SpringApplication.run(MvcApplication.class, args);
    }
    @Bean
    public CommandLineRunner appSetup(FoodManager foodManager){
        return args -> {
            System.out.println("CommandLineRunner started...");
            LocalDate currentDate = LocalDate.now();
            foodManager.addFood(new Food("Bolek", currentDate.plus(3, ChronoUnit.DAYS)));
            foodManager.addFood(new Food("Lolek", currentDate.plus(5, ChronoUnit.DAYS)));
            foodManager.addFood(new Food("Tola", currentDate.plus(7, ChronoUnit.DAYS)));
        };
    }

}
