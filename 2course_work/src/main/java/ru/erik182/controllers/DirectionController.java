package ru.erik182.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import ru.erik182.models.Direction;
import ru.erik182.models.User;
import ru.erik182.models.forms.DirectionForm;
import ru.erik182.repositories.DeclarationRepository;
import ru.erik182.repositories.DirectionRepository;
import ru.erik182.repositories.ExamRepository;
import ru.erik182.services.DeclarationService;
import ru.erik182.services.DirectionService;
import ru.erik182.services.ExamService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.Optional;

@Controller
@RequestMapping("/home")
@SessionAttributes("user")
public class DirectionController {

    @Autowired
    private DirectionService directionService;

    @Autowired
    private DeclarationService declarationService;

    @Autowired
    private ExamService examService;

    @RequestMapping(method = RequestMethod.GET)
    public String getHomePage(Authentication authentication,
                              ModelMap modelMap){
        User user = null;
        if(authentication != null) {
            user = (User) authentication.getPrincipal();
        }
            modelMap.addAttribute("user", user);
            modelMap.addAttribute("directions", directionService.getDirections(user));
            modelMap.addAttribute("declarations", declarationService.getDeclarationsOfUser(user));
            modelMap.addAttribute("exams", examService.getExamsOfUser(user));
        return "home";
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    //TODO: ajax
    public String addDirectionOfUser(@Valid @ModelAttribute("directionForm") DirectionForm form,
                                     Authentication authentication,
                                     ModelMap map){
        System.out.println("i am here");
        User user = (User) authentication.getPrincipal();
        Direction direction = Direction.builder().id(form.getDirId()).build();
        declarationService.saveDeclarationOfUser(user, direction);
        return "redirect:" + MvcUriComponentsBuilder
                .fromMappingName("DeclarationController#getDeclarationsPage").build();
    }
}
