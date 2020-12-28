package com.datin.elms.controller.mangeEmployee;

import com.datin.elms.model.Employee;
import com.datin.elms.repository.CategoryDao;
import com.datin.elms.repository.EmployeeDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/addEmployeeToDb")
public class AddEmpToDbSrv extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("name");
        String family = req.getParameter("family");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String phoneNumber = req.getParameter("phonenumber");
        int roleId = Integer.parseInt(req.getParameter("roleName")) ;
        Employee superVisor = (Employee) req.getSession().getAttribute("employee") ;
        Employee employee = new Employee();
        employee.setName(name);
        employee.setFamily(family);
        employee.setUsername(username);
        employee.setPassword(password);
        employee.setEmail(email);
        employee.setRole(CategoryDao.getElementById(roleId));
        employee.setPhoneNumber(phoneNumber);
        employee.setManager(superVisor);

        EmployeeDao employeeDao = new EmployeeDao();
        employeeDao.saveEmployee(employee);
        resp.sendRedirect("employees");

    }
}
