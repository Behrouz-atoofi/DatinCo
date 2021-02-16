package com.datin.elms.controller;

import com.datin.elms.model.Employee;
import com.datin.elms.model.LeaveRequest;
import com.datin.elms.service.LoginService;
import com.datin.elms.service.RequestService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/manageRequests")
public class ManageRequestController extends HttpServlet {
    private HttpSession session;
    private Employee employee;
    private RequestService requestService;
    private LoginService loginService;

    @Override
    public void init() {
        employee = new Employee();
        requestService = new RequestService();
        loginService = new LoginService();

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");
        session = request.getSession();
        employee = (Employee) session.getAttribute("employee");
        String nextPage = "/manageRequests.jsp";

        if (loginService.isManager(employee)) {

            if (action.equalsIgnoreCase("acceptRequest")) {
                int requestId = Integer.parseInt(request.getParameter("id"));
                requestService.acceptRequest(requestId);
            } else if (action.equalsIgnoreCase("rejectRequest")) {
                int requestId = Integer.parseInt(request.getParameter("id"));
                requestService.rejectRequest(requestId);

            }
            List<LeaveRequest> leaveRequests = requestService.getSubsetRequests(employee);
            request.setAttribute("leaveRequests", leaveRequests);
        } else {
            request.setAttribute("msg", "You don't have enough permission to access this page .");
            nextPage = "/error.jsp";
        }

        RequestDispatcher rd = request.getRequestDispatcher(nextPage);
        rd.forward(request, response);


    }


}
