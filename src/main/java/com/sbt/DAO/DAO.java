/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbt.DAO;


import com.sbt.model.BaseEntity;
import com.sbt.model.User;

import java.util.List;

/**
 * @author admin
 */

public interface DAO {

    void persist(BaseEntity entity) throws DAOException;

    void closeConnection() throws DAOException;

    List<? extends BaseEntity> findAll(char className) throws DAOException;

    User getUser(String username) throws DAOException;

    User authorizationUser(String username, String password) throws DAOException;
}
