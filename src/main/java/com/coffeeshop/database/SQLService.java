package com.coffeeshop.database;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.util.List;

/**
 * Created by Mohammad on 4/28/2017.
 */
public class SQLService {

    public <T> boolean create(T object) {
        try{
            HibernateUtil.beginTransaction();
            HibernateUtil.getSession().save(object);
            HibernateUtil.commitTransaction();
            return true;
        }catch (HibernateException e){
            HibernateUtil.rollbackTransaction();
            return false;
        }
    }

    public <T> boolean update(T object) {
        try{
            HibernateUtil.beginTransaction();
            HibernateUtil.getSession().update(object);
            HibernateUtil.commitTransaction();
            return true;
        }catch (HibernateException e){
            HibernateUtil.rollbackTransaction();
            return false;
        }
    }

    public <T> boolean delete(T object) {
        try{
            HibernateUtil.beginTransaction();
            HibernateUtil.getSession().delete(object);
            HibernateUtil.commitTransaction();
            return true;
        }catch (HibernateException e){
            return false;
        }

    }

    public <T> List getAllObjects(T object) {
        HibernateUtil.beginTransaction();
        Criteria criteria = HibernateUtil.getSession().createCriteria(object.getClass());
        List list = criteria.list();
        HibernateUtil.commitTransaction();
        return list;
    }
}