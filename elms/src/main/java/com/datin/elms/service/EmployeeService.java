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


    public List<Employee> getEmployees() {

        Transaction transaction = null;
        List<Employee> employeeList = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();
            employeeList = session.createQuery("FROM Employee empl", Employee.class).list();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }

            e.printStackTrace();
        }

        return employeeList;
    }

    public List<Category_element> getRole() {
        Transaction transaction = null;
        Category category = new Category();
        category.setId(1);
        List<Category_element> roles = null ;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Query query = session.createQuery("FROM Category_element element WHERE element.category IN (:item)");
            query.setParameter("item", category);

            roles = query.list();
            session.close();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();

        }

        return roles;
    }

    public void saveEmployee(Employee employee) {

        Transaction transaction = null;

        try (final Session session = HibernateUtil.getHibernateSession()) {

            transaction = session.beginTransaction();
            session.save(employee);
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

    }

    public void deleteEmployee(int id) {

        Transaction transaction = null;
        Employee employee = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();

            employee = (Employee) session.createQuery("FROM Employee WHERE id = :id").setParameter("id", id)
                    .uniqueResult();
            if (employee != null) {
                session.delete(employee);
                session.getTransaction().commit();
                session.close();
                transaction.commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateEmployee(Employee employee) {

        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();

            String hql = "UPDATE Employee set name = :name ,family =:family" +
                    ",username =:username " +
                    ",password=:password ," +
                    "phoneNumber=:phonenumber," +
                    "email=:email " + "WHERE id = :id";
            Query query = session.createQuery(hql);
            query.setParameter("name", employee.getName());
            query.setParameter("family", employee.getFamily());
            query.setParameter("username", employee.getUsername());
            query.setParameter("password", employee.getPassword());
            query.setParameter("phonenumber", employee.getPhoneNumber());
            query.setParameter("email", employee.getEmail());
            query.setParameter("id", employee.getId());

            query.executeUpdate();

            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();

        }
    }

    public Employee getEmployeeById(int id) {

        Transaction transaction = null;
        Employee employee = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            employee = (Employee) session.createQuery("FROM Employee WHERE id=:id").setParameter("id", id).uniqueResult();

            return employee;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Employee> getManager() {

        Transaction transaction = null;
        List<Employee> managerList = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();

            managerList = (List<Employee>) session.createQuery("FROM Employee emp Where emp.role=:role")
                    .setParameter("role", new Integer[]{4, 5, 6});

            session.close();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();

        }
        return managerList;
    }
}

