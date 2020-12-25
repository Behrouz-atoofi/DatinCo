package com.datin.elms.controller.email;


import com.datin.elms.model.Email;
import com.datin.elms.model.Employee;
import com.datin.elms.service.CategoryService;
import com.datin.elms.service.EmailService;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;

@WebServlet("/sendEmail")
@MultipartConfig(maxFileSize = 16177215)
public class SendEmailSrv extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String subject = req.getParameter("subject");
        String content = req.getParameter("content");
        String receiverEmail = req.getParameter("receiver");
        Part filePart = req.getPart("file");
        System.out.println(filePart.getName());
        System.out.println(filePart.getContentType());
        InputStream inputStream = null;

        if (filePart != null ) {
            inputStream = filePart.getInputStream();

        }
        byte[] fileBytes = new byte[inputStream.read()];

        Email email = new Email() ;
        email.setSubject(subject);
        email.setContent(content);
        email.setEmail_receiver(receiverEmail);
        email.setStatus(CategoryService.getElementByName("unread"));

        if (inputStream != null && fileBytes.length>0 ) {
            email.setAttach(fileBytes);
        }
        inputStream.close();

        Employee employee = (Employee) req.getSession().getAttribute("employee");
        email.setEmail_sender(employee.getEmail());
        EmailService emailService = new EmailService() ;
        emailService.save(email);
        resp.sendRedirect("email");
    }


}


