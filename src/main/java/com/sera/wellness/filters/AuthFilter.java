package com.sera.wellness.filters;

import com.sera.wellness.models.User;
import com.sera.wellness.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthFilter implements Filter {

    @Autowired
    private UserService userService;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        HttpServletRequest req = (HttpServletRequest) servletRequest;
//        HttpServletResponse res = (HttpServletResponse) servletResponse;
//        User user = (User)req.getSession().getAttribute("user");
//        Cookie token = null;
//        for(Cookie cookie:req.getCookies()){
//            if(cookie.getName().equals("token")) token = cookie;
//        }
//
//        if(user != null){
//            if(token != null){
//                if(userService.checkCookie(user, token.getValue())){
//                    res.sendRedirect("/articles");
//                    return;
//                }
//            }
//        }
//        req.getSession().setAttribute("user", null);
//        res.addCookie(new Cookie("token", null));
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
