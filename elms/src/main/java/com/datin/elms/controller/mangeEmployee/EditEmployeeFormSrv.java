package com.datin.elms.controller.mangeEmployee;


import com.datin.elms.model.CategoryElement;
import com.datin.elms.model.Employee;
import com.datin.elms.repository.EmployeeDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/editEmployeeForm")
public class EditEmployeeFormSrv extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id")) ;
        EmployeeDao employeeDao = new EmployeeDao() ;
        Employee employee = employeeDao.getEmployeeById(id) ;
        List<CategoryElement> roleList = employeeDao.getRole() ;

        //System.out.println(employee.getName());
        req.setAttribute("roleList",roleList);
        req.setAttribute("employee",employee);
        req.getRequestDispatcher("editemployee.jsp").forward(req,resp);

    }
}
