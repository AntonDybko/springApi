package com.ap.lab04.zad1.config;

/*import com.ap.lab04.zad1.domain.Food;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Configuration
public class FoodCreator {
    static List<Food> personToAdd = new ArrayList<>();
    static LocalDate currentDate = LocalDate.now();
    static int counter = -1;
    static {
        personToAdd.add(new Food("Bolek", currentDate.plus(7, ChronoUnit.DAYS)));
        personToAdd.add(new Food("Lolek", currentDate.plus(10, ChronoUnit.DAYS)));
        personToAdd.add(new Food("Tola", currentDate.plus(13, ChronoUnit.DAYS)));
        personToAdd.add(new Food("Reksio", currentDate.plus(16, ChronoUnit.DAYS)));
    }

    @Bean
    @Scope("prototype")
    Food createBean() {
        counter++;
        return personToAdd.get(counter);
    }

}*/
