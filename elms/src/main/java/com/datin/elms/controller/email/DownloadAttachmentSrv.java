package com.datin.elms.controller.email;


import com.datin.elms.model.Attachment;
import com.datin.elms.service.EmailService;
import org.apache.commons.io.IOUtils;

import javax.servlet.ServletException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

import java.sql.SQLException;
import java.util.List;

@WebServlet("/download")
public class DownloadAttachmentSrv extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int attachmentId = Integer.parseInt(req.getParameter("id"));
        EmailService emailService = new EmailService();
        Attachment attachment = emailService.getAttachmentById(attachmentId);

        try {

            resp.setHeader("Content-Disposition", "inline; filename=\"" + "attachment" + "\"");
            OutputStream out = resp.getOutputStream();
            resp.setContentType(attachment.getFileType());

            IOUtils.copy(attachment.getData().getBinaryStream(), out);
            out.flush();
            out.close();

        } catch (IOException | SQLException e) {
            System.out.println(e.toString());


        }

    }

}
