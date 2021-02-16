package com.datin.elms.controller;

import com.datin.elms.model.Employee;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet(urlPatterns = "/profile")
public class ProfileController extends HttpServlet {

    private HttpSession session;
    private Employee employee;

    @Override
    public void init() {

        employee = new Employee();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        session = request.getSession();
        employee = (Employee) session.getAttribute("employee");
        request.setAttribute("me", employee);
        RequestDispatcher rd = request.getRequestDispatcher("/profile.jsp");
        rd.forward(request, response);


    }
}
