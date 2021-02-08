package com.datin.elms.repository;

import com.datin.elms.model.Attachment;
import com.datin.elms.model.Email;
import com.datin.elms.model.Employee;
import com.datin.elms.util.HibernateUtil;
import com.github.mfathi91.time.PersianDate;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class EmailDao {


    public List<Email> getEmailByReceiver(Employee receiver) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Integer> idList = new ArrayList<>() ;
        List<Email> emailList = new ArrayList<>() ;
        try {

            String sql = "select t_email.id from t_email JOIN mm_email_employee on t_email.id=mm_email_employee.c_emailId where c_EmployeeId=:receiver and t_email.c_disabled=false" ;
            SQLQuery query = session.createSQLQuery(sql);
            query.setParameter("receiver",receiver.getId()) ;
            idList.addAll(query.list()) ;
            for (int id : idList) {
                emailList.add((Email)session.createQuery("From Email where id=:id").setParameter("id",id).uniqueResult());
            }
            return emailList;
        }  finally {
            session.close();
        }
    }

    public List<Email> getEmailBySender(Employee sender) {


        Session session = HibernateUtil.getSessionFactory().openSession();
        try {

            String hql = "FROM Email eml where eml.sender=:sender and eml.disabled=:disabled";
            Query query = session.createQuery(hql);
            query.setParameter("sender", sender);
            query.setParameter("disabled", false);
            return query.list();

        }  finally {
            session.close();
        }

    }

    public void save(Email email) {

        Session session = HibernateUtil.getSessionFactory().openSession();

        try {

            Transaction transaction = session.beginTransaction();
            session.save(email);
            transaction.commit();
        } finally {
            session.close();
        }
    }

    public void deleteEmailById(int id) {


        Session session = HibernateUtil.getSessionFactory().openSession();

        try {

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            String now = PersianDate.now().format(dtf);

            Transaction transaction = session.beginTransaction();
            String hql = "UPDATE Email set disabled = :disabled , lastModified=:now " + "WHERE id = :id";
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

    public Email getEmailById(int id) {


        Session session = HibernateUtil.getSessionFactory().openSession();
        Email email ;
        try {
            email = (Email) session.createQuery("FROM Email eml WHERE eml.id=:id").setParameter("id", id).uniqueResult();
        } finally {
            session.close();
        }
        return email;
    }

    public void updateStatus(Email email) {


        Session session = HibernateUtil.getSessionFactory().openSession();

        try {

            Transaction transaction = session.beginTransaction();
            String hql = "UPDATE Email set status = :status " + "WHERE id = :id";
            Query query = session.createQuery(hql);
            query.setParameter("status", CategoryDao.getElementByName("read"));
            query.setParameter("id", email.getId());
            query.executeUpdate();
            transaction.commit();

            } finally {
            session.close();
        }

    }

    public void saveEmailAttachment(Attachment attachment) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Transaction transaction = session.beginTransaction();
            session.save(attachment);
            transaction.commit();
        } finally {
            session.close();
        }

    }

    public List<Attachment> downloadAttachments(Email email) {


        List<Attachment> attachments ;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String hql = "FROM Attachment emf WHERE emf.email=:email";
            Query query = session.createQuery(hql);
            attachments = query.setParameter("email", email).list();

        } finally {
            session.close();
        }
        return attachments;
    }

    public Attachment downloadSingleAttachment(int attachmentId) {


        Attachment attachment ;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            attachment = (Attachment) session.createQuery("FROM Attachment emf WHERE emf.id=:id")
                    .setParameter("id", attachmentId).uniqueResult();
        } finally {
            session.close();
        }
        return attachment;

    }


}


