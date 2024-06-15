package com.ug.edu.pl.ap.lab9.controllers.shop.api;

import com.ug.edu.pl.ap.lab9.domain.*;
import com.ug.edu.pl.ap.lab9.exceptions.ConflictException;
import com.ug.edu.pl.ap.lab9.exceptions.NotFoundException;
import com.ug.edu.pl.ap.lab9.services.ShopService;
import com.ug.edu.pl.ap.lab9.services.FoodService;
import jakarta.validation.Valid;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.math.BigDecimal;
import java.net.URI;
import java.time.LocalTime;
import java.util.ArrayList;
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
        List<ShopDTO> shopList = ShopDTO.listToShopDTO(shopService.getAll());
        return ResponseEntity.ok(shopList);
    }

    //here need some work yeah??
    @GetMapping("/api/shop/findByAddressAndOpeningTime")
    public ResponseEntity<List<ShopDTO>> findByAddressAndOpeningTime(@RequestParam String address, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime openingTime) {
        List<ShopDTO> shopList = ShopDTO.listToShopDTOWithoutFood(shopService.findByAddressAndOpeningTime(address, openingTime));
        return ResponseEntity.ok(shopList);
    }
    @GetMapping("/api/shop/findByAddressAndRevenue")
    public ResponseEntity<List<ShopDTO>> findByAddressAndRevenue(@RequestParam String address, @RequestParam BigDecimal revenue) {
        List<ShopDTO> shopList = ShopDTO.listToShopDTOWithoutFood(shopService.findByAddressAndRevenue(address, revenue));
        return ResponseEntity.ok(shopList);
    }
    @GetMapping("/api/shop/findByRevenue")
    public ResponseEntity<List<ShopDTO>> findByRevenue(@RequestParam BigDecimal min, @RequestParam BigDecimal max) {
        List<ShopDTO> shopList = ShopDTO.listToShopDTOWithoutFood(shopService.findByRevenue(min, max));
        return ResponseEntity.ok(shopList);
    }
    @GetMapping("/api/shop/findByTwoAddresses")
    public ResponseEntity<List<ShopDTO>> findByTwoAddresses(@RequestParam String address1, @RequestParam String address2) {
        List<ShopDTO> shopList = ShopDTO.listToShopDTOWithoutFood(shopService.findByTwoAddresses(address1, address2));
        return ResponseEntity.ok(shopList);
    }
    @PutMapping("/api/shop/addFood/{id}/{foodId}")
    public ResponseEntity<Shop> addFood(@PathVariable Long id, @PathVariable Long foodId) {
        Optional<Shop> updatedShop = shopService.addFood(id, foodId);
        if(updatedShop.isPresent()){
            return ResponseEntity.ok(updatedShop.get());
        }else{
            throw new ConflictException("Error occured during adding food with ID " + foodId + " to shop with ID " + id);
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
    public ResponseEntity<ShopDTO> getShopById(@PathVariable Long id) {
        Optional<ShopDTO> shop = ShopDTO.shopToShopDTO(shopService.findWholeById(id));
        if (shop.isPresent()) {
            return ResponseEntity.ok(shop.get());
        } else {
            throw new NotFoundException(SHOP_NOT_FOUND + id);
        }
    }

    @PostMapping("/api/shop")
    public ResponseEntity<Shop> addShop(@Valid @RequestBody Shop shopToAdd) {
        try {
            Shop newShop = shopService.add(shopToAdd);

            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(newShop.getId())
                    .toUri();

            return ResponseEntity.status(HttpStatus.CREATED).location(location).body(newShop);
        } catch (DataIntegrityViolationException ex) {
            throw new ConflictException("The food entry already exists.");
        }
    }

    @PutMapping("/api/shop/{id}")
    public ResponseEntity<Shop> editShop(@PathVariable Long id, @Valid @RequestBody Shop newShop) {
        try {
            Shop editedShop = shopService.edit(newShop);
            if (editedShop != null) {
                return ResponseEntity.ok(editedShop);
            } else {
                throw new NotFoundException(SHOP_NOT_FOUND + id);
            }
        } catch (DataIntegrityViolationException ex) {
            throw new ConflictException("The food entry already exists.");
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
