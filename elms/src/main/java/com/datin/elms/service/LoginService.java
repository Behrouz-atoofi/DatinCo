package com.datin.elms.service;


import com.datin.elms.model.Employee;
import com.datin.elms.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class LoginService {

    public Employee validate(String username, String password) {

        Transaction transaction = null;
        Employee employee = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // get an user object
            employee = (Employee) session.createQuery("FROM Employee WHERE username = :c_username").setParameter("c_username", username)
                    .uniqueResult();

            if (employee != null && employee.getPassword().equals(password)) {
                return employee;
            }
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return null;
    }



}


