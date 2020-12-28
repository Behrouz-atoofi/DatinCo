import com.datin.elms.model.*;
import com.datin.elms.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.IOException;

public class main {
    public static void main(String[] args) throws IOException {


        Transaction transaction = null;
        EmailFile emailFile = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            emailFile = (EmailFile) session.createQuery("FROM EmailFile emf WHERE emf.email=:email")
                    .setParameter("email", 108).uniqueResult();


            emailFile.getFileType();
        }


    }


    public static void loadData() {


        // load Category and Category Elements ...
        Category role = new Category(1,"role");
        Category email_status = new Category(2,"email_status");
        Category leave_status = new Category(3,"leave_status");
        CategoryElement developer = new CategoryElement(1,"developer", "developer",role);
        CategoryElement dataMiner = new CategoryElement(2,"dataMiner", "dataMiner", role);
        CategoryElement analyzer = new CategoryElement(3,"analyzer", "analyzer", role);
        CategoryElement manager = new CategoryElement(4,"manager", "manager", role);
        CategoryElement departmentManager = new CategoryElement(5,"departmentManager", "departmentManager", role);
        CategoryElement founder = new CategoryElement(6,"founder", "founder", role);
        CategoryElement read = new CategoryElement(7,"read", "read", email_status);
        CategoryElement unread = new CategoryElement(8,"unread", "unread", email_status);
        CategoryElement accepted = new CategoryElement(9,"accepted", "accepted", leave_status);
        CategoryElement rejected = new CategoryElement(10,"rejected", "rejected", leave_status);
        CategoryElement pending = new CategoryElement(11,"pending", "pending", leave_status);


        //load administrator ....
        Employee administrator = new Employee() ;
        administrator.setId(1);
        administrator.setName("Behrouz");
        administrator.setFamily("atoofi");
        administrator.setUsername("admin");
        administrator.setPassword("admin");
        administrator.setEmail("email@");
        administrator.setPhoneNumber("000000000");
        administrator.setManager(administrator);
        administrator.setRole(founder);

        Transaction transaction = null ;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.save(role);
            session.save(email_status);
            session.save(leave_status);
            session.save(developer);
            session.save(dataMiner);
            session.save(analyzer);
            session.save(manager);
            session.save(departmentManager);
            session.save(founder);
            session.save(read);
            session.save(unread);
            session.save(accepted);
            session.save(rejected);
            session.save(pending) ;

            session.save(administrator) ;

            transaction.commit();

        }


    }
}

