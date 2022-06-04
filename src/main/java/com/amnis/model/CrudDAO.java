package com.amnis.model;

import javax.management.Query;
import java.util.List;

public class CrudDAO extends BasicDAO {

    public BasicEntity findEntityById(int id, BasicEntity entity) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(entity.getClass(), id);
    }

    public <T> List<?> findFieldValuesByCondition(String conditionFieldName, BasicEntity entity,T conditionValue) {
        openSession();
        List<?> results = getSession().createQuery("FROM " + entity.getClass().getName() +" WHERE " + conditionFieldName + " = " + conditionValue.toString()).list();
        closeSession();
        return results;
    }

    public void saveEntity(BasicEntity entity) {
        openSession();
        getSession().save(entity);
        closeSession();
    }

    public void saveOrUpdateEntity(BasicEntity entity) {
        openSession();
        getSession().saveOrUpdate(entity);
        closeSession();
    }

    public void updateEntity(BasicEntity entity) {
        openSession();
        getSession().update(entity);
        closeSession();
    }

    public List<?> findAllEntities(BasicEntity entity) {
        return (List<?>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From " + entity.getClass().getName()).list();
    }
}
