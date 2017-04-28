package com.coffeeshop.database;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 * Created by Mohammad on 4/28/2017.
 */
public class HibernateUtil {

    private static HibernateUtil hibernateUtil;
    private SessionFactory sessionFactory;

    private HibernateUtil(){
        Configuration configuration=new Configuration().configure();
        StandardServiceRegistryBuilder builder=new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        sessionFactory=configuration.buildSessionFactory(builder.build());
    }

    public static HibernateUtil getInstance(){
        synchronized (HibernateUtil.class){
            if(hibernateUtil==null){
                hibernateUtil=new HibernateUtil();
            }
        }
        return hibernateUtil;
    }

    public static Session getSession(){
        Session currentSession = HibernateUtil.getInstance().sessionFactory.getCurrentSession();
        return currentSession;
    }

    public static void beginTransaction(){
        HibernateUtil.getSession().beginTransaction();
    }

    public static void commitTransaction(){
        HibernateUtil.getSession().getTransaction().commit();
    }

    public static void closeSession(){
        HibernateUtil.getSession().close();
    }

    public static void rollbackTransaction(){
        HibernateUtil.getSession().getTransaction().rollback();
    }

}
