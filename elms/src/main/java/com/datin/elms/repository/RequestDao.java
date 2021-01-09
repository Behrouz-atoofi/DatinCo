package com.datin.elms.repository;

import com.datin.elms.model.Employee;
import com.datin.elms.model.LeaveRequest;
import com.datin.elms.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class RequestDao {


    public List<LeaveRequest> getRequestsByEmployee(Employee employee) {

        Transaction transaction = null;
        List<LeaveRequest> requestList = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();


            Query query = session.createQuery("FROM LeaveRequest request where request.disabled=:disabled and request.employee=:employee") ;
            query.setParameter("disabled",false) ;
            query.setParameter("employee",employee) ;

            requestList = query.list() ;

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return requestList;

    }

    public boolean saveRequest(LeaveRequest leaveRequest) {

        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();

            session.save(leaveRequest);
            transaction.commit();
            return true ;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
                return false ;
            }
            e.printStackTrace();
        }
   return false ;

    }

    public boolean deleteRequestById(int id) {

        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            Date dTime = new Date( );
            SimpleDateFormat df = new SimpleDateFormat("YYYY/MM/dd HH:mm:ss a");
            String last_modified = df.format(dTime);

            transaction = session.beginTransaction();

            String hql = "UPDATE LeaveRequest lvr set lvr.disabled =:disabled , lvr.last_modified =:last_modified "+
                    "WHERE lvr.id= :id";
            Query query = session.createQuery(hql);
            query.setParameter("last_modified", last_modified );
            query.setParameter("disabled", true );
            query.setParameter("id", id);

            query.executeUpdate();
            transaction.commit();
            return true ;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();

            }
            e.printStackTrace();
            return false ;
        }
    }

    public List<LeaveRequest> getRequestsByManager(Employee manager) {

        Transaction transaction = null;
        List<LeaveRequest> leaveRequests = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            leaveRequests = session.createQuery("FROM LeaveRequest lvr join fetch lvr.employee lvre WHERE lvre.manager=:manager")
                    .setParameter("manager", manager).list();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return leaveRequests;

    }

    public boolean updateStatusToAccepted(int requestID) {


        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Date dTime = new Date( );
            SimpleDateFormat df = new SimpleDateFormat("YYYY/MM/dd HH:mm:ss a");
            String last_modified = df.format(dTime);
            String hql = "UPDATE LeaveRequest lvr set lvr.status =:status ,lvr.last_modified=:last_modified WHERE lvr.id= :id";
            Query query = session.createQuery(hql);
            query.setParameter("status", CategoryDao.getElementByName("accepted"));
            query.setParameter("last_modified",last_modified );
            query.setParameter("id", requestID);

            query.executeUpdate();
            transaction.commit();
            return true ;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();

            }
            e.printStackTrace();
            return false ;
        }

    }

    public boolean updateStatusToRejected(int requestID) {

        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            Date dTime = new Date( );
            SimpleDateFormat df = new SimpleDateFormat("YYYY/MM/dd HH:mm:ss a");
            String last_modified = df.format(dTime);

            transaction = session.beginTransaction();

            String hql = "UPDATE LeaveRequest lvr set lvr.status =:status , lvr.last_modified=:last_modified WHERE lvr.id= :id";
            Query query = session.createQuery(hql);
            query.setParameter("status", CategoryDao.getElementByName("rejected"));
            query.setParameter("last_modified",last_modified) ;
            query.setParameter("id", requestID);

            query.executeUpdate();
            transaction.commit();
            return true ;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false ;
        }

    }
}

