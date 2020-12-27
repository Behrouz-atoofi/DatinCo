package com.datin.elms.controller.email;


import com.datin.elms.model.Email;
import com.datin.elms.model.EmailFile;
import com.datin.elms.model.Employee;
import com.datin.elms.service.CategoryService;
import com.datin.elms.service.EmailService;
import com.datin.elms.util.HibernateUtil;
import org.apache.commons.io.FileUtils;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;

@WebServlet("/sendEmail")
@MultipartConfig(maxFileSize = 16177215)
public class SendEmailSrv extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Email email = new Email();

        String subject = req.getParameter("subject");
        String content = req.getParameter("content");
        String receiverEmail = req.getParameter("receiver");

        EmailService emailService = new EmailService();
        email.setSubject(subject);
        email.setContent(content);
        email.setEmail_receiver(receiverEmail);
        email.setStatus(CategoryService.getElementByName("unread"));
        Employee employee = (Employee) req.getSession().getAttribute("employee");
        email.setEmail_sender(employee.getEmail());

        Part filePart = req.getPart("file");


        // Email Contains file

        if (filePart.getSize() > 0) {
            email.setAttachment(true);
            emailService.save(email);
            InputStream inputStream = null;
            inputStream = filePart.getInputStream();
            File targetFile = new File("src/main/resources/targetFile.tmp");
            FileUtils.copyInputStreamToFile(inputStream, targetFile);
            FileInputStream fis = new FileInputStream(targetFile);
            Blob data = HibernateUtil.getHibernateSession().getLobHelper().createBlob(fis, filePart.getSize());
            EmailFile emailFile = new EmailFile();
            emailFile.setFileName("attachment");
            emailFile.setFileType(filePart.getContentType());
            emailFile.setData(data);
            emailFile.setEmail(email);
            emailService.saveEmailFile(emailFile);
            inputStream.close();
        } else {
            email.setAttachment(false);
            emailService.save(email);
        }

        resp.sendRedirect("email");

    }
}
