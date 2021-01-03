package com.datin.elms.repository;


import com.datin.elms.model.Employee;
import com.datin.elms.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class LoginDao {

    public Employee validate(String username, String password) {

        Transaction transaction = null;
        Employee employee = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();

            employee = (Employee) session.createQuery("FROM Employee WHERE username = :c_username").setParameter("c_username", username)
                    .uniqueResult();

            if (employee != null && employee.getPassword().equals(password)) {
                return employee;
            }

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


