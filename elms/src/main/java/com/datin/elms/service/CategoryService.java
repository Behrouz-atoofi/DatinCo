package com.datin.elms.service;

import com.datin.elms.model.Category;
import com.datin.elms.model.CategoryElement;
import com.datin.elms.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class CategoryService {

    public static CategoryElement getElementById(int id) {

        Transaction transaction = null ;
        CategoryElement category_element = null ;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();

            category_element = (CategoryElement) session.createQuery("FROM CategoryElement catE WHERE catE.id=:id")
                    .setParameter("id", id).uniqueResult();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }

            e.printStackTrace();
        }

            return category_element;


        }

        public static CategoryElement getElementByName(String name) {

        Transaction transaction = null ;
        CategoryElement category_element = null ;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction  =session.beginTransaction() ;

            category_element = (CategoryElement)session.createQuery("FROM CategoryElement  CatE WHERE CatE.name=:name")
                    .setParameter("name",name).uniqueResult() ;
            session.close();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return category_element ;

        }


    public static Category getCategoryByName(String name) {

        Transaction transaction = null ;
        Category category = null ;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction  =session.beginTransaction() ;

            category = (Category)session.createQuery("FROM Category Cat WHERE Cat.name=:name")
                    .setParameter("name",name).uniqueResult() ;
            session.close();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return category ;

    }
}
