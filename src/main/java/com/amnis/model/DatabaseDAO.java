package com.amnis.model;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class DatabaseDAO {


    //Tasks DAO
    public TasksEntity findTaskById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(TasksEntity.class, id);
    }

    public void saveTask(TasksEntity tasks) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(tasks);
        tx1.commit();
        session.close();
    }

    public void updateTask(TasksEntity tasks) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(tasks);
        tx1.commit();
        session.close();
    }

    public void deleteTask(TasksEntity tasks) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(tasks);
        tx1.commit();
        session.close();
    }

    public List<TasksEntity> findAllTasks() {
        List<TasksEntity> tasks = (List<TasksEntity>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From TasksEntity").list();
        return tasks;

    }

    //Users DAO

    public UsersEntity findUserById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(UsersEntity.class, id);
    }

    public void saveUser(UsersEntity user) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(user);
        tx1.commit();
        session.close();
    }

    public void updateUser(UsersEntity user) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(user);
        tx1.commit();
        session.close();
    }

    public void deleteUser(UsersEntity user) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(user);
        tx1.commit();
        session.close();
    }

    public List<UsersEntity> findAllUsers() {
        List<UsersEntity> users = (List<UsersEntity>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From UsersEntity").list();
        return users;

    }

    //Commits DAO

    public CommitsEntity findCommitById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(CommitsEntity.class, id);
    }

    public void saveCommit(CommitsEntity commit) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(commit);
        tx1.commit();
        session.close();
    }

    public void updateCommit(CommitsEntity commit) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(commit);
        tx1.commit();
        session.close();
    }

    public void deleteCommit(CommitsEntity commit) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(commit);
        tx1.commit();
        session.close();
    }

    public List<CommitsEntity> findAllCommits() {
        List<CommitsEntity> commits = (List<CommitsEntity>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From CommitsEntity").list();
        return commits;

    }


}
