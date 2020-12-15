package com.datin.elms.service;

import com.datin.elms.model.Category;
import com.datin.elms.model.Category_element;
import com.datin.elms.model.Employee;
import com.datin.elms.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class EmployeeService {


    public List<Employee> getEmployees () {

        Transaction transaction = null ;
        List<Employee> employeeList = null ;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            employeeList = session.createQuery("FROM Employee",Employee.class).list();
            transaction.commit();
        }catch (Exception e) {
                if (transaction != null) {
                    transaction.rollback();
                }
                e.printStackTrace();
            }

        return employeeList ;
    }
    public List<Category_element> getRole () {
        Transaction transaction = null ;
        List<Category_element> category_elements = null ;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            
            category_elements = session.createQuery("FROM Category WHERE id =:role").setParameter("role",role).list();
            transaction.commit();
        }catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

        return category_elements ;
    }


}
