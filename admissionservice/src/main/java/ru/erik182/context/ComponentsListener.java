package ru.erik182.context;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.erik182.repositories.*;
import ru.erik182.services.*;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ComponentsListener implements ServletContextListener {

    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ApplicationContext context = new
                AnnotationConfigApplicationContext(JavaConfig.class);
        UserService userService = context.getBean(UserService.class);
        DirectionService directionService = context.getBean(DirectionService.class);
        DeclarationService declarationService = context.getBean(DeclarationService.class);
        AdminService adminService = context.getBean(AdminService.class);

        servletContextEvent.getServletContext().setAttribute("userService", userService);
        servletContextEvent.getServletContext().setAttribute("adminService", adminService);
        servletContextEvent.getServletContext().setAttribute("directionService", directionService);
        servletContextEvent.getServletContext().setAttribute("declarationService", declarationService);
//
    }


    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
