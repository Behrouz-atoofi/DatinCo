package com.datin.elms.controller.profile;

import com.datin.elms.model.Employee;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/myProfile")
public class profileSrv extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Employee me = (Employee) req.getSession().getAttribute("employee") ;
        req.setAttribute("me",me);
        req.getRequestDispatcher("profile.jsp").forward(req,resp);

    }
}
