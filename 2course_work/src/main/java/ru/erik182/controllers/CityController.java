package ru.erik182.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.erik182.repositories.CityRepository;
import ru.erik182.services.CityService;

@Controller
@RequestMapping("/city")
public class CityController {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private CityService cityService;

    @GetMapping
    public String getCity(@RequestParam("city_id") Long cityId,
                          ModelMap modelMap){
        modelMap.addAttribute("city", cityRepository.getOne(cityId));
        modelMap.addAttribute("coord", cityService.getCoordinates(cityId));
        return "city";
    }
}
