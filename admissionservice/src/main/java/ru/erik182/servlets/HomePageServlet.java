package ru.erik182.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.json.simple.JSONObject;
import ru.erik182.dto.DeclarationDto;
import ru.erik182.dto.DirectionDto;
import ru.erik182.dto.ExamDto;
import ru.erik182.dto.UserDto;
import ru.erik182.models.Declaration;
import ru.erik182.models.Direction;
import ru.erik182.models.Exam;
import ru.erik182.models.User;
import ru.erik182.services.DeclarationService;
import ru.erik182.services.DirectionService;
import ru.erik182.services.UserService;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class HomePageServlet extends HttpServlet {

    private UserService userService;
    private DirectionService directionService;
    private DeclarationService declarationService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        userService = (UserService) context.getAttribute("userService");
        directionService = (DirectionService) context.getAttribute("directionService");
        declarationService = (DeclarationService) context.getAttribute("declarationService");
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession();
        UserDto user = (UserDto)session.getAttribute("user");

        if(user != null) req.setAttribute("name", user.getFullName());

        List<DirectionDto> directions = null;
        List<DeclarationDto> declarations = new ArrayList<>();
        if(user != null){
            List<ExamDto> exams = userService.getExamsOfUserByPassport(user.getPassport());
            if(!exams.isEmpty()){
                req.setAttribute("exams", exams);
            }

            directions = directionService.getDirectionsForUser(user.getPassport());
            declarations = declarationService.getDeclarationsOfUser(user.getPassport());
            req.setAttribute("declarations", declarations);
            req.setAttribute("size", declarations.size());
            req.setAttribute("directions", directions);
        }
        else{
            directions = directionService.getAllDirections();
            req.setAttribute("directions", directions);
        }
        req.getRequestDispatcher("/WEB-INF/views/home.ftl").forward(req,res);

    }


    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession();
        UserDto user = (UserDto)session.getAttribute("user");
        JSONObject json = declarationService.generateJSON(user, Long.valueOf(req.getParameter("dirId")));
        res.getWriter().write(json.toJSONString());
    }

}
