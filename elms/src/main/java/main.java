import com.datin.elms.model.*;
import com.datin.elms.util.HibernateUtil;
import com.github.mfathi91.time.PersianDate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.io.IOException;
import java.time.format.DateTimeFormatter;


public class main {
    public static void main(String[] args) throws IOException {

   //loadData();

    }


    public static void loadData() {


        // load Category and Category Elements ...

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        String now  = PersianDate.now().format(dtf);

        Category role = new Category("role",now,now);
        Category email_status = new Category("email_status",now,now);
        Category leave_status = new Category("leave_status",now,now);
        Category fileType = new Category("file_type",now,now) ;
        CategoryElement developer = new CategoryElement("developer", "developer",role,now,now);
        CategoryElement dataMiner = new CategoryElement("dataMiner", "dataMiner", role,now,now);
        CategoryElement analyzer = new CategoryElement("analyzer", "analyzer", role,now,now);
        CategoryElement manager = new CategoryElement("manager", "manager", role,now,now);
        CategoryElement departmentManager = new CategoryElement("departmentManager", "departmentManager", role,now,now);
        CategoryElement founder = new CategoryElement("founder", "founder", role,now,now);
        CategoryElement read = new CategoryElement("read", "read", email_status,now,now);
        CategoryElement unread = new CategoryElement("unread", "unread", email_status,now,now);
        CategoryElement accepted = new CategoryElement("accepted", "accepted", leave_status,now,now);
        CategoryElement rejected = new CategoryElement("rejected", "rejected", leave_status,now,now);
        CategoryElement pending = new CategoryElement("pending", "pending", leave_status,now,now);
        CategoryElement imageFile = new CategoryElement("image", "image", fileType,now,now);
        CategoryElement textFile = new CategoryElement("text", "text", fileType,now,now);
        CategoryElement pdfFile = new CategoryElement("application/pdf", "application/pdf", fileType,now,now);

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
        administrator.setDateCreated(now);
        administrator.setLastModified(now);

        Transaction transaction = null ;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.save(role);
            session.save(email_status);
            session.save(leave_status);
            session.save(fileType);
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
            session.save(imageFile) ;
            session.save(textFile) ;
            session.save(pdfFile) ;

            session.save(administrator) ;

            transaction.commit();

        }


    }
}

