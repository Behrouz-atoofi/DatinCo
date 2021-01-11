package com.datin.elms.repository;

import com.datin.elms.model.Category;
import com.datin.elms.model.CategoryElement;
import com.datin.elms.model.Employee;
import com.datin.elms.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.nio.file.SecureDirectoryStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;


public class EmployeeDao {


    public List<Employee> getEmployees() {

        Transaction transaction = null;
        List<Employee> employeeList = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();
            employeeList = session.createQuery("FROM Employee empl where empl.disabled=:disabled", Employee.class).setParameter("disabled",false).list();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }

            e.printStackTrace();
        }

        return employeeList;
    }

    public List<CategoryElement> getRole() {
        Transaction transaction = null;
        Category role = CategoryDao.getCategoryByName("role");
        List<CategoryElement> roles = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Query query = session.createQuery("FROM CategoryElement element WHERE element.category IN (:item)");
            query.setParameter("item", role);

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

    public boolean saveEmployee(Employee employee) {

        Transaction transaction = null;

        try (final Session session = HibernateUtil.getHibernateSession()) {

            transaction = session.beginTransaction();
            session.save(employee);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }

    }

    public boolean deleteEmployee(int id) {

        Transaction transaction = null;



        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Date dTime = new Date( );
            SimpleDateFormat df = new SimpleDateFormat("YYYY/MM/dd HH:mm:ss a");
            String last_modified = df.format(dTime);

            transaction = session.beginTransaction();

            String hql = "UPDATE Employee set disabled = :disabled , last_modified=:last_modified " + "WHERE id = :id";
            Query query = session.createQuery(hql);
            query.setParameter("disabled", true);
            query.setParameter("last_modified",last_modified) ;
            query.setParameter("id", id);
            query.executeUpdate();
            transaction.commit();

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateEmployee(Employee employee) {


        setInUse(employee.getId(),true) ;

        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();

            String hql = "UPDATE Employee set name = :name ,family =:family" +
                    ",username =:username " +
                    ",password=:password ," +
                    "phoneNumber=:phonenumber," +
                    "email=:email ," +
                    "role=:role  ," +
                    "isActive=:isActive ," +
                    "inUse=:inUse ," +
                    "last_modified=:last_modified " +
                    "WHERE id = :id";
            Query query = session.createQuery(hql);
            query.setParameter("name", employee.getName());
            query.setParameter("family", employee.getFamily());
            query.setParameter("username", employee.getUsername());
            query.setParameter("password", employee.getPassword());
            query.setParameter("phonenumber", employee.getPhoneNumber());
            query.setParameter("email", employee.getEmail());
            query.setParameter("role", employee.getRole());
            query.setParameter("isActive", employee.isActive());
            query.setParameter("inUse", employee.isInUse());
            query.setParameter("last_modified", employee.getLast_modified());
            query.setParameter("id", employee.getId());


            query.executeUpdate();

            // commit transaction
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
                return false;
            }
            e.printStackTrace();

        }
        return false;
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

    public boolean setInUse (int id , boolean inUse) {

        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();

            String hql = "UPDATE Employee set inUse = :inUse " +
                    "WHERE id = :id";
            Query query = session.createQuery(hql);
            query.setParameter("inUse", inUse);
            query.setParameter("id", id);

            query.executeUpdate();
            transaction.commit();

            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
                return false;
            }
            e.printStackTrace();

        }
        return false;
    }

    public boolean isInUse (int employeeId) {

        Transaction transaction= null ;

        Employee employee = null ;

        try(Session session=HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction() ;
            employee = (Employee) session.createQuery("From Employee emp where id=:id").setParameter("id",employeeId).uniqueResult() ;
            if (employee.isInUse())
                return false ;
            return true ;

        }
    }

    public boolean checkEmployeeByEmail (String email) {

        Transaction transaction = null ;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction() ;

            String hql = "From Employee emp WHERE emp.email = :email" ;

            Query query = session.createQuery(hql) ;
            query.setParameter("email",email) ;

            if ( query.uniqueResult() != null ) {
                return true ;
            } else
                return false ;
        }catch (Exception e ) {
            e.printStackTrace();
            return false ;
        }

    }

    public Employee getEmployeeByEmail (String email) {

        Transaction transaction = null ;

        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction() ;
            Employee receiver = null ;
            String hql = "From Employee emp Where emp.email=:email" ;

            Query query = session.createQuery(hql) ;
            query.setParameter("email",email) ;
            receiver = (Employee) query.uniqueResult() ;

            return receiver ;
        } catch (Exception e ) {
            return null;
        }
    }

}

