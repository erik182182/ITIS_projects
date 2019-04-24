package ru.erik182.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.erik182.models.User;
import ru.erik182.models.forms.SignInForm;
import ru.erik182.services.UserService;

@Controller
@RequestMapping("/signin")
@SessionAttributes("user")
public class AuthController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public String getSignInPage(ModelMap model){
        User user = (User)model.get("user");
        if(user == null){
            return "signin";
        }
        else {
            return "redirect:/home";
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public String signIn(@RequestParam("passport") String passport,
                         @RequestParam("password") String password,
                         @RequestParam(value = "remember", required = false) boolean isRemember,
                         ModelMap model){
        User user = (User)model.get("user");
        if(user != null) {
            return "redirect:/home";
        }
        SignInForm signInForm = SignInForm.builder()
                .passport(passport)
                .password(password)
                .isRemember(isRemember)
                .build();
        try{
            model.addAttribute("user", userService.singIn(signInForm));
        }
        catch (IllegalArgumentException e){
            model.addAttribute("message", e.getMessage());
            return "signin";
        }
        return "redirect:/home";
    }
}
