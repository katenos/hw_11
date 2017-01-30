package com.sbt.model;

import javax.persistence.*;

/**
 * Created by kate_ on 29.01.2017.
 */
@Entity
public class Delivery extends BaseEntity {
    @OneToOne
    @JoinColumn(name = "bid_id")
    private Bid bid; //информация о сделке
    @Column
    private
    String userFullName;//полное имя покупателя
    @Column
    private
    String address; //адрес для доставки
    @Column
    private
    boolean deliveried;//доставлено

    public Bid getBid() {
        return bid;
    }

    public void setBid(Bid bid) {
        this.bid = bid;
    }

    public String getUserFullName() {
        return userFullName;
    }

    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isDeliveried() {
        return deliveried;
    }

    public void setDeliveried(boolean deliveried) {
        this.deliveried = deliveried;
    }
}
