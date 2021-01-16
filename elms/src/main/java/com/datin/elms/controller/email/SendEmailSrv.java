package com.datin.elms.controller.email;


import com.datin.elms.model.Employee;
import com.datin.elms.service.EmailService;
import com.datin.elms.service.EmployeeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/sendEmail")
@MultipartConfig(maxFileSize = 16177215)
public class SendEmailSrv extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Employee employee = (Employee) req.getSession().getAttribute("employee");
        String subject = req.getParameter("subject");
        String content = req.getParameter("content");
        String receiver = req.getParameter("receiver");
        List<Part> fileParts = req.getParts().stream().filter(part -> "file".equals(part.getName()) && part.getSize() > 0).collect(Collectors.toList());

        EmployeeService employeeService = new EmployeeService() ;

        if (employeeService.checkEmployeeByEmail(receiver)) {
            EmailService emailService = new EmailService();
            emailService.sendEmail(employee, subject, receiver, content, fileParts);
            resp.sendRedirect("email");
        }else {
            req.setAttribute("msg","One or Some receivers don't exist in DB");
            req.getRequestDispatcher("error.jsp").forward(req,resp);
            }

    }
}
