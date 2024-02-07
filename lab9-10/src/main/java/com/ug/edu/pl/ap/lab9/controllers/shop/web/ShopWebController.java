package com.ug.edu.pl.ap.lab9.controllers.shop.web;


import com.ug.edu.pl.ap.lab9.domain.Food;
import com.ug.edu.pl.ap.lab9.domain.Shop;
import com.ug.edu.pl.ap.lab9.exceptions.NotFoundException;
import com.ug.edu.pl.ap.lab9.services.ShopService;
import com.ug.edu.pl.ap.lab9.services.FoodService;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.context.Context;
import org.thymeleaf.context.LazyContextVariable;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/shop")
public class ShopWebController {
    private final ShopService shopService;
    private final FoodService foodService;
    private final static String SHOP_NOT_FOUND = "No shops matches your criteria";
    private final static String SHOP_NOT_FOUND_WITH_ID = "Shop not found with ID: ";

    public ShopWebController(ShopService shopService, FoodService foodService) {
        this.shopService = shopService;
        this.foodService = foodService;
    }

    @GetMapping("/")
    public String showAllShops(Model model) {
        model.addAttribute("shopList", shopService.getAll());
        return "shop/list";
    }
    @GetMapping("/findShop")
    public String getFindFoodPage(Model model) {
        return "shop/findFuncList";
    }

    @GetMapping("/findByAddressAndOpeningTime")
    public String findByAddressAndOpeningTime(@RequestParam String address, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime openingTime, Model model) {
        List<Shop> shops = shopService.findByAddressAndOpeningTime(address, openingTime);
        if (!shops.isEmpty()) {
            model.addAttribute("shopList", shops);
        } else {
            model.addAttribute("errorMessage", SHOP_NOT_FOUND);
            model.addAttribute("shopList", shopService.getAll());
        }
        return "shop/list";
    }
    @GetMapping("/findByTwoAddresses")
    public String findByTwoAddresses(@RequestParam String address1, @RequestParam String address2, Model model) {
        List<Shop> shops = shopService.findByTwoAddresses(address1, address2);
        if (!shops.isEmpty()) {
            model.addAttribute("shopList", shops);
        } else {
            model.addAttribute("errorMessage", SHOP_NOT_FOUND);
            model.addAttribute("shopList", shopService.getAll());
        }
        return "shop/list";
    }
    @GetMapping("/findByAddressAndRevenue")
    public String findByAddressAndRevenue(@RequestParam String address_ar, @RequestParam BigDecimal revenue, Model model) {
        List<Shop> shops = shopService.findByAddressAndRevenue(address_ar, revenue);
        if (!shops.isEmpty()) {
            model.addAttribute("shopList", shops);
        } else {
            model.addAttribute("errorMessage", SHOP_NOT_FOUND);
            model.addAttribute("shopList", shopService.getAll());
        }
        return "shop/list";
    }
    @GetMapping("/findByRevenue")
    public String findByRevenue(@RequestParam BigDecimal min_revenue, @RequestParam BigDecimal max_revenue, Model model) {
        List<Shop> shops = shopService.findByRevenue(min_revenue, max_revenue);
        if (!shops.isEmpty()) {
            model.addAttribute("shopList", shops);
        } else {
            model.addAttribute("errorMessage", SHOP_NOT_FOUND);
            model.addAttribute("shopList", shopService.getAll());
        }
        return "shop/list";
    }

    @GetMapping("/{id}")
    public String showDetailsOfShop(@PathVariable Long id, Model model) {
        //Optional<Shop> foundShop = shopService.findByIdWithFood(id);
        Optional<Shop> foundShopWithFood = shopService.findWholeById(id);
        if (foundShopWithFood.isPresent()) {
            model.addAttribute("shop", foundShopWithFood.get());
            return "shop/details";
        } else {
            model.addAttribute("errorMessage", SHOP_NOT_FOUND_WITH_ID + id);
            return "errors/error";
        }
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("shop", new Shop());
        return "shop/add";
    }

    @PostMapping("/add")
    public String addShop(@ModelAttribute @Valid Shop shop, BindingResult result, Model model) {
        if (result.hasErrors()) {
            //DateTimeFormatter input = DateTimeFormatter.ofPattern("HH:mm");
            //DateTimeFormatter output = DateTimeFormatter.ofPattern("HH:mm a");
            //shop.setOpeningTime(LocalTime.parse(shop.getOpeningTime().format(input)));
            model.addAttribute("shop", shop);
            return "shop/add";
        }

        shopService.add(shop);

        model.addAttribute("successMessage", "Shop added");
        model.addAttribute("shopList", shopService.getAll());
        return "shop/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteShop(@PathVariable Long id, Model model) {
        Optional<Shop> deletedShop = shopService.delete(id);
        deletedShop.ifPresentOrElse(
                shop -> model.addAttribute("successMessage", "Shop deleted"),
                () -> model.addAttribute("errorMessage", SHOP_NOT_FOUND_WITH_ID + id)
        );
        model.addAttribute("shopList", shopService.getAll());
        return "shop/list";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Optional<Shop> existingShop = shopService.findById(id);
        if (!existingShop.isEmpty()) {
            model.addAttribute("shop", existingShop.get());
            return "shop/edit";
        } else {
            model.addAttribute("errorMessage", SHOP_NOT_FOUND_WITH_ID +  id);
            model.addAttribute("shopList", shopService.getAll());
            return "shop/list";
        }
    }

    @PostMapping("/edit/{id}")
    public String editShop(@PathVariable Long id, @ModelAttribute @Valid Shop updatedShop, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("shop", updatedShop);
            return "shop/edit";
        }
        shopService.edit(updatedShop);
        model.addAttribute("successMessage", "Shop updated");
        model.addAttribute("shopList", shopService.getAll());
        return "shop/list";
    }

    @GetMapping("/addFood/{id}")
    public String getAddFood(@PathVariable Long id, Model model) {
        Optional<Shop> shop = shopService.findById(id);
        if(!shop.isEmpty()){
            model.addAttribute("shop", shop.get());
            model.addAttribute("foodList", foodService.getAll());
        }else{
            model.addAttribute("errorMessage", SHOP_NOT_FOUND_WITH_ID +  id);
        }
        return "shop/addFood";
    }

    @PostMapping("/addFood/{id}/{foodId}")
    public String addFood(@PathVariable Long id, @PathVariable Long foodId, Model model) {
        Optional<Shop> updatedShop = shopService.addFood(id, foodId);
        if (updatedShop.isPresent()) {
            model.addAttribute("shop", updatedShop.get());
            model.addAttribute("successMessage", "Food with ID " + foodId + " added.");
            return "shop/details";
        } else {
            model.addAttribute("errorMessage",
                    "Error occured during adding food with ID " + foodId + " to shop with ID " + id);
            return "errors/error";
        }
    }

    @GetMapping("/removeFood/{id}/{foodId}")
    public String removeFood(@PathVariable Long id, @PathVariable Long foodId, Model model) {
        Optional<Shop> updatedShop = shopService.removeFood(id, foodId);
        if (updatedShop.isPresent()) {
            model.addAttribute("shop", updatedShop.get());
            model.addAttribute("successMessage", "Food with ID " + foodId + " removed.");
            return "shop/details";
        } else {
            model.addAttribute("errorMessage",
                    "Error occured during removing food with ID " + foodId + " from shop with ID " + id);
            return "errors/error";
        }
    }
}

