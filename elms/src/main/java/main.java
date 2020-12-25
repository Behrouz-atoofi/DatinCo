import com.datin.elms.model.*;
import com.datin.elms.service.CategoryService;
import com.datin.elms.service.EmailService;
import com.datin.elms.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;

public class main {
    public static void main(String[] args) throws IOException {


//        EmailService emailService = new EmailService();
//        Email email = emailService.getEmailById(57);
//
//        byte[] file = email.getAttach() ;
//
//        System.out.println(file.length);
//        FileOutputStream outputStream = new FileOutputStream("D:/Test/Photo/MyPuppy.jpg");
//        outputStream.write(file);
//        outputStream.close();
//        CommonsMultipartFile[] fileUpload ;


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

