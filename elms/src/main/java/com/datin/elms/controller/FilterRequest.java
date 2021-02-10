package com.datin.elms.controller;

import com.datin.elms.model.Employee;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = "/*")
public class FilterRequest implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        HttpSession session = request.getSession(false);
        String loginURI = request.getContextPath() + "/login";
        System.out.println(loginURI);
        boolean loggedIn = session != null && session.getAttribute("employee") != null;
        boolean loginRequest = request.getRequestURI().equals(loginURI);
        System.out.println(request.getRequestURI());
        if (loggedIn || loginRequest) {
            chain.doFilter(request, response);
        } else {
            request.getRequestDispatcher("/login.jsp").forward(request,response);
        }
    }

    @Override
    public void destroy() {

    }

}
