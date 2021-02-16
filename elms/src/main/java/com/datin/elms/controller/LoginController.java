package com.datin.elms.controller;

import com.datin.elms.model.Employee;
import com.datin.elms.repository.LoginDao;
import com.datin.elms.service.LoginService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet(urlPatterns = "/login")

public class LoginController extends HttpServlet {

    private HttpSession session;
    private LoginDao loginDao;
    private LoginService loginService;

    @Override
    public void init()  {
        loginDao = new LoginDao();
        loginService = new LoginService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");
        String nextPage = "/index.jsp";

        if (action.equalsIgnoreCase("signIn")) {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            Employee employee = loginDao.validate(username, password);

            if (employee != null) {
                request.getSession().setAttribute("employee", employee);
            } else {
                request.setAttribute("msg", "Invalid username or password");
                nextPage = "/error.jsp";
            }
        } else if (action.equalsIgnoreCase("signOut")) {
            request.getSession().invalidate();
            nextPage = "/login.jsp";
        }
        RequestDispatcher rd = request.getRequestDispatcher(nextPage);
        rd.forward(request, response);
    }
}
