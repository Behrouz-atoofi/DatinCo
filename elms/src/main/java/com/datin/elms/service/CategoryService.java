package com.datin.elms.service;

import com.datin.elms.model.Category;
import com.datin.elms.model.Category_element;
import com.datin.elms.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.concurrent.ExecutionException;

public class CategoryService {

    public static Category_element getCategoryById(int id) {

        Transaction transaction = null ;
        Category_element category_element = null ;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();

            category_element = (Category_element) session.createQuery("FROM Category_element catE WHERE catE.id=:id")
                    .setParameter("id", id).uniqueResult();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }

            e.printStackTrace();
        }

            return category_element;


        }

        public static Category_element getCategoryByName(String name) {

        Transaction transaction = null ;
        Category_element category_element = null ;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction  =session.beginTransaction() ;

            category_element = (Category_element)session.createQuery("FROM Category_element  CatE WHERE CatE.name=:name")
                    .setParameter("name",name).uniqueResult() ;
            session.close();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return category_element ;

        }

}
