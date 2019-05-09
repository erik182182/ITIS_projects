package ru.erik182.filters;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

public class CookieFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        if(request.getParameter("remember") != null){
            System.out.println(request.getParameter("remember"));
            Cookie tokenCookie = new Cookie("token", request.getSession().getId());
            tokenCookie.setMaxAge(60 * 60 * 24 * 365);
            response.addCookie(tokenCookie);
        }

        if (request.getCookies() != null) {
            Optional<Cookie> tokenCookie = Arrays.stream(request.getCookies())
                    .filter(c -> c.getName().equals("token")).findFirst();
            if(tokenCookie.isPresent()){
                Cookie sessionCookie = new Cookie("JSESSIONID", tokenCookie.get().getValue());
                response.addCookie(sessionCookie);
            }
        }

        filterChain.doFilter(servletRequest, servletResponse);

    }

    @Override
    public void destroy() {

    }
}
