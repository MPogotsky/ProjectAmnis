package com.amnis.model;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class CrudDAO implements BasicDAO{


    @Override
    public BasicEntity findEntityById(int id, BasicEntity entity) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(entity.getClass(), id);
    }

    @Override
    public void saveEntity(BasicEntity entity) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(entity);
        tx1.commit();
        session.close();
    }

    @Override
    public void updateEntity(BasicEntity entity) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(entity);
        tx1.commit();
        session.close();
    }

    @Override
    public void deleteEntity(BasicEntity entity) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(entity);
        tx1.commit();
        session.close();
    }

    @Override
    public List<?> findAllEntities(BasicEntity entity) {
        return (List<?>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From " + entity.getClass().getName()).list();
    }
}
