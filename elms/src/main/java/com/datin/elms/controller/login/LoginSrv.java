package com.datin.elms.controller.login;

import com.datin.elms.model.Employee;
import com.datin.elms.service.LoginService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginSrv extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private LoginService loginService;

    public void init() {
        loginService= new LoginService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("index.jsp");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            authenticate(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void authenticate(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (loginService.validate(username, password) != null ) {
            Employee employee = loginService.validate(username,password);
            request.getSession().setAttribute("employee",employee);
            RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
            dispatcher.forward(request, response);
        }else {
            response.sendRedirect("error.jsp");
        }
    }
}