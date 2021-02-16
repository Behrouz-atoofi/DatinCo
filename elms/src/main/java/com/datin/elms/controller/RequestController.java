package com.datin.elms.controller;

import com.datin.elms.model.Employee;
import com.datin.elms.model.LeaveRequest;
import com.datin.elms.service.RequestService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@WebServlet(urlPatterns = "/request")
public class RequestController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    RequestService requestService;
    private HttpSession session;
    private Employee employee;
    private List<LeaveRequest> leaveRequests;

    @Override
    public void init() {
        employee = new Employee();
        leaveRequests = new ArrayList<>();
        requestService = new RequestService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");

        if (action.equalsIgnoreCase("deleteRequest")) {
            int requestId = Integer.parseInt(request.getParameter("id"));
            requestService.deleteLeaveRequest(requestId);

        } else if (action.equalsIgnoreCase("sendRequest")) {

            String fromDate = request.getParameter("fromDate");
            String toDate = request.getParameter("toDate");
            String reason = request.getParameter("reason");
            requestService.sendRequest(fromDate, toDate, reason, employee);

        }

        session = request.getSession();
        employee = (Employee) session.getAttribute("employee");
        leaveRequests = requestService.getMyRequest(employee);
        request.setAttribute("leaveRequests", leaveRequests);
        RequestDispatcher rd = request.getRequestDispatcher("/viewRequests.jsp");
        rd.forward(request, response);

    }

}







