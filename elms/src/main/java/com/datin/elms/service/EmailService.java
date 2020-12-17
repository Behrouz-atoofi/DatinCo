package com.datin.elms.service;

import com.datin.elms.controller.email.EmailSrv;
import com.datin.elms.model.Email;
import com.datin.elms.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

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
}
