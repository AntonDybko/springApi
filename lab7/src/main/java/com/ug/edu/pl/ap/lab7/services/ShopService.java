package com.ug.edu.pl.ap.lab7.services;

import com.ug.edu.pl.ap.lab7.domain.Food;
import com.ug.edu.pl.ap.lab7.domain.Shop;
import com.ug.edu.pl.ap.lab7.repository.FoodRepository;
import com.ug.edu.pl.ap.lab7.repository.ShopRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ShopService {
    final ShopRepository shopRepository;
    private static final String SHOP_FOUND_STATUS = "SHOP FOUND";
    private static final String FOOD_NOT_FOUND_STATUS = "SHOP NOT FOUND";
    public ShopService(ShopRepository shopRepository) {
        this.shopRepository = shopRepository;
    }
    public List<Shop> getAll(){
        List<Shop> allShops = new ArrayList<>();
        shopRepository.findAll().forEach(allShops::add);
        return allShops;
    }
    public Optional<Shop> findById(Long id) {
        return shopRepository.findById(id);
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
}
