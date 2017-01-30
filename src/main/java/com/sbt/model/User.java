package com.sbt.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kate_ on 26.01.2017.
 */
@Entity
@Table(name = "user_table")
public class User extends BaseEntity {
    @Column
    private String name;
    @Column
    private String password;
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Item> itemList = new ArrayList<Item>();
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Item> itemPurchList = new ArrayList<Item>();
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Bid> bidList = new ArrayList<Bid>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public void addItem(Item item) {
        if (item.isSales()) {
            this.itemPurchList.add(item);
        } else {
            this.itemList.add(item);
        }
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Bid> getBidList() {
        return bidList;
    }

    public void addBid(Bid bid) {
        this.bidList.add(bid);
    }
}
