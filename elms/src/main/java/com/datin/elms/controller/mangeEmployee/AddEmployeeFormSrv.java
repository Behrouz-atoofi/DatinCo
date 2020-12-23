package com.datin.elms.controller.mangeEmployee;


import com.datin.elms.model.CategoryElement;
import com.datin.elms.service.EmployeeService;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/addEmployeeForm")
public class AddEmployeeFormSrv extends HttpServlet {

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {

        EmployeeService employeeService = new EmployeeService() ;
        List<CategoryElement> roleList = employeeService.getRole() ;
        req.setAttribute("roleList",roleList);
        req.getRequestDispatcher("addemployee.jsp").forward(req,res);

    }


}
