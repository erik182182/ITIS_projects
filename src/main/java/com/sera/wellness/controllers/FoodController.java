package com.sera.wellness.controllers;


import com.sera.wellness.models.User;
import com.sera.wellness.services.FoodService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@NoArgsConstructor
@Controller
public class FoodController {
    @Autowired
    private FoodService foodService;

    @GetMapping("/mypersonalproducts")
    public String getAllPersonalProducts(ModelMap modelMap, Authentication authentication) {
        if (authentication == null) {
            return "redirect:/signin";
        }
        User user = (User) authentication.getPrincipal();
        modelMap.addAttribute("personalProducts",foodService.getAllProductsToUser(user));
        return "personalProducts";
    }

    @PostMapping("/addpersonalproduct")
    public String addPersonalProduct(ModelMap modelMap,Authentication authentication) {
        if (authentication == null) {
            return "redirect:/signin";
        }
        User user = (User) authentication.getPrincipal();
        //foodService.addPersonalProduct(Product.builder().build());
        return "redirect:/mypersonalproducts";
    }

}
