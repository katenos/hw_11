package com.sbt.model;

import javax.persistence.*;

/**
 * Created by kate_ on 26.01.2017.
 */
@Entity
public class Bid extends BaseEntity{
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;
    @OneToOne(mappedBy = "bid", fetch = FetchType.LAZY)
    private Delivery delivery;


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Delivery getDelivery() {
        return delivery;
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
    }

}
