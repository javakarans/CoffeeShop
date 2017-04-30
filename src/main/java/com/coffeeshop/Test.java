package com.coffeeshop;

import com.coffeeshop.database.HibernateUtil;
import org.hibernate.Session;

/**
 * Created by Mohammad on 4/28/2017.
 */
public class Test {

    public static void main(String[] args) {
        ////////

        HibernateUtil.beginTransaction();
        Session session = HibernateUtil.getSession();
//        session.save()
    }
}
