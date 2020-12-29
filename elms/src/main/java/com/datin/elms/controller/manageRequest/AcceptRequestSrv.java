package com.datin.elms.controller.manageRequest;


import com.datin.elms.repository.RequestDao;
import com.datin.elms.service.RequestService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/acceptRequest")
public class AcceptRequestSrv extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int requestId = Integer.parseInt(req.getParameter("id")) ;
        RequestService requestService =  new RequestService() ;
        requestService.acceptRequest(requestId);
        resp.sendRedirect("manageRequests");
    }
}
