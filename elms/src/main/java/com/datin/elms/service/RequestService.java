package com.datin.elms.service;

import com.datin.elms.model.Employee;
import com.datin.elms.model.LeaveRequest;
import com.datin.elms.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import sun.plugin2.gluegen.runtime.StructAccessor;
import sun.tracing.dtrace.DTraceProviderFactory;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class RequestService {


    public List<LeaveRequest> getRequestsByEmployee(Employee employee) {

        Transaction transaction = null;
        List<LeaveRequest> requestList = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();

            requestList = session.createQuery("FROM LeaveRequest request where request.employee=:employee")
                    .setParameter("employee", employee).list();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return requestList;

    }


    public void saveRequest(LeaveRequest leaveRequest) {

        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();

            session.save(leaveRequest);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }


    }

    public void deleteRequestById(int id) {

        Transaction transaction= null ;
        LeaveRequest leaveRequest = null ;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
           transaction = session.beginTransaction() ;
           leaveRequest = (LeaveRequest) session.createQuery("FROM LeaveRequest lvr WHERE lvr.id =:id")
                   .setParameter("id",id).uniqueResult() ;

           if (leaveRequest !=null) {
               session.delete(leaveRequest);
               transaction.commit();
           }

        } catch (Exception e ) {
            e.printStackTrace();
        }
    }

    public List<LeaveRequest> getRequestsByManager(Employee manager) {

        Transaction transaction = null;
        List<LeaveRequest> leaveRequests = null  ;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            leaveRequests =session.createQuery("FROM LeaveRequest lvr join fetch lvr.employee lvre WHERE lvre.manager=:manager")
                    .setParameter("manager",manager).list() ;
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return leaveRequests ;

    }

    public void updateStatusToAccepted (int requestID) {


        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            String hql = "UPDATE LeaveRequest lvr set lvr.status =:status WHERE lvr.id= :id";
            Query query = session.createQuery(hql);
            query.setParameter("status", CategoryService.getElementByName("accepted"));
            query.setParameter("id", requestID);

            query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();

        }

    }

    public void updateStatusToRejected (int requestID) {

        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            String hql = "UPDATE LeaveRequest lvr set lvr.status =:status WHERE lvr.id= :id";
            Query query = session.createQuery(hql);
            query.setParameter("status", CategoryService.getElementByName("rejected"));
            query.setParameter("id", requestID);

            query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();

        }

    }
}

