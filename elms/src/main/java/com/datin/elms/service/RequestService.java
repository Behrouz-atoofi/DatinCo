package com.datin.elms.service;

import com.datin.elms.model.Employee;
import com.datin.elms.model.LeaveRequest;
import com.datin.elms.repository.CategoryDao;
import com.datin.elms.repository.RequestDao;
import com.github.mfathi91.time.PersianDate;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class RequestService {
    static Logger log = Logger.getLogger(EmailService.class.getName());
    RequestDao requestDao = new RequestDao();

    public void deleteLeaveRequest(int requestId) {
        requestDao.deleteRequestById(requestId);
    }

    public List<LeaveRequest> getMyRequest(Employee employee) {
        BasicConfigurator.configure();
        List<LeaveRequest> leaveRequests = requestDao.getRequestsByEmployee(employee);
        return leaveRequests;
    }

    public void sendRequest(String fromDate, String toDate, String reason, Employee employee) {
        BasicConfigurator.configure();

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        String now = PersianDate.now().format(dtf);


        LeaveRequest leaveRequest = new LeaveRequest();
        leaveRequest.setFrom_date(fromDate);
        leaveRequest.setTo_date(toDate);
        leaveRequest.setReason(reason);
        leaveRequest.setEmployee(employee);
        leaveRequest.setDateCreated(now);
        leaveRequest.setLastModified(now);
        leaveRequest.setStatus(CategoryDao.getElementByName("pending"));

        RequestDao requestDao = new RequestDao();
        requestDao.saveRequest(leaveRequest);
    }

    public void acceptRequest(int requestId) {
        BasicConfigurator.configure();
        RequestDao requestDao = new RequestDao();
        requestDao.updateStatusToAccepted(requestId);

    }

    public void rejectRequest(int requestId) {
        BasicConfigurator.configure();
        requestDao.updateStatusToRejected(requestId);

    }

    public List<LeaveRequest> getSubsetRequests(Employee employee) {
        BasicConfigurator.configure();
        List<LeaveRequest> leaveRequests = requestDao.getRequestsByManager(employee);
        log.info("Number of subset requests is : " + leaveRequests.size());
        return leaveRequests;
    }
}
