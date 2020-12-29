package com.datin.elms.controller.email;


import com.datin.elms.model.Employee;
import com.datin.elms.service.EmailService;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;

@WebServlet("/sendEmail")
@MultipartConfig(maxFileSize = 16177215)
public class SendEmailSrv extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Employee employee = (Employee) req.getSession().getAttribute("employee");
        String subject = req.getParameter("subject");
        String content = req.getParameter("content");
        String receiverEmail = req.getParameter("receiver");
        Part filePart = req.getPart("file");

        EmailService emailService =  new EmailService() ;
        emailService.sendEmail(employee,subject,receiverEmail,content,filePart) ;

        resp.sendRedirect("email");

    }
}
