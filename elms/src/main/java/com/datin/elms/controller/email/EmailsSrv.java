package com.datin.elms.controller.email;

import com.datin.elms.controller.mangeEmployee.EditEmployeeToDbSrv;
import com.datin.elms.model.Email;
import com.datin.elms.model.Employee;
import com.datin.elms.service.EmailService;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

@WebServlet("/email")
public class EmailsSrv extends HttpServlet {
    static Logger log = Logger.getLogger(EditEmployeeToDbSrv.class.getName());
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Employee employee = (Employee) req.getSession().getAttribute("employee") ;
        EmailService emailService = new EmailService() ;
        List<Email> emails = emailService.getEmailByAddress(employee.getEmail()) ;
        req.setAttribute("emails",emails);
        req.getRequestDispatcher("email.jsp").forward(req,resp);
        log.info(emails.size());
    }


}
