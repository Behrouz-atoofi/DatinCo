package com.datin.elms.repository;

import com.datin.elms.model.Category;
import com.datin.elms.model.CategoryElement;
import com.datin.elms.util.HibernateUtil;
import org.hibernate.Session;

public class CategoryDao {

    public static CategoryElement getElementById(int id) {

        CategoryElement category_element;
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {

            category_element = (CategoryElement) session.createQuery("FROM CategoryElement catE WHERE catE.id=:id")
                    .setParameter("id", id).uniqueResult();

        } finally {
            session.close();
        }

        return category_element;

    }

    public static CategoryElement getElementByName(String name) {


        CategoryElement categoryElement ;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {

            categoryElement = (CategoryElement) session.createQuery("FROM CategoryElement  CatE WHERE CatE.name=:name")
                    .setParameter("name", name).uniqueResult();

        } finally {
            session.close();
        }
        return categoryElement;

    }

    public static Category getCategoryByName(String name) {


        Category category;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            category = (Category) session.createQuery("FROM Category Cat WHERE Cat.name=:name")
                    .setParameter("name", name).uniqueResult();

        } finally {
            session.close();
        }
        return category;

    }
}
