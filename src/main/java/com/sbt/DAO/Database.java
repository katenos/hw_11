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

    private void createConnection() throws DAOException {
        if (session == null) {
            configureSessionFactory();
            session = sessionFactory.openSession();
            createTestData();
        }
        if (!session.isOpen()) {
            session = sessionFactory.openSession();
        }
        tx = session.beginTransaction();
    }

    private void createTestData() throws DAOException {
        User user = createUser("test", "test");//основной пользователь
        User user2 = createUser("test2", "test2");//второй пользователь
        Item item = createItem("testItem", user, "Description item", 100, false);//товар, выставленный основным пользователем на продажу
        Item item2 = createItem("testItem2", user, "Description item2", 200, true);//товар, проданный основным пользователем
        Item itemPucrh = createItem("test purch Item", user2, "Description item purch", 300, true);//товар выставленный вторым пользователем на продажу
        Bid bid = createBid(user, itemPucrh);
        Bid bid2 = createBid(user2, item2);
        Delivery delivery = createDelivery(bid, "fuulname user", "user's address", false);
        Delivery delivery2 = createDelivery(bid2, "fuulname user2", "user2's address", true);
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
                case 'd': //Delivery
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

    public User getUser(String username) throws DAOException {
        try {
            createConnection();
            List<User> userList = session.createCriteria(User.class).list();
            for (User user : userList) {
                if (user.getName().equals(username)) {
                    session.flush();
                    tx.commit();
                    return user;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            tx.rollback();
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public User authorizationUser(String username, String password) throws DAOException {
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

    public User createUser(String name, String password) throws DAOException {
        User user = new User();
        try {
            createConnection();
            user.setName(name);
            user.setPassword(password);
            session.persist(user);
            session.flush();
            tx.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            tx.rollback();
        }
        return user;
    }

    public Delivery createDelivery(Bid bid, String fullname, String address, boolean delived) throws DAOException {
        Delivery delivery = new Delivery();
        try {
            createConnection();
            delivery.setBid(bid);
            delivery.setAddress(address);
            delivery.setUserFullName(fullname);
            delivery.setDeliveried(delived);
            bid.setDelivery(delivery);
            session.persist(delivery);
            session.persist(bid);
            session.flush();
            tx.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            tx.rollback();
        }
        return delivery;
    }

    public Item createItem(String name, User user, String description, int price, boolean isSales) throws DAOException {
        Item item = new Item();
        try {
            createConnection();
            item.setName(name);
            item.setPrice(price);
            item.setDescription(description);
            item.setSales(isSales);
            item.setUser(user);
            user.addItem(item);
            session.persist(item);
            session.persist(user);
            session.flush();
            tx.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            tx.rollback();
        }
        return item;
    }

    public Bid createBid(User user, Item item) throws DAOException {
        Bid bid = new Bid();
        try {
            createConnection();
            bid.setUser(user);
            user.addBid(bid);
            bid.setItem(item);
            item.setSales(true);
            session.persist(item);
            session.persist(bid);
            session.persist(user);
            session.flush();
            tx.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            tx.rollback();
        }
        return bid;
    }

}
