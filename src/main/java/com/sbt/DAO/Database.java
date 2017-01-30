package com.sbt.DAO;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.sbt.model.*;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import java.util.List;
import java.util.Properties;

/**
 * @author kate_
 */
public class Database implements DAO {

    private static SessionFactory sessionFactory = null;
    private static ServiceRegistry serviceRegistry = null;
    private static Session session = null;
    private static Transaction tx = null;

    private static SessionFactory configureSessionFactory() throws HibernateException {
        Configuration configuration = new Configuration();
        configuration.configure();
        Properties properties = configuration.getProperties();
        serviceRegistry = new ServiceRegistryBuilder().applySettings(properties).buildServiceRegistry();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        return sessionFactory;
    }

    private void createConnection() {
        if(session==null){
            configureSessionFactory();
            session = sessionFactory.openSession();
            TestUsersInfo test=new TestUsersInfo(session);
        }
        if (!session.isOpen()) {
            session = sessionFactory.openSession();
        }
        tx = session.beginTransaction();
    }


    public void persist(BaseEntity entity) throws DAOException {
        try {
            createConnection();
            session.persist(entity);
            session.flush();
            tx.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            tx.rollback();
        }
    }

    public void closeConnection() {
        if (session != null) {
            session.close();
        }
    }

    @Override
    public List<? extends BaseEntity> findAll(char className) throws DAOException {
        try {
            createConnection();
            List list = null;
            switch (className) {
                case 'u': //User
                    list = (List<User>) session.createCriteria(User.class).list();
                    break;
                case 'i': //Item
                    list = (List<Item>) session.createCriteria(Item.class).list();
                    break;
                case 'b': //Bid
                    list = (List<Bid>) session.createCriteria(Bid.class).list();
                    break;
                case 'd': //Bid
                    list = (List<Delivery>) session.createCriteria(Delivery.class).list();
                    break;
            }
            session.flush();
            tx.commit();
            return list;
        } catch (Exception ex) {
            ex.printStackTrace();
            tx.rollback();
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public User getUser(String username, String password) throws DAOException {
        try {
            createConnection();
            List<User> userList = session.createCriteria(User.class).list();
            for (User user : userList) {
                if (user.getName().equals(username) && user.getPassword().equals(password)) {
                    return user;
                }
            }
            session.flush();
            tx.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            tx.rollback();
        }
        return null;
    }

}
