package com.datin.elms.repository;


import com.datin.elms.model.Employee;
import com.datin.elms.util.HibernateUtil;
import org.hibernate.Session;

public class LoginDao {

    public Employee validate(String username, String password) {


        Employee employee ;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {

            employee = (Employee) session.createQuery("FROM Employee WHERE username = :c_username").setParameter("c_username", username)
                    .uniqueResult();

        }  finally {
            session.close();
        }

            if (employee != null && employee.getPassword().equals(password)) {
                return employee;
            }
        return null;
    }

}


