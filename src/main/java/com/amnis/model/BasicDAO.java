package com.amnis.model;

import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Basic;
import java.util.List;

public interface BasicDAO {

    public BasicEntity findEntityById(int id,BasicEntity entity);

    public void saveEntity(BasicEntity entity);

    public void updateEntity(BasicEntity entity);

    public void deleteEntity(BasicEntity entity);

    public List<?> findAllEntities(BasicEntity entity);
}
