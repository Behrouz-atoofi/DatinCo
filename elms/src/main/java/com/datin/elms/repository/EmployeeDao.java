package com.datin.elms.repository;

import com.datin.elms.model.Category;
import com.datin.elms.model.CategoryElement;
import com.datin.elms.model.Employee;
import com.datin.elms.util.HibernateUtil;
import com.github.mfathi91.time.PersianDate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.time.format.DateTimeFormatter;
import java.util.List;


public class EmployeeDao {


    public List<Employee> getEmployees() {


        List<Employee> employeeList = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            employeeList = session.createQuery("FROM Employee empl where empl.disabled=:disabled", Employee.class).setParameter("disabled", false).list();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

        return employeeList;
    }

    public List<CategoryElement> getRole() {

        List<CategoryElement> roles = null;
        Category role = CategoryDao.getCategoryByName("role");
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {

            String hql = "FROM CategoryElement element WHERE element.category IN (:item)";
            Query query = session.createQuery(hql);
            query.setParameter("item", role);

            roles = query.list();

        } catch (Exception e) {
            e.printStackTrace();
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

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

    }

    public boolean deleteEmployee(int id) {


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
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }

    public boolean updateEmployee(Employee employee) {


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
                    "lastModified=:now " +
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
            query.setParameter("id", employee.getId());
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

    public Employee getEmployeeById(int id) {


        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            return (Employee) session.createQuery("FROM Employee WHERE id=:id").setParameter("id", id).uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    public List<Employee> getManager() {


        List<Employee> managerList = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {

            managerList = (List<Employee>) session.createQuery("FROM Employee emp Where emp.role=:role")
                    .setParameter("role", new Integer[]{4, 5, 6});


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return managerList;
    }

    public boolean checkExistByEmail(String email) {

        Session session = HibernateUtil.getSessionFactory().openSession();

        try {

            String hql = "From Employee emp WHERE emp.email = :email";
            Query query = session.createQuery(hql);
            query.setParameter("email", email);

            if (query.uniqueResult() != null) {
                return true;
            } else
                return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }

    }

    public Employee getEmployeeByEmail(String email) {

        Session session = HibernateUtil.getSessionFactory().openSession();

        try {

            String hql = "From Employee emp Where emp.email=:email";
            Query query = session.createQuery(hql);
            query.setParameter("email", email);
            Employee receiver = (Employee) query.uniqueResult();
            return receiver;
        } catch (Exception e) {
            return null;
        } finally {
            session.close();
        }
    }

    public boolean isExist(String email, String username) {


        Employee employee = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String hql = "FROM Employee emp WHERE emp.username=:username or emp.email=:email";
            Query query = session.createQuery(hql);
            query.setParameter("username", username);
            query.setParameter("email", email);
            employee = (Employee) query.uniqueResult();

            if (employee == null) {
                return false;
            } else {
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }

    public boolean checkExistForUpdate(int id, String username, String email) {

        Employee employee = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {

            String hql = "FROM Employee emp WHERE emp.username=:username or emp.email=:email";
            Query query = session.createQuery(hql);
            query.setParameter("username", username);
            query.setParameter("email", email);
            employee = (Employee) query.uniqueResult();

            if (employee.getId() == id) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }

}

