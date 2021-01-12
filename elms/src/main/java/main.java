import com.datin.elms.model.*;
import com.datin.elms.repository.EmailDao;
import com.datin.elms.repository.EmployeeDao;
import com.datin.elms.service.EmployeeService;
import com.datin.elms.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class main {
    public static void main(String[] args) throws IOException {

        EmailDao emailDao =  new EmailDao() ;

        Email email  = emailDao.getEmailById(123);


        for (int i = 0 ; i<email.getReceivers().size() ;i++) {
            System.out.println(email.getReceivers().get(i).getEmail());
        }


    }


    public static void loadData() {


        // load Category and Category Elements ...
        Category role = new Category("role");
        Category email_status = new Category("email_status");
        Category leave_status = new Category("leave_status");
        CategoryElement developer = new CategoryElement("developer", "developer",role);
        CategoryElement dataMiner = new CategoryElement("dataMiner", "dataMiner", role);
        CategoryElement analyzer = new CategoryElement("analyzer", "analyzer", role);
        CategoryElement manager = new CategoryElement("manager", "manager", role);
        CategoryElement departmentManager = new CategoryElement("departmentManager", "departmentManager", role);
        CategoryElement founder = new CategoryElement("founder", "founder", role);
        CategoryElement read = new CategoryElement("read", "read", email_status);
        CategoryElement unread = new CategoryElement("unread", "unread", email_status);
        CategoryElement accepted = new CategoryElement("accepted", "accepted", leave_status);
        CategoryElement rejected = new CategoryElement("rejected", "rejected", leave_status);
        CategoryElement pending = new CategoryElement("pending", "pending", leave_status);


        //load administrator ....
        Employee administrator = new Employee() ;

        administrator.setName("Behrouz");
        administrator.setFamily("atoofi");
        administrator.setUsername("admin");
        administrator.setPassword("admin");
        administrator.setEmail("email@");
        administrator.setPhoneNumber("000000000");
        administrator.setManager(administrator);
        administrator.setRole(founder);

        administrator.setDisabled(false);
        administrator.setInUse(false);
        administrator.setActive(true);

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

