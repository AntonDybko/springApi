package com.ug.edu.pl.ap.lab7.controllers.shop.web;


import com.ug.edu.pl.ap.lab7.domain.Shop;
import com.ug.edu.pl.ap.lab7.services.ShopService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Controller
@RequestMapping("/shop")
public class ShopWebController {
    private final ShopService shopService;
    private final static String SHOP_NOT_FOUND = "Shop not found with ID: ";

    public ShopWebController(ShopService shopService) {
        this.shopService = shopService;
    }

    @GetMapping("/")
    public String showAllShops(Model model) {
        model.addAttribute("shopList", shopService.getAll());
        return "shop/list";
    }

    @GetMapping("/{id}")
    public String showDetailsOfShop(@PathVariable Long id, Model model) {
        Optional<Shop> foundShop = shopService.findById(id);
        if (foundShop.isPresent()) {
            model.addAttribute("shop", foundShop.get());
            return "shop/details";
        } else {
            model.addAttribute("errorMessage", SHOP_NOT_FOUND + id);
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
                shop -> model.addAttribute("successMessage", "Shop with id deleted"),
                () -> model.addAttribute("errorMessage", SHOP_NOT_FOUND + id)
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
            model.addAttribute("errorMessage", SHOP_NOT_FOUND +  id);
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
}

