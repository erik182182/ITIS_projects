package ru.erik182.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.erik182.models.User;
import ru.erik182.services.DirectionService;

@Controller
@RequestMapping("/declaration")
public class DeclarationController {

    @Autowired
    private DirectionService directionService;

    @GetMapping
    public String getDeclarationsPage(Authentication authentication,
                                      ModelMap modelMap){
        User user = null;
        if(authentication != null) {
            user = (User) authentication.getPrincipal();
        }
        modelMap.addAttribute(directionService.getDirectionsOfUserWithDeclarations(user));
        return "declarations";
    }
}
