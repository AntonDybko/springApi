package com.ug.edu.pl.ap.lab9.controllers.shop.api;

import com.ug.edu.pl.ap.lab9.domain.Food;
import com.ug.edu.pl.ap.lab9.domain.FoodDTO;
import com.ug.edu.pl.ap.lab9.domain.Shop;
import com.ug.edu.pl.ap.lab9.domain.ShopDTO;
import com.ug.edu.pl.ap.lab9.exceptions.NotFoundException;
import com.ug.edu.pl.ap.lab9.services.ShopService;
import com.ug.edu.pl.ap.lab9.services.FoodService;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.math.BigDecimal;
import java.net.URI;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class ShopController {
    private final ShopService shopService;
    private final static String SHOP_NOT_FOUND = "Shop not found with ID: ";
    public ShopController(ShopService shopService) {
        this.shopService = shopService;
    }

    @GetMapping("/api/shop")
    public ResponseEntity<List<ShopDTO>> getAll() {
        List<ShopDTO> shopList = shopService.getAll()
                .stream().map(shop -> new ShopDTO(shop.getId(), shop.getName()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(shopList);
    }

    //here need some work yeah??
    @GetMapping("/api/shop/findByAddressAndOpeningTime")
    public ResponseEntity<List<ShopDTO>> findByAddressAndOpeningTime(@RequestParam String address, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime openingTime) {
        List<ShopDTO> shopList = shopService.findByAddressAndOpeningTime(address, openingTime)
                .stream().map(s-> new ShopDTO(s.getId(), s.getName()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(shopList);
    }
    @GetMapping("/api/shop/findByAddressAndRevenue")
    public ResponseEntity<List<ShopDTO>> findByAddressAndRevenue(@RequestParam String address, @RequestParam BigDecimal revenue) {
        List<ShopDTO> shopList = shopService.findByAddressAndRevenue(address, revenue)
                .stream().map(s-> new ShopDTO(s.getId(), s.getName()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(shopList);
    }
    @GetMapping("/api/shop/findByRevenue")
    public ResponseEntity<List<ShopDTO>> findByRevenue(@RequestParam BigDecimal min, @RequestParam BigDecimal max) {
        List<ShopDTO> shopList = shopService.findByRevenue(min, max)
                .stream().map(s-> new ShopDTO(s.getId(), s.getName()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(shopList);
    }
    @GetMapping("/api/shop/findByTwoAddresses")
    public ResponseEntity<List<ShopDTO>> findByTwoAddresses(@RequestParam String address1, @RequestParam String address2) {
        List<ShopDTO> shopList = shopService.findByTwoAddresses(address1, address2)
                .stream().map(s-> new ShopDTO(s.getId(), s.getName()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(shopList);
    }
    @PutMapping("/api/shop/addFood/{id}/{foodId}")
    public ResponseEntity<Shop> addFood(@PathVariable Long id, @PathVariable Long foodId) {
        Optional<Shop> updatedShop = shopService.addFood(id, foodId);
        if(updatedShop.isPresent()){
            return ResponseEntity.ok(updatedShop.get());
        }else{
            throw new NotFoundException("Error occured during adding food with ID " + foodId + " to shop with ID " + id);
        }
    }

    @DeleteMapping("/api/shop/removeFood/{id}/{foodId}")
    public ResponseEntity<Void> removeFood(@PathVariable Long id, @PathVariable Long foodId) {
        Optional<Shop> updatedShop = shopService.removeFood(id, foodId);
        if (updatedShop.isPresent()) {
            return ResponseEntity.noContent().build(); //204
        } else {
            throw new NotFoundException("Error occured during removing food with ID " + foodId + " from shop with ID " + id);
        }
    }

    @GetMapping("/api/shop/{id}")
    public ResponseEntity<Shop> getShopById(@PathVariable Long id) {
        Optional<Shop> shop = shopService.findWholeById(id);
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
