package com.datin.elms.repository;

import com.datin.elms.model.Employee;
import com.datin.elms.model.LeaveRequest;
import com.datin.elms.util.HibernateUtil;
import com.github.mfathi91.time.PersianDate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class RequestDao {


    public List<LeaveRequest> getRequestsByEmployee(Employee employee) {


        List<LeaveRequest> requestList = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {

            Query query = session.createQuery("FROM LeaveRequest request where request.disabled=:disabled and request.employee=:employee");
            query.setParameter("disabled", false);
            query.setParameter("employee", employee);

            requestList = query.list();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return requestList;

    }

    public void saveRequest(LeaveRequest leaveRequest) {


        Session session = HibernateUtil.getSessionFactory().openSession();
        try {

            Transaction transaction = session.beginTransaction();

            session.flush();
            session.save(leaveRequest);
            transaction.commit();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }


    }

    public boolean deleteRequestById(int id) {


        Session session = HibernateUtil.getSessionFactory().openSession();

        try {

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            String now = PersianDate.now().format(dtf);

            Transaction transaction = session.beginTransaction();

            String hql = "UPDATE LeaveRequest lvr set lvr.disabled =:disabled , lvr.lastModified =:lastModified " +
                    "WHERE lvr.id= :id";
            Query query = session.createQuery(hql);
            query.setParameter("lastModified", now);
            query.setParameter("disabled", true);
            query.setParameter("id", id);
            session.flush();
            query.executeUpdate();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }

    public List<LeaveRequest> getRequestsByManager(Employee manager) {

        List<LeaveRequest> leaveRequests = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String hql = "FROM LeaveRequest lvr join fetch lvr.employee lvre WHERE lvre.manager=:manager and lvre.disabled=:disabled";
            Query query = session.createQuery(hql);
            query.setParameter("manager", manager);
            query.setParameter("disabled", false);

            leaveRequests = query.list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

        return leaveRequests;

    }

    public boolean updateStatusToAccepted(int requestID) {


        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            String now = PersianDate.now().format(dtf);
            Transaction transaction = session.beginTransaction();
            String hql = "UPDATE LeaveRequest lvr set lvr.status =:status ,lvr.lastModified=:lastModified WHERE lvr.id= :id and version=lvr.version";
            Query query = session.createQuery(hql);
            query.setParameter("status", CategoryDao.getElementByName("accepted"));
            query.setParameter("lastModified", now);
            query.setParameter("id", requestID);
            query.executeUpdate();
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }

    }

    public boolean updateStatusToRejected(int requestID) {


        Session session = HibernateUtil.getSessionFactory().openSession();

        try {

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            String now = PersianDate.now().format(dtf);

            Transaction transaction = session.beginTransaction();

            String hql = "UPDATE LeaveRequest lvr set lvr.status =:status , lvr.lastModified=:lastModified WHERE lvr.id= :id";
            Query query = session.createQuery(hql);
            query.setParameter("status", CategoryDao.getElementByName("rejected"));
            query.setParameter("lastModified", now);
            query.setParameter("id", requestID);

            query.executeUpdate();
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }

    }
}

