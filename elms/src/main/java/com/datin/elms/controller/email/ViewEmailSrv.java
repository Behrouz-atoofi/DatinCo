package com.datin.elms.controller.email;


import com.datin.elms.model.Email;
import com.datin.elms.model.EmailFile;
import com.datin.elms.repository.EmailDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/viewEmail")
public class ViewEmailSrv extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int emailId = Integer.parseInt(req.getParameter("id"));
        EmailDao emailDao = new EmailDao();
        Email email = emailDao.getEmailById(emailId);
        emailDao.updateStatus(email);

        if (email.getAttachment()) {
            EmailFile emailFile = emailDao.downloadAttachment(email);
            req.setAttribute("emailFile",emailFile);
        }

        req.setAttribute("email", email);
        req.getRequestDispatcher("viewEmail.jsp").forward(req, resp);
    }
}
