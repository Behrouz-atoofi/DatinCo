package com.datin.elms.repository;

import com.datin.elms.model.Email;
import com.datin.elms.model.EmailFile;
import com.datin.elms.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

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

    public void save(Email email) {

        Transaction transaction = null;


        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(email);
            transaction.commit();
            session.close();

        }
    }

    public void deleteById(int id) {
        Transaction transaction = null;
        Email email = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();


            email = (Email) session.createQuery("FROM Email eml WHERE eml.id =:id").setParameter("id", id).uniqueResult();

            if (email != null) {
                session.delete(email);
                session.getTransaction().commit();
                session.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
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

    public void updateStatus(Email email) {

        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();

            String hql = "UPDATE Email set status = :status " + "WHERE id = :id";
            Query query = session.createQuery(hql);
            query.setParameter("status", CategoryDao.getElementByName("read"));
            query.setParameter("id", email.getId());
            query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();

        }

    }

    public void saveEmailFile(EmailFile emailFile) {
        Transaction transaction = null;


        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(emailFile);
            transaction.commit();
            session.close();

        }

    }

    public EmailFile downloadAttachment(Email email) {

        Transaction transaction = null;
        EmailFile emailFile = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            emailFile = (EmailFile) session.createQuery("FROM EmailFile emf WHERE emf.email=:email")
                    .setParameter("email", email).uniqueResult();

        }
        return emailFile;
    }
}


