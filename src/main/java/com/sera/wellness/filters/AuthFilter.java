package com.sera.wellness.filters;

import com.sera.wellness.models.User;
import com.sera.wellness.models.UserAuth;
import com.sera.wellness.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Component
@Order(1)
public class AuthFilter implements Filter {

    @Autowired
    private UserService userService;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        if (req.getSession().getAttribute("user") != null) {
            filterChain.doFilter(req, servletResponse);
            return;
        }
        Cookie[] cookies = req.getCookies();
        String id = null, value = null;
        if(cookies ==null) {
            filterChain.doFilter(req,servletResponse);
            return;
        }
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("id")) {
                id = cookie.getValue();
            }
            if (cookie.getName().equals("value")) {
                value = cookie.getValue();
            }
        }
        if (id != null) {
            if (value != null) {
                Long longId =  -1L;
                try {
                    longId = Long.parseLong(id);
                } catch (Exception e) {
                    filterChain.doFilter(req, servletResponse);
                    return;
                }
                UserAuth userAuth = null;
                userAuth = userService.auth(longId);
                if (userAuth==null) {
                    System.err.println("wrong");
                    filterChain.doFilter(req,servletResponse);
                    return;
                }
                if (userAuth.getCookieValue().equals(value)) {
                    req.getSession().setAttribute("user",userAuth.getUser());
                }
            }
        }
        try {
            filterChain.doFilter(req, servletResponse);
        } catch (Exception e) {

        }
    }

    @Override
    public void destroy() {

    }
}
