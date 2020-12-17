package com.datin.elms.controller.mangeEmployee;

import com.datin.elms.model.Employee;
import com.datin.elms.service.EmployeeService;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.io.IOException;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

@WebServlet("/editEmployee")
public class EditEmployeeToDb extends HttpServlet {
    static Logger log = Logger.getLogger(EditEmployeeToDb.class.getName());

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        BasicConfigurator.configure();

        int id = Integer.parseInt(req.getParameter("id") ) ;
        String name = req.getParameter("name") ;
        String family = req.getParameter("family" );
        String username = req.getParameter("username") ;
        String password = req.getParameter("password" );
        String email = req.getParameter("email" );
        String phoneNumber = req.getParameter("phonenumber") ;
        String role = req.getParameter("roleName") ;
        String manager = req.getParameter("manager") ;

        Employee employee = new Employee() ;
        employee.setId(id);
        employee.setName(name);
        employee.setFamily(family);
        employee.setUsername(username);
        employee.setPassword(password);
        employee.setEmail(email);
        employee.setPhoneNumber(phoneNumber);
        employee.setRole(null);
        employee.setManager(null);

        EmployeeService employeeService = new EmployeeService() ;
        employeeService.updateEmployee(employee);

        req.getRequestDispatcher("employees").forward(req, res); ;

    }

}
