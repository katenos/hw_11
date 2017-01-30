package com.sbt.DAO;

import com.sbt.model.Bid;
import com.sbt.model.Delivery;
import com.sbt.model.Item;
import com.sbt.model.User;
import org.hibernate.Session;

/**
 * Created by kate_ on 30.01.2017.
 */
public class TestUsersInfo {
    private Session session = null;

    public TestUsersInfo(Session session) {
        this.session = session;
        createTestData();
    }

    private void createTestData() {
        User user=createUser("test","test",session);//основной пользователь
        User user2=createUser("test2","test2",session);//второй пользователь
        Item item=createItem("testItem",user, "Description item", false,session);//товар, выставленный основным пользователем на продажу
        Item item2=createItem("testItem2",user, "Description item2", true,session);//товар, проданный основным пользователем
        Item itemPucrh=createItem("test purch Item",user2, "Description item purch", true,session);//товар выставленный вторым пользователем на продажу
        Bid bid=createBid(user,itemPucrh, session );
        Bid bid2=createBid(user2,item2, session );
        Delivery delivery=createDelivery(session, bid,"fuulname user","user's address", false);
        Delivery delivery2=createDelivery(session, bid2,"fuulname user2","user2's address", true);
    }

    private static User createUser(String name, String password, Session session) {
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        session.persist(user);
        return user;
    }

    private static Delivery createDelivery(Session session, Bid bid, String fullname, String address, boolean delived) {
        Delivery delivery = new Delivery();
        delivery.setBid(bid);
        delivery.setAddress(address);
        delivery.setUserFullName(fullname);
        delivery.setDeliveried(delived);
        bid.setDelivery(delivery);
        session.persist(delivery);
        session.persist(bid);
        return delivery;
    }

    private static Item createItem(String name, User user, String description, boolean isSales, Session session) {
        Item item = new Item();
        item.setName(name);
        item.setDescription(description);
        item.setSales(isSales);
        item.setUser(user);
        user.addItem(item);
        session.persist(item);
        session.persist(user);
        return item;
    }

    private static Bid createBid(User user, Item item, Session session) {
        Bid bid = new Bid();
        bid.setUser(user);
        user.addBid(bid);
        bid.setItem(item);
        session.persist(bid);
        session.persist(user);
        return bid;
    }
}
