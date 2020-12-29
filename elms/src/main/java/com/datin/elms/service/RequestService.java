package com.datin.elms.service;

import com.datin.elms.model.Email;
import com.datin.elms.model.Employee;
import com.datin.elms.model.LeaveRequest;
import com.datin.elms.repository.CategoryDao;
import com.datin.elms.repository.RequestDao;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import javax.persistence.Basic;
import java.util.List;

public class RequestService {
    static Logger log = Logger.getLogger(EmailService.class.getName());
    RequestDao requestDao = new RequestDao() ;

    public void  deleteLeaveRequest (int requestId) {
        BasicConfigurator.configure();

        if ( requestDao.deleteRequestById(requestId) )  {
            log.info("request deleted successfully...");
        } else {
            log.warn("request couldn't to be deleted ...");
        }


    }

    public List<LeaveRequest> getMyRequest(Employee employee) {
        BasicConfigurator.configure();
        List<LeaveRequest> leaveRequests  = requestDao.getRequestsByEmployee(employee) ;
        log.info("number of my requests is : " + leaveRequests.size());
        return leaveRequests ;

    }

    public void sendRequest (String fromDate , String toDate ,String reason , Employee employee) {
        BasicConfigurator.configure();
        LeaveRequest leaveRequest =  new LeaveRequest() ;
        leaveRequest.setFrom_date(fromDate);
        leaveRequest.setTo_date(toDate);
        leaveRequest.setReason(reason);
        leaveRequest.setEmployee(employee);
        leaveRequest.setStatus(CategoryDao.getElementByName("pending"));

        RequestDao requestDao = new RequestDao() ;
        if (requestDao.saveRequest(leaveRequest)) {
            log.info("Request saved successfully...");
        } else {
            log.warn("Request couldn't to be sent ...");
        }
    }

    public void acceptRequest (int requestId) {
        BasicConfigurator.configure();
        RequestDao requestDao = new RequestDao() ;

        if (requestDao.updateStatusToAccepted(requestId)) {
            log.info("Request accepted Successfully ...");
        } else {
            log.warn("The status couldn't to be changed ...");
        }


    }

    public void rejectRequest ( int requestId) {
        BasicConfigurator.configure();

        if ( requestDao.updateStatusToRejected(requestId)) {
            log.info("Request rejected successfully ...");
        } else {
            log.warn("The status of Request couldn't be changed ...");
        }
    }

    public List<LeaveRequest> getSubsetRequests (Employee employee) {
        BasicConfigurator.configure();


        List<LeaveRequest> leaveRequests = requestDao.getRequestsByEmployee(employee) ;
        log.info("Number of subset requests is : " + leaveRequests.size() );

        return leaveRequests ;
    }
}
