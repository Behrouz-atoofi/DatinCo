package com.datin.elms.controller;

import com.datin.elms.model.Attachment;
import com.datin.elms.model.Email;
import com.datin.elms.model.Employee;
import com.datin.elms.model.EmployeeVO;
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
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@WebServlet(urlPatterns = "/email")
@MultipartConfig(maxFileSize = 16177215)
public class EmailController extends HttpServlet {

    private EmailService emailService;
    private Employee employee;
    private EmployeeService employeeService;
    private HttpSession session;

    @Override
    public void init()  {

        emailService = new EmailService();
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

        if (action.equalsIgnoreCase("sendEmail")) {

            String subject = request.getParameter("subject");
            String content = request.getParameter("content");
            String[] receivers = request.getParameterValues("receivers");

            List<Part> fileParts = request.getParts().stream().filter(part -> "file".equals(part.getName()) && part.getSize() > 0).collect(Collectors.toList());


            emailService.sendEmail(employee, subject, receivers, content, fileParts);


        } else if (action.equalsIgnoreCase("viewEmail")) {
            int emailId = Integer.parseInt(request.getParameter("id"));
            Email email = emailService.viewEmail(emailId);
            request.setAttribute("email", email);
            page = "/viewEmail.jsp";
        } else if (action.equalsIgnoreCase("deleteEmail")) {
            int emailId = Integer.parseInt(request.getParameter("id"));
            emailService.deleteEmail(emailId);
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
        } else if (action.equalsIgnoreCase("emailForm")) {
            List<EmployeeVO> employeeList = employeeService.getEmployees();
            List<String> receivers = new ArrayList<>();
            for (EmployeeVO emp : employeeList) {
                receivers.add(emp.getName() + " " + emp.getFamily());
            }
            request.setAttribute("receivers", receivers);
            page = "/emailForm.jsp";
        }

        List<Email> inbox = emailService.getInboxEmails(employee);
        request.setAttribute("inbox", inbox);
        List<Email> sent = emailService.getSentEmails(employee);
        request.setAttribute("sent", sent);

        RequestDispatcher rd = request.getRequestDispatcher(page);
        rd.forward(request, response);

    }

}
