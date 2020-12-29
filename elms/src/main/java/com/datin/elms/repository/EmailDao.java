package com.datin.elms.repository;

import com.datin.elms.model.Email;
import com.datin.elms.model.Attachment;
import com.datin.elms.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class EmailDao {


    public List<Email> getEmailByReceiver(String email) {

        Transaction transaction = null;
        List<Email> emails = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();
            emails = session.createQuery("FROM Email eml where eml.email_receiver=:email").setParameter("email", email).list();


            return emails;
        }
    }

    public List<Email> getEmailBySender(String email) {


        List<Email> emails = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            emails = session.createQuery("FROM Email eml where eml.email_sender=:email").setParameter("email", email).list();


            return emails;
        }

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
        Email email = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();


            email = (Email) session.createQuery("FROM Email eml WHERE eml.id =:id").setParameter("id", id).uniqueResult();

            if (email != null) {
                session.delete(email);
                transaction.commit();
                session.close();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return false;
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
            session.close();
            return true ;
        } catch ( Exception e ) {
            e.printStackTrace();
            return false ;
        }

    }

    public Attachment downloadAttachment(Email email) {

        Transaction transaction = null;
        Attachment attachment = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            attachment = (Attachment) session.createQuery("FROM Attachment emf WHERE emf.email=:email")
                    .setParameter("email", email).uniqueResult();

        }
        return attachment;
    }
}


