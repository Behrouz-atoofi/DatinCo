package com.datin.elms.controller.email;


import com.datin.elms.model.Email;
import com.datin.elms.model.EmailFile;
import com.datin.elms.service.EmailService;
import org.apache.commons.io.IOUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.SQLException;

@WebServlet("/download")
public class DownloadSrv extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int emailId = Integer.parseInt(req.getParameter("id")) ;

        EmailService emailService = new EmailService() ;
        Email email = emailService.getEmailById(emailId);
        EmailFile attachment = emailService.downloadAttachment(email) ;


        byte[] fileBytes = attachment.getContent();

        resp.setContentType("text/csv");
        resp.setContentLength((int) fileBytes.length);
        ServletOutputStream out = null;

        try {
            out = resp.getOutputStream();
            out.write(fileBytes);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            out.close();
            out.flush();
        }


    }
}
