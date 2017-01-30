//package com.sbt;
//
//import com.sbt.model.Bid;
//import com.sbt.model.Delivery;
//import com.sbt.model.Item;
//import com.sbt.model.User;
//import org.hibernate.*;
//import org.hibernate.cfg.Configuration;
//import org.hibernate.service.ServiceRegistryBuilder;
//
//import java.util.List;
//import java.util.Properties;
//
//
///**
// * Created by kate_ on 26.01.2017.
// */
//public class Main {
//    private static SessionFactory sessionFactory = null;
//    private static org.hibernate.service.ServiceRegistry serviceRegistry = null;
//
//    private static SessionFactory configureSessionFactory() throws HibernateException {
//        Configuration configuration = new Configuration();
//        configuration.configure();
//        Properties properties = configuration.getProperties();
//        serviceRegistry = new ServiceRegistryBuilder().applySettings(properties).buildServiceRegistry();
//        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
//        return sessionFactory;
//    }
//
//    public static void main(String[] args) {
//        configureSessionFactory();
//        Session session = null;
//        Transaction tx = null;
//        try {
//            session = sessionFactory.openSession();
//            tx = session.beginTransaction();
//            fillField(session);
//            tx.commit();
//            session.close();
//            session=sessionFactory.openSession();
//            tx = session.beginTransaction();
//            outputData(session);
//            session.flush();
//            tx.commit();
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            tx.rollback();
//        } finally {
//            if (session != null) {
//                session.close();
//            }
//        }
//    }
//
//    private static void outputData(Session session) {
//        List<User> list = session.createCriteria(User.class).list();
//        List<Bid> listBid = session.createCriteria(Bid.class).list();
//        List<Delivery> listDel = session.createCriteria(Delivery.class).list();
//        for (User user : list) {
//            System.out.println("Имя пользователя "+user.getName());
//            if (user.getItemList().size() != 0) {
//                for (Item item : user.getItemList()) {
//                    System.out.println("Имя товара "+item.getName());
//                }
//            }
//            System.out.println();
//        }
//        System.out.println("список bid");
//        for (Bid bid : listBid) {
//            System.out.println("Имя товара " + bid.getItem().getName() + "\tИмя пользователя " + bid.getUser().getName());
//        }
//        System.out.println("spisok del");
//        for (Delivery delivery : listDel) {
//            System.out.println("fio "+delivery.getUserFullName()+"adres "+delivery.getAddress());
//        }
//    }
//
//    private static void fillField(Session session) {
//        User user1 = createUser("имя1", session);
//        User user2 = createUser("name2", session);
//        Item item1 = createItem("item1", user1, session);
//        Item item2 = createItem("item2", user1, session);
//        Item item3 = createItem("item3", user2, session);
//        Bid bid1 = createBid(user1, item1, session);
//        Bid bid2 = createBid(user2, item3, session);
//        Delivery delivery= createDelivery(session,bid1,"fio1","adres", false);
//    }
//
//    private static User createUser(String name, Session session) {
//        User user = new User();
//        user.setName(name);
//        session.persist(user);
//        return user;
//    }
//
//    private static Delivery createDelivery(Session session, Bid bid, String fullname, String address, boolean delived) {
//        Delivery delivery=new Delivery();
//        delivery.setBid(bid);
//        delivery.setAddress(address);
//        delivery.setUserFullName(fullname);
//        delivery.setDeliveried(delived);
//        session.persist(delivery);
//        return  delivery;
//    }
//
//    private static Item createItem(String name, User user, Session session) {
//        Item item = new Item();
//        item.setName(name);
//        item.setUser(user);
//        user.addItem(item);
//        session.persist(item);
//        return item;
//    }
//
//    private static Bid createBid(User user, Item item, Session session) {
//        Bid bid = new Bid();
//        bid.setUser(user);
//        user.addBid(bid);
//        bid.setItem(item);
//        session.persist(bid);
//        return bid;
//    }
//
//}
