/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbt.DAO;


import com.sbt.model.*;

import java.util.List;

/**
 * @author admin
 */

public interface DAO {

    void closeConnection() throws DAOException;

    List<? extends BaseEntity> findAll(char className) throws DAOException;

    User getUser(String username) throws DAOException;

    User authorizationUser(String username, String password) throws DAOException;

    User createUser(String name, String password) throws DAOException;

    Delivery createDelivery(Bid bid, String fullname, String address, boolean delived) throws DAOException;

    Item createItem(String name, User user, String description, int startPrice, boolean isSales) throws DAOException;

    Bid createBid(User user, Item item) throws DAOException;

}
