package com.datin.elms.repository;

import com.datin.elms.model.*;
import com.datin.elms.util.HibernateUtil;
import com.github.mfathi91.time.PersianDate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class EmployeeDao {


    public List<Employee> getEmployees() {


        List<Employee> employeeList ;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            employeeList = session.createQuery("FROM Employee empl where empl.disabled=:disabled", Employee.class).setParameter("disabled", false).list();

        } finally {
            session.close();
        }

        return employeeList;
    }

    public List<CategoryElement> getRole() {

        List<CategoryElement> roles ;
        Category role = CategoryDao.getCategoryByName("role");
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {

            String hql = "FROM CategoryElement element WHERE element.category IN (:item)";
            Query query = session.createQuery(hql);
            query.setParameter("item", role);
            roles = query.list();

        } finally {
            session.close();
        }

        return roles;
    }

    public void saveEmployee(Employee employee) {

        Session session = HibernateUtil.getHibernateSession();

        try {

            Transaction transaction = session.beginTransaction();
            session.save(employee);
            transaction.commit();

        } finally {
            session.close();
        }

    }

    public void deleteEmployee(int id) {


        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            String now = PersianDate.now().format(dtf);

            Transaction transaction = session.beginTransaction();

            String hql = "UPDATE Employee set disabled = :disabled , lastModified=:now " + "WHERE id = :id";
            Query query = session.createQuery(hql);
            query.setParameter("disabled", true);
            query.setParameter("now", now);
            query.setParameter("id", id);
            query.executeUpdate();
            transaction.commit();

        } finally {
            session.close();
        }
    }

    public void updateEmployee(Employee employee) {


        Session session = HibernateUtil.getSessionFactory().openSession();

        try {

            Transaction transaction = session.beginTransaction();
            String hql = "UPDATE Employee set name = :name ,family =:family" +
                    ",username =:username " +
                    ",password=:password ," +
                    "phoneNumber=:phonenumber," +
                    "email=:email ," +
                    "role=:role  ," +
                    "active=:active ," +
                    "lastModified=:now ," +
                    "manager=:manager " +
                    "WHERE id = :id";
            Query query = session.createQuery(hql);
            query.setParameter("name", employee.getName());
            query.setParameter("family", employee.getFamily());
            query.setParameter("username", employee.getUsername());
            query.setParameter("password", employee.getPassword());
            query.setParameter("phonenumber", employee.getPhoneNumber());
            query.setParameter("email", employee.getEmail());
            query.setParameter("role", employee.getRole());
            query.setParameter("active", employee.isActive());
            query.setParameter("now", employee.getLastModified());
            query.setParameter("manager",employee.getManager()) ;
            query.setParameter("id", employee.getId());
            query.executeUpdate();
            transaction.commit();
        } finally {
            session.close();
        }
    }

    public Employee getEmployeeById(int id) {


        Session session = HibernateUtil.getSessionFactory().openSession();
        Employee employee ;
        try {
            String hql = "from Employee WHERE id=:id" ;
            Query query = session.createQuery(hql) ;
            query.setParameter("id",id) ;
            employee = (Employee)query.uniqueResult() ;
        } finally {
            session.close();
        }
        return employee;
    }

    public Employee getEmployeeByEmail(String email) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        Employee receiver ;
        try {

            String hql = "From Employee emp Where emp.email=:email";
            Query query = session.createQuery(hql);
            query.setParameter("email", email);
             receiver = (Employee) query.uniqueResult();
        } finally {
            session.close();
        }
        return receiver;
    }

    public boolean isExist(String email, String username) {


        Employee employee ;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String hql = "FROM Employee emp WHERE emp.username=:username or emp.email=:email";
            Query query = session.createQuery(hql);
            query.setParameter("username", username);
            query.setParameter("email", email);
            employee = (Employee) query.uniqueResult();
        } finally {
            session.close();
        }

        if (employee == null) {
            return false;
        } else {
            return true;
        }
    }

    public boolean checkExistForUpdate(int id, String username, String email) {


        // In the first step this method checks existence employee in db and then checks it for update .
        Employee employee;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {

            String hql = "FROM Employee emp WHERE emp.username=:username or emp.email=:email";
            Query query = session.createQuery(hql);
            query.setParameter("username", username);
            query.setParameter("email", email);
            employee = (Employee) query.uniqueResult();

        } finally {
            session.close();
        }

            if (employee.getId() == id) {
                return true;
            } else {
                return false;
            }

    }

    public void deActiveEmployee(int employeeId) {

        Session session = HibernateUtil.getSessionFactory().openSession() ;

        try {
          Transaction transaction = session.beginTransaction() ;

          String hql = "Update Employee emp set active=:active where emp.id=:id" ;

          Query query = session.createQuery(hql) ;
            query.setParameter("active" ,false );
            query.setParameter("id",employeeId) ;
            query.executeUpdate() ;
            transaction.commit();
        } finally {
            session.close();
        }

    }

    public void ActiveEmployee (int employeeId) {

        Session session = HibernateUtil.getSessionFactory().openSession() ;

        try {

            Transaction transaction = session.beginTransaction() ;
            String hql = "Update Employee emp set active=:active where emp.id=:id" ;

            Query query = session.createQuery(hql) ;
            query.setParameter("active" ,true );
            query.setParameter("id",employeeId) ;
            query.executeUpdate() ;
            transaction.commit();
        } finally {
            session.close();
        }
    }

    public List<EmployeeVO> getManagers () {

        Session session= HibernateUtil.getSessionFactory().openSession() ;
        List<Employee> managerList ;

        List<CategoryElement> managerRole = new ArrayList<>() ;
        managerRole.add(CategoryDao.getElementByName("manager")) ;
        managerRole.add(CategoryDao.getElementByName("departmentManager")) ;
        managerRole.add(CategoryDao.getElementByName("founder")) ;

        try {
            Transaction transaction =session.beginTransaction() ;

            String hql = "From Employee emp where emp.role in (:managerRole) and emp.disabled=:disabled" ;
            Query query = session.createQuery(hql) ;
            query.setParameterList("managerRole", managerRole) ;
            query.setParameter("disabled",false) ;
            managerList = query.list() ;
        } finally {
         session.close();
        }
        List<EmployeeVO> managers = new ArrayList<>() ;
        for (Employee manager:managerList) {
            EmployeeVO employee = new EmployeeVO() ;
            employee.setId(manager.getId());
            employee.setName(manager.getName());
            employee.setFamily(manager.getFamily());
            employee.setRole(manager.getRole());
            managers.add(employee) ;
        }
        return managers ;
    }

}

