package com.coffeeshop.database;

import org.hibernate.Criteria;
import org.hibernate.Session;

import java.util.List;

/**
 * Created by Mohammad on 4/28/2017.
 */
public class SQLService {

    public <T> void create(T object) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.save(object);
        session.getTransaction().commit();
        session.flush();
        session.close();
    }

    public <T> void update(T object) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.update(object);
        session.getTransaction().commit();
        session.flush();
        session.close();
    }

    public <T> void delete(T object) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.delete(object);
        session.getTransaction().commit();
        session.flush();
        session.close();
    }

    public <T> List getAllObjects(T object) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(object.getClass());
        List list = criteria.list();
        session.close();
        return list;
    }
}