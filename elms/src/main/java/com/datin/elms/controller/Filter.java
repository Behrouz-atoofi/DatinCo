package com.datin.elms.controller;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = "/*")
public class Filter implements javax.servlet.Filter {

    @Override
    public void init(FilterConfig filterConfig)  {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        HttpSession session = request.getSession(false);
        String loginURI = request.getContextPath() + "/login";
        boolean loggedIn = session != null && session.getAttribute("employee") != null;
        boolean loginRequest = request.getRequestURI().equals(loginURI);
        boolean staticFile = request.getRequestURI().contains("/static/LoginPage/");
        if (loggedIn || loginRequest || staticFile) {
            chain.doFilter(request, response);
        } else {
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }

    @Override
    public void destroy() {

    }

}
