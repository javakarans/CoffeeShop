package com.coffeeshop.database;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

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
        List<T> list = criteria.list();
        HibernateUtil.commitTransaction();
        return list;
    }

    public <T> List getObjectsBySpecialColumn(T object, String column, Object value){
        HibernateUtil.beginTransaction();
        Criteria criteria = HibernateUtil.getSession().createCriteria(object.getClass());
        criteria.add(Restrictions.eq(column, value));
        List<T> list = criteria.list();
        HibernateUtil.commitTransaction();
        return list;
    }

    public <T> List getObjectsByTwoSpecialColumns(T object, String firstColumn, Object firstValue, String secondColumn, Object secondValue){
        HibernateUtil.beginTransaction();
        Criteria criteria = HibernateUtil.getSession().createCriteria(object.getClass());
        criteria.add(Restrictions.eq(firstColumn, firstValue))
                .add(Restrictions.eq(secondColumn, secondValue));
        List<T> list = criteria.list();
        HibernateUtil.commitTransaction();
        return list;
    }

}