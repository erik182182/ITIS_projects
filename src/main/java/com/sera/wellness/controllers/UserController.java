package com.sera.wellness.controllers;

import com.sera.wellness.forms.UserLoginForm;
import com.sera.wellness.models.UserAuth;
import com.sera.wellness.security.UserDetailsImpl;
import com.sera.wellness.services.UserService;
import com.sera.wellness.forms.UserRegistrationForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
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



//    @RequestMapping(method = RequestMethod.POST,value = "/signin")
    public String tryAuth(@RequestParam(value = "email") String email,
                          @RequestParam(value = "password") String password,
                          HttpServletResponse response,
                          ModelMap model){
        UserLoginForm form = UserLoginForm.builder()
                .email(email)
                .password(password)
                .build();
            try{
                UserAuth userAuth = service.signIn(form);
                Cookie id = new Cookie( "id",userAuth.getId().toString());
                Cookie value = new Cookie( "value",userAuth.getCookieValue());
                id.setMaxAge(60*60*24*365);
                value.setMaxAge(60*60*24*7);
                response.addCookie(id);
                response.addCookie(value);



                session.setAttribute("user",userAuth.getUser());
                return "redirect:/articles";

            }
            catch (IllegalArgumentException e){
                model.addAttribute("error", e.getMessage());
                return "signin";
            }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/profile")
    public String webFlow(ModelMap modelMap, UserDetailsImpl userDetails){
        modelMap.addAttribute("getUser", userDetails.getUser());
        return "profile";
    }
    @RequestMapping(method = RequestMethod.POST, value = "/profile")
    public String webFlow(
            @RequestParam(value = "email") String email,
            @RequestParam(value = "first_name") String firstName,
            @RequestParam(value = "last_name") String lastName,
            @RequestParam(value = "password") String password,
            @RequestParam(value = "repeat_password") String repeatPassword,
            @RequestParam(value = "consent_emails", required = false) boolean consentToReceiveEmails,
            @RequestParam(value = "sex") String sex,
            @RequestParam(value = "growth") Long growth,
            @RequestParam(value = "weight") Long weight,
            @RequestParam(value = "purposeWeight") Long purposeWeight

    ){
        return "profile";
    }

    /*@GetMapping(value = "/exit")
    public String exit(ModelMap modelMap, HttpServletResponse response) {
        Cookie id = new Cookie( "id","");
        Cookie value = new Cookie( "value","");
        id.setMaxAge(-1000000000);
        value.setMaxAge(-1000000000);
        response.addCookie(id);
        response.addCookie(value);
        session.removeAttribute("user");
        return "redirect:/signin";
    }*/
}
