package com.datin.elms.repository;

import com.datin.elms.model.Attachment;
import com.datin.elms.model.Email;
import com.datin.elms.model.Employee;
import com.datin.elms.util.HibernateUtil;
import com.github.mfathi91.time.PersianDate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class EmailDao {


    public List<Email> getEmailByReceiver(Employee receiver) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        try {

            String hql = "select distinct eml from Email eml " +
                    "join eml.receivers t " +
                    "where t.email in (:receiver) and t.disabled=:disabled";
            Query query = session.createQuery(hql);
            query.setParameter("receiver", receiver.getEmail());
            query.setParameter("disabled", false);

            return query.list();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    public List<Email> getEmailBySender(Employee sender) {


        Session session = HibernateUtil.getSessionFactory().openSession();
        try {

            String hql = "FROM Email eml where eml.sender=:sender and eml.disabled=:disabled";
            Query query = session.createQuery(hql);
            query.setParameter("sender", sender);
            query.setParameter("disabled", false);
            return query.list();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    public void save(Email email) {

        Session session = HibernateUtil.getSessionFactory().openSession();

        try {

            Transaction transaction = session.beginTransaction();
            session.save(email);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public boolean deleteEmailById(int id) {


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

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }

    }

    public Email getEmailById(int id) {


        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Email email = null;
            email = (Email) session.createQuery("FROM Email eml WHERE eml.id=:id").setParameter("id", id).uniqueResult();
            return email;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }

    }

    public boolean updateStatus(Email email) {


        Session session = HibernateUtil.getSessionFactory().openSession();
        try {

            Transaction transaction = session.beginTransaction();

            String hql = "UPDATE Email set status = :status " + "WHERE id = :id";
            Query query = session.createQuery(hql);
            query.setParameter("status", CategoryDao.getElementByName("read"));
            query.setParameter("id", email.getId());
            query.executeUpdate();
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return false;
    }

    public void saveEmailAttachment(Attachment attachment) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Transaction transaction = session.beginTransaction();
            session.save(attachment);
            transaction.commit();

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            session.close();
        }

    }

    public List<Attachment> downloadAttachments(Email email) {


        List<Attachment> attachments = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Transaction transaction = session.beginTransaction();
            String hql = "FROM Attachment emf WHERE emf.email=:email";
            Query query = session.createQuery(hql);
            attachments = query.setParameter("email", email).list();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return attachments;
    }

    public Attachment downloadSingleAttachment(int attachmentId) {


        Attachment attachment = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Transaction transaction = session.beginTransaction();
            attachment = (Attachment) session.createQuery("FROM Attachment emf WHERE emf.id=:id")
                    .setParameter("id", attachmentId).uniqueResult();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return attachment;

    }


}


