package com.datin.elms.controller.mangeEmployee;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.io.IOException;

import com.datin.elms.service.EmployeeService;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

@WebServlet("/editEmployee")
public class EditEmployeeToDbSrv extends HttpServlet {
    static Logger log = Logger.getLogger(EditEmployeeToDbSrv.class.getName());

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
        int roleId = Integer.parseInt(req.getParameter("roleName")) ;
        String manager = req.getParameter("manager") ;
        int isActive = Integer.parseInt(req.getParameter("active")) ;

        EmployeeService employeeService = new EmployeeService() ;
        employeeService.updateEmployee(id,name,family,username,password,email,phoneNumber,roleId,isActive,false);

        req.getRequestDispatcher("employees").forward(req, res); ;

    }

}
