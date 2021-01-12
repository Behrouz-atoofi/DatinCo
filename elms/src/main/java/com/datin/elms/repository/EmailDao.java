package com.datin.elms.repository;

import com.datin.elms.model.Email;
import com.datin.elms.model.Attachment;
import com.datin.elms.model.Employee;
import com.datin.elms.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class EmailDao {


    public List<Email> getEmailByReceiver(Employee receiver) {

        Transaction transaction = null;


        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();

            String hql = "select distinct eml from Email eml " +
                    "join eml.receivers t " +
                    "where t.email in (:receiver) and t.disabled=:disabled" ;
            Query query = session.createQuery(hql);
            query.setParameter("receiver", receiver.getEmail());
            query.setParameter("disabled", false);

            return query.list() ;

        } catch (Exception e ) {
            e.printStackTrace();
        }
        return null ;
    }

    public List<Email> getEmailBySender(Employee sender) {

        Transaction transaction = null;


        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();

            String hql = "FROM Email eml where eml.sender=:sender and eml.disabled=:disabled" ;
            Query query = session.createQuery(hql) ;
            query.setParameter("sender",sender) ;
            query.setParameter("disabled",false) ;
            return query.list() ;

        } catch (Exception e ) {
            e.printStackTrace();
        }
        return null ;
    }

    public boolean save(Email email) {

        Transaction transaction = null;


        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(email);
            transaction.commit();
            session.close();
            return true ;
        } catch (Exception e) {
            e.printStackTrace();
            return false ;
        }
    }

    public boolean deleteEmailById(int id) {


        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            Date dTime = new Date( );
            SimpleDateFormat df = new SimpleDateFormat("YYYY/MM/dd HH:mm:ss a");
            String last_modified = df.format(dTime);

            transaction = session.beginTransaction();

            String hql = "UPDATE Email set disabled = :disabled , last_modified=:last_modified " + "WHERE id = :id";
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

    public Email getEmailById(int id) {

        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Email email = null;

            email = (Email) session.createQuery("FROM Email eml WHERE eml.id=:id").setParameter("id", id).uniqueResult();
            return email;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public boolean updateStatus(Email email) {

        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();

            String hql = "UPDATE Email set status = :status " + "WHERE id = :id";
            Query query = session.createQuery(hql);
            query.setParameter("status", CategoryDao.getElementByName("read"));
            query.setParameter("id", email.getId());
            query.executeUpdate();
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

    public boolean saveEmailAttachment(Attachment attachment) {
        Transaction transaction = null;


        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(attachment);
            transaction.commit();
            return true ;
        } catch ( Exception e ) {
            e.printStackTrace();
            return false ;
        }

    }

    public List<Attachment> downloadAttachments(Email email) {

        Transaction transaction = null;
        List<Attachment> attachments = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            attachments = session.createQuery("FROM Attachment emf WHERE emf.email=:email")
                    .setParameter("email", email).list();

        }
        return attachments;
    }

    public Attachment downloadSingleAttachment (int attachmentId) {

        Transaction transaction = null;
        Attachment attachment = null ;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            attachment = (Attachment) session.createQuery("FROM Attachment emf WHERE emf.id=:id")
                    .setParameter("id", attachmentId).uniqueResult();

        }
        return attachment;

    }


}


