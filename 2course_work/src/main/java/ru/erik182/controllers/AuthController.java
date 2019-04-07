package ru.erik182.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AuthController {

    @RequestMapping(value = "/signin", method = RequestMethod.GET)
    public String getSignInPage(ModelMap model){
        return "signin";
    }
}
