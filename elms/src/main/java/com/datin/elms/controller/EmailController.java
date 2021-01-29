package com.datin.elms.controller;

import com.datin.elms.model.Attachment;
import com.datin.elms.model.Email;
import com.datin.elms.model.Employee;
import com.datin.elms.service.EmailService;
import com.datin.elms.service.EmployeeService;
import org.apache.commons.io.IOUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;


@WebServlet(urlPatterns = "/email")
@MultipartConfig(maxFileSize = 16177215)
public class EmailController extends HttpServlet {

    private EmailService emailService;
    private Attachment attachment;
    private Employee employee;
    private EmployeeService employeeService;
    private HttpSession session;

    @Override
    public void init() throws ServletException {

        emailService = new EmailService();
        attachment = new Attachment();
        employee = new Employee();
        employeeService = new EmployeeService();

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");
        String page = "/emails.jsp";
        session = request.getSession();
        employee = (Employee) session.getAttribute("employee");


        if (action.equalsIgnoreCase("emails")) {
            page = "/emails.jsp";

        } else if (action.equalsIgnoreCase("sendEmail")) {

            String subject = request.getParameter("subject");
            String content = request.getParameter("content");
            String receiver = request.getParameter("receiver");
            List<Part> fileParts = request.getParts().stream().filter(part -> "file".equals(part.getName()) && part.getSize() > 0).collect(Collectors.toList());

            if (employeeService.checkExistByEmail(receiver)) {
                System.out.println("Exists");
                System.out.println(subject);
                System.out.println(content);
                System.out.println(receiver);
                System.out.println(fileParts.size());
                emailService.sendEmail(employee, subject, receiver, content, fileParts);
                page = "/emails.jsp";
            } else {
                request.setAttribute("msg", "One or Some receivers not exist in DB");
                page = "/error.jsp";
            }


        } else if (action.equalsIgnoreCase("viewEmail")) {
            int emailId = Integer.parseInt(request.getParameter("id"));
            Email email = emailService.viewEmail(emailId);
            request.setAttribute("email", email);
            page = "/viewEmail.jsp";
        } else if (action.equalsIgnoreCase("deleteEmail")) {
            int emailId = Integer.parseInt(request.getParameter("id"));
            emailService.deleteEmail(emailId);
            page = "/emails.jsp";
        } else if (action.equalsIgnoreCase("downloadAttachment")) {

            int attachmentId = Integer.parseInt(request.getParameter("id"));
            Attachment attachment = emailService.getAttachmentById(attachmentId);

            try {

                response.setHeader("Content-Disposition", "inline; filename=\"" + "attachment" + "\"");
                OutputStream out = response.getOutputStream();
                response.setContentType(attachment.getFileType().getName());

                IOUtils.copy(attachment.getData().getBinaryStream(), out);
                out.flush();
                out.close();

            } catch (IOException | SQLException e) {
                System.out.println(e.toString());


            }
        }

        List<Email> inbox = emailService.getInboxEmails(employee);
        request.setAttribute("inbox", inbox);
        List<Email> sent = emailService.getSentEmails(employee);
        request.setAttribute("sent", sent);

        RequestDispatcher rd = request.getRequestDispatcher(page);
        rd.forward(request, response);

    }

}
