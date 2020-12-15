package com.datin.elms.controller;


import com.datin.elms.model.Category;
import com.datin.elms.model.Category_element;
import com.datin.elms.service.EmployeeService;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/addEmployee")
public class AddEmployeeServlet extends HttpServlet {

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {

        EmployeeService employeeService = new EmployeeService() ;
        List<Category_element> roleList = employeeService.getRole() ;
        req.setAttribute("roleList",roleList);
        req.getRequestDispatcher("addemployee.jsp").forward(req,res);

//        Category category = new Category() ;
//        List<Category_element> roleList = category.getCategory_elements() ;
//        req.setAttribute("roleList",roleList);
//        req.getRequestDispatcher("addemployee.jsp").forward(req,res);

    }


}
