package com.ug.edu.pl.ap.lab9;

import com.ug.edu.pl.ap.lab9.domain.Shop;
import com.ug.edu.pl.ap.lab9.services.FoodService;
import com.ug.edu.pl.ap.lab9.services.ShopService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Lab7Application {

	public static void main(String[] args) {
		SpringApplication.run(Lab7Application.class, args);
	}

	@Bean
	public CommandLineRunner setUpApp(FoodService foodService, ShopService shopService) {
		return (args) -> {
			shopService.learning();
			foodService.learning();
			//shopService.learning();
		};
	}

}
