package com.datin.elms.controller.mangeEmployee;

import com.datin.elms.service.EmployeeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/deleteEmployee")
public class DeleteEmployeeSrv extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int id = Integer.parseInt(req.getParameter("id"));

        EmployeeService employeeService = new EmployeeService();
        employeeService.deleteEmployee(id);

        resp.sendRedirect("employees");
    }

}
