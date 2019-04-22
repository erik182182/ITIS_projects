package com.sera.wellness.controllers;

import com.sera.wellness.models.User;
import com.sera.wellness.services.UserService;
import com.sera.wellness.forms.UserRegistrationForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class UserController {
    @Autowired
    private HttpSession session;
    @Autowired
    private UserService service;
    @Autowired
    private ApplicationContext context;

    @RequestMapping(method = RequestMethod.GET,value = "/signup")
    public String getForm(ModelMap model){
        return "signup";
    }

    @RequestMapping(method = RequestMethod.POST,value = "/signup")
    public String trySave(@RequestParam(value = "email") String email,
          @RequestParam(value = "first_name") String firstName,
          @RequestParam(value = "last_name") String lastName,
          @RequestParam(value = "password") String password,
          @RequestParam(value = "repeat_password") String repeatPassword,
          @RequestParam(value = "consent_emails", required = false) boolean consentToReceiveEmails,
          @RequestParam(value = "consent_data") boolean consentToTheProcessingPersonalData,
          @RequestParam(value = "sex") String sex,
                          ModelMap model) {
        UserRegistrationForm form = UserRegistrationForm.builder()
                .email(email)
                .password(password)
                .repeatPassword(repeatPassword)
                .firstName(firstName)
                .lastName(lastName)
                .consentToReceiveEmails(consentToReceiveEmails)
                .consentToTheProcessingOfPersonalData(consentToTheProcessingPersonalData)
                .sex(sex)
                .build();
                try{
                    service.signUp(form);

                    return "redirect:/signin";
                }
                catch (IllegalArgumentException e){
                    model.addAttribute("error", e.getMessage());
                    return "signup";
                }
    }


    @RequestMapping(method = RequestMethod.GET,value = "/signin")
    public String getLoginForm(HttpServletRequest request, ModelMap model)
    {
        if(request.getParameter("error") !=null) {
            model.addAttribute("error","Неправильный логин или пароль");
        }
        return "signin";
    }




    @RequestMapping(method = RequestMethod.GET, value = "/profile")
    public String webFlow(ModelMap modelMap, Authentication authentication){
        if (authentication ==null) {
            return "redirect:/signin";
        }
        User user = (User) authentication.getPrincipal();
        modelMap.addAttribute("getUser", user);
        return "profile";
    }
    @RequestMapping(method = RequestMethod.POST, value = "/profile")
    public String webFlow(
            @RequestParam(value = "firstName") String firstName,
            @RequestParam(value = "lastName") String lastName,
            @RequestParam(value = "password") String password,
            @RequestParam(value = "repeat_password") String repeatPassword,
            @RequestParam(value = "consent_emails", required = false) boolean consentToReceiveEmails,
            @RequestParam(value = "sex") Boolean sex,
            @RequestParam(value = "growth") Integer growth,
            @RequestParam(value = "weight") Integer weight,
            @RequestParam(value = "purposeWeight") Integer purposeWeight,
            Authentication authentication ){
        if (authentication ==null) {
            return "redirect:/signin";
        }
        User user = (User) authentication.getPrincipal();
        System.out.println(user);
        System.out.println(password);
        if (password==null){
            User userToSave = User.builder()
                    .id(user.getId())
                    .firstName(firstName)
                    .lastName(lastName)
                    .consentToReceiveEmails(consentToReceiveEmails)
                    .sex(sex)
                    .growth(growth)
                    .weight(weight)
                    .purposeWeight(purposeWeight)
                    .build();
            service.updateUser(userToSave);
        }else {
            if (password.equals(repeatPassword)){
                User userToSave = User.builder()
                        .id(user.getId())
                        .firstName(firstName)
                        .lastName(lastName)
                        .hashPassword(new BCryptPasswordEncoder().encode(password))
                        .consentToReceiveEmails(consentToReceiveEmails)
                        .sex(sex)
                        .growth(growth)
                        .weight(weight)
                        .purposeWeight(purposeWeight)
                        .build();
                service.updateUser(userToSave);
            }
            //доделать выбрасывать исключения!!!
        }


        return "redirect:/profile";
    }


}
