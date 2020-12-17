import com.datin.elms.model.Category;
import com.datin.elms.model.Category_element;
import com.datin.elms.model.Email;
import com.datin.elms.service.EmailService;
import com.datin.elms.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class main {
    public static void main(String[] args) {

        Email email = new Email();
        email.setSubject("hello");
        email.setContent("HOW ARE YOU");
        email.setEmail_sender("ALi@gmail.com");
        email.setEmail_receiver("mr.atoufi@gmail.com");

        EmailService emailService = new EmailService();
        emailService.save(email);

        Transaction transaction = null;
        List<Email> emails = null;
        String address = "mr.atoufi@gmail.com" ;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();
            emails = session.createQuery("FROM Email eml where eml.email_receiver=:email").setParameter("email", address).list();

            for (Email es : emails
            ) {
                System.out.println(es.getEmail_sender());
            }
            ;
//        } catch (Exception e) {
//            if (transaction != null) {
//                //transaction.rollback();
//            }
//
//            e.printStackTrace();
//        }

        }
    }

    public static void loadData() {
        Category role = new Category(1,"role");
        Category email_status = new Category(2,"email_status");
        Category leave_status = new Category(3,"leave_status");
        Category_element developer = new Category_element(1,"developer", "developer",role);
        Category_element dataMiner = new Category_element(2,"dataMiner", "dataMiner", role);
        Category_element analyzer = new Category_element(3,"analyzer", "analyzer", role);
        Category_element manager = new Category_element(4,"manager", "manager", role);
        Category_element departmentManager = new Category_element(5,"departmentManager", "departmentManager", role);
        Category_element founder = new Category_element(6,"founder", "founder", role);
        Category_element read = new Category_element(7,"read", "read", email_status);
        Category_element unread = new Category_element(8,"unread", "unread", email_status);
        Category_element accepted = new Category_element(9,"accepted", "accepted", leave_status);
        Category_element rejected = new Category_element(10,"rejected", "rejected", leave_status);

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

            transaction.commit();

        }


    }
}

