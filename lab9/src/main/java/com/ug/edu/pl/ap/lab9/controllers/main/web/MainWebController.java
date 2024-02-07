package com.ug.edu.pl.ap.lab9.controllers.main.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainWebController {
    @GetMapping("/")
    public String showAllShops(Model model) {
        return "main";
    }
}
