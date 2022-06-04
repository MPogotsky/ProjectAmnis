package com.amnis.model;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class BasicDAO {

    private Session session;
    private Transaction tx;

    public Session getSession() {
        return session;
    }

    public void openSession() {
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        tx = session.beginTransaction();
    }

    public void closeSession() {
        tx.commit();
        session.close();
    }

}
