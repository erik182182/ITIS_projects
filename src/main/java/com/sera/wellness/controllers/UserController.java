package com.sera.wellness.controllers;

import com.sera.wellness.forms.UserProfileForm;
import com.sera.wellness.models.Friend;
import com.sera.wellness.models.UploadedFile;
import com.sera.wellness.models.User;
import com.sera.wellness.services.UserService;
import com.sera.wellness.forms.UserRegistrationForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

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


    @RequestMapping(method = RequestMethod.GET, value = "/signin")
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
//        user = service.getThis(user.getId()).get();

        modelMap.addAttribute("getUser", user);
        return "profile";
    }
    @RequestMapping(method = RequestMethod.POST, value = "/profile")
    public String webFlow(
            @RequestParam(value = "firstName") String firstName,
            @RequestParam(value = "lastName") String lastName,
            @RequestParam(value = "photo") MultipartFile photo,
            @RequestParam(value = "password") String password,
            @RequestParam(value = "repeat_password") String repeatPassword,
            @RequestParam(value = "consent_emails", required = false) boolean consentToReceiveEmails,
            @RequestParam(value = "sex") Boolean sex,
            @RequestParam(value = "age") Integer age,
            @RequestParam(value = "growth") Integer growth,
            @RequestParam(value = "weight") Integer weight,
            @RequestParam(value = "purposeWeight") Integer purposeWeight,
            Authentication authentication ){
        if (authentication ==null) {
            return "redirect:/signin";
        }
        User user = (User) authentication.getPrincipal();
        String fileName = user.getId()+photo.hashCode()+photo.getOriginalFilename();
        String fileDir = "src/main/resources/static/users.profile.img/" + fileName;

        if (!photo.isEmpty()) {
            try {
                byte[] bytes = photo.getBytes();
                BufferedOutputStream stream =
                        new BufferedOutputStream(new FileOutputStream(new File(fileDir)));
                stream.write(bytes);
                stream.close();
            } catch (Exception e) {
//                return "Вам не удалось загрузить " + name + " => " + e.getMessage();
            }
        } else {
            fileName = user.getPhotoSrc();
        }

        if (password.length()==0){
            UserProfileForm userToSave = UserProfileForm.builder()
                .id(user.getId())
                .firstName(firstName)
                .lastName(lastName)
                .password(user.getHashPassword())
                .email(user.getEmail())
                .consentToReceiveEmails(consentToReceiveEmails)
                .sex(sex)
                .age(age)
                .growth(growth)
                .weight(weight)
                .photoSrc(fileName)
                .purposeWeight(purposeWeight)
                .build();
        service.updateUser(userToSave);
        }else {
            if (password.equals(repeatPassword)){
                UserProfileForm userToSave = UserProfileForm.builder()
                .id(user.getId())
                .firstName(firstName)
                .lastName(lastName)
                .password(new BCryptPasswordEncoder().encode(password))
                .email(user.getEmail())
                .consentToReceiveEmails(consentToReceiveEmails)
                .sex(sex)
                .age(age)
                .growth(growth)
                .weight(weight)
                .photoSrc(fileName)
                .purposeWeight(purposeWeight)
                .build();
        service.updateUser(userToSave);
            }else {

            }
        }

        user = service.getThis(user.getId()).get();
        authentication = new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return "redirect:/articles";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/addToFriend")
    public String addToFriend(Authentication authentication)
    {
        if (authentication ==null) {
            return "redirect:/signin";
        }
        User user = (User) authentication.getPrincipal();

        return "signin";
    }
    @RequestMapping(method = RequestMethod.GET, value = "/myFriends")
    public String getAllMyFriends(Authentication authentication, ModelMap modelMap)
    {
        if (authentication ==null) {
            return "redirect:/signin";
        }
        User user = (User) authentication.getPrincipal();

        modelMap.addAttribute("friends", service.getFriends(user.getId()));
        modelMap.addAttribute("getUser", user);

        return "friends";
    }

    @RequestMapping(value = "/findByName", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<User> getUsersAsJson(@RequestParam(value = "name") String name) {
        return service.findAllByName(name);
    }

    @RequestMapping(path = "myFriends/{id}",method = RequestMethod.GET)
    public String getFriend(@PathVariable Long id, ModelMap modelMap,Authentication authentication) {
        if (authentication==null) {
            return "redirect:/signin";
        }
        User user = (User) authentication.getPrincipal();
        try{
            User friend = service.getFriend(user.getId(), id).get();
            if (friend!=null) {
                modelMap.addAttribute("friend", friend);
            }
            modelMap.addAttribute("user", user);
        }
        catch (IllegalArgumentException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
        return "friend";
    }

    @RequestMapping(path = "myFriends/{id}",method = RequestMethod.GET)
    public String messages(@PathVariable Long id, ModelMap modelMap,Authentication authentication) {
        if (authentication==null) {
            return "redirect:/signin";
        }
        User user = (User) authentication.getPrincipal();
        try{
            User friend = service.getFriend(user.getId(), id).get();
            if (friend!=null) {
                modelMap.addAttribute("friend", friend);
            }
            modelMap.addAttribute("user", user);
        }
        catch (IllegalArgumentException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
        return "messages";
    }





}
