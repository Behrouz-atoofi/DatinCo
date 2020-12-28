package com.datin.elms.controller.mangeEmployee;


import com.datin.elms.model.CategoryElement;
import com.datin.elms.repository.EmployeeDao;

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

        EmployeeDao employeeDao = new EmployeeDao() ;
        List<CategoryElement> roleList = employeeDao.getRole() ;
        req.setAttribute("roleList",roleList);
        req.getRequestDispatcher("addemployee.jsp").forward(req,res);

    }


}
