package com.datin.elms.controller.mangeEmployee;


import com.datin.elms.model.CategoryElement;
import com.datin.elms.model.Employee;
import com.datin.elms.service.EmployeeService;

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
        EmployeeService employeeService = new EmployeeService() ;
        int employeeId = Integer.parseInt(req.getParameter("id")) ;
        if (employeeService.checkInUse(employeeId)) {

            Employee employee = employeeService.getEmployee(employeeId);
            List<CategoryElement> roleList = employeeService.getRoles();
            req.setAttribute("roleList", roleList);
            req.setAttribute("employee", employee);
            req.getRequestDispatcher("editemployee.jsp").forward(req, resp);
        }
        else {
            req.setAttribute("msg" , "The selected employee is in Use right now ! ");
            req.getRequestDispatcher("error.jsp").forward(req,resp);
        }
    }
}
