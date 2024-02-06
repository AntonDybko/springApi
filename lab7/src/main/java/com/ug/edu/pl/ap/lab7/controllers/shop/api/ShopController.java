package com.ug.edu.pl.ap.lab7.controllers.shop.api;

import com.ug.edu.pl.ap.lab7.domain.Shop;
import com.ug.edu.pl.ap.lab7.services.ShopService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ug.edu.pl.ap.lab7.exceptions.NotFoundException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
@RestController
public class ShopController {
    private final ShopService shopService;
    private final static String SHOP_NOT_FOUND = "Shop not found with ID: ";
    public ShopController(ShopService shopService) {
        this.shopService = shopService;
    }

    @GetMapping("/api/shop")
    public ResponseEntity<List<Shop>> getAll() {
        List<Shop> shopList = shopService.getAll();
        return ResponseEntity.ok(shopList);
    }

    @GetMapping("/api/shop/{id}")
    public ResponseEntity<Shop> getShopById(@PathVariable Long id) {
        Optional<Shop> shop = shopService.findById(id);
        if (!shop.isEmpty()) {
            return ResponseEntity.ok(shop.get());
        } else {
            throw new NotFoundException(SHOP_NOT_FOUND + id);
        }
    }

    @PostMapping("/api/shop")
    public ResponseEntity<Shop> addShop(@Valid @RequestBody Shop shopToAdd) {
        Shop newShop = shopService.add(shopToAdd);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newShop.getId())
                .toUri();

        return ResponseEntity.status(HttpStatus.CREATED).location(location).body(newShop);
    }

    @PutMapping("/api/shop/{id}")
    public ResponseEntity<Shop> editShop(@PathVariable Long id, @Valid @RequestBody Shop newShop) {
        Shop editedShop = shopService.edit(newShop);
        if(editedShop != null){
            return ResponseEntity.ok(editedShop);
        }else{
            throw new NotFoundException(SHOP_NOT_FOUND + id);
        }
    }

    @DeleteMapping("/api/shop/{id}")
    public ResponseEntity<Void> deleteShop(@PathVariable Long id) {
        if(!shopService.delete(id).isEmpty()){
            return ResponseEntity.noContent().build(); //204
        }else{
            throw new NotFoundException(SHOP_NOT_FOUND + id);
        }
    }


}
