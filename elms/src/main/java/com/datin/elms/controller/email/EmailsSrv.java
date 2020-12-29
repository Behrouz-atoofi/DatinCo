package com.datin.elms.controller.email;

import com.datin.elms.controller.mangeEmployee.EditEmployeeToDbSrv;
import com.datin.elms.model.Email;
import com.datin.elms.model.Employee;
import com.datin.elms.repository.EmailDao;
import com.datin.elms.service.EmailService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/email")
public class EmailsSrv extends HttpServlet {
    static Logger log = Logger.getLogger(EditEmployeeToDbSrv.class.getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Employee employee = (Employee) req.getSession().getAttribute("employee");
        EmailService emailService = new EmailService() ;

        List<Email> inbox = emailService.getInboxEmails(employee);
        req.setAttribute("inbox", inbox);

        List<Email> sent = emailService.getSentEmails(employee) ;
        req.setAttribute("sent", sent);

        req.getRequestDispatcher("email.jsp").forward(req, resp);

    }


}
