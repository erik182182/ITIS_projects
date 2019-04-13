package com.sera.wellness.controllers;

import com.sera.wellness.forms.UserLoginForm;
import com.sera.wellness.services.UserService;
import com.sera.wellness.forms.UserRegistrationForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
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
                    //TODO добавить в сессию и куки
                    return "redirect:/articles";
                }
                catch (IllegalArgumentException e){
                    model.addAttribute("error", e.getMessage());
                    return "signup";
                }
    }


    @RequestMapping(method = RequestMethod.GET,value = "/signin")
    public String getLoginForm(ModelMap model){
        return "signin";
    }

    @RequestMapping(method = RequestMethod.POST,value = "/signin")
    public String tryAuth(@RequestParam(value = "email") String email,
                          @RequestParam(value = "password") String password,
                          ModelMap model){
        UserLoginForm form = UserLoginForm.builder()
                .email(email)
                .password(password)
                .build();
            try{
                service.signIn(form);
                //TODO добавить в сессию и куки
                return "redirect:/articles";

            }
            catch (IllegalArgumentException e){
                model.addAttribute("error", e.getMessage());
                return "signin";
            }
    }
}
