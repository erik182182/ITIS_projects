package ru.erik182.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import ru.erik182.models.User;
import ru.erik182.repositories.DeclarationRepository;
import ru.erik182.repositories.DirectionRepository;
import ru.erik182.repositories.ExamRepository;
import ru.erik182.services.DeclarationService;
import ru.erik182.services.DirectionService;
import ru.erik182.services.ExamService;

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
    public String getHomePage(ModelMap modelMap){
        User user = (User) modelMap.get("user");
        modelMap.addAttribute("directions", directionService.getDirections(user));
        modelMap.addAttribute("declarations", declarationService.getDeclarationsOfUser(user));
        modelMap.addAttribute("exams", examService.getExamsOfUser(user));
        return "home";
    }
}
