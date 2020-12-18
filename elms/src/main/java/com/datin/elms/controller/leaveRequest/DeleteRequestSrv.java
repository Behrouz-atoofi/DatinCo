package com.datin.elms.controller.leaveRequest;


import com.datin.elms.service.RequestService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteRequest")
public class DeleteRequestSrv extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int requestID = Integer.parseInt(req.getParameter("id")) ;

        RequestService requestService = new RequestService() ;
        requestService.deleteRequestById(requestID) ;

        resp.sendRedirect("requests");


    }
}
