package com.datin.elms.service;

import com.datin.elms.model.Category_element;
import com.datin.elms.model.Email;
import com.datin.elms.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class EmailService {


    public List<Email> getEmailByAddress(String email) {

        Transaction transaction = null;
        List<Email> emails = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();
            emails = session.createQuery("FROM Email eml where eml.email_receiver=:email").setParameter("email", email).list();


            return emails;
        }
    }
    public void save (Email email) {

        Transaction transaction = null ;


        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction() ;
            session.save(email) ;
            transaction.commit();
            session.close();

        }
    }


    public void deleteById (int id) {
        Transaction transaction = null  ;
        Email email = null ;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction() ;


            email = (Email) session.createQuery("FROM Email eml WHERE eml.id =:id").setParameter("id",id).uniqueResult() ;

            if (email != null) {
                session.delete(email);
                session.getTransaction().commit();
                session.close();
                transaction.commit();
            }
        }catch (Exception e) {
            e.printStackTrace();
        }

    }

    public Email getEmailById (int id ) {

        Transaction transaction = null ;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction() ;
            Email email = null ;

            email = (Email) session.createQuery("FROM Email eml WHERE eml.id=:id").setParameter("id",id).uniqueResult() ;
            return email ;
        } catch (Exception e ) {
            e.printStackTrace();
            return null ;
        }

    }

    public void updateStatus (Email email) {

        Transaction transaction = null ;

        try(Session session = HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction() ;
            Category_element read = new Category_element() ;
            read.setId(7);
            String hql = "UPDATE Email set status = :status " + "WHERE id = :id";
            Query query = session.createQuery(hql);
            query.setParameter("status", read);
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


    }


