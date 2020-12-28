package com.datin.elms.controller.leaveRequest;


import com.datin.elms.model.Employee;
import com.datin.elms.model.LeaveRequest;
import com.datin.elms.repository.CategoryDao;
import com.datin.elms.repository.RequestDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/sendRequest")
public class SendRequestSrv extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String fromDate = req.getParameter("fromDate") ;
        String toDate = req.getParameter("toDate") ;
        String reason =  req.getParameter("reason");
        Employee employee = (Employee) req.getSession().getAttribute("employee") ;

        LeaveRequest leaveRequest =  new LeaveRequest() ;
        leaveRequest.setFrom_date(fromDate);
        leaveRequest.setTo_date(toDate);
        leaveRequest.setReason(reason);
        leaveRequest.setEmployee(employee);
        leaveRequest.setStatus(CategoryDao.getElementByName("pending"));

        RequestDao requestDao = new RequestDao() ;
        requestDao.saveRequest(leaveRequest);

        resp.sendRedirect("myRequests");

    }
}
