package com.ug.edu.pl.ap.lab7.repository;

import com.ug.edu.pl.ap.lab7.domain.Food;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodRepository extends CrudRepository<Food, Long> {

}
