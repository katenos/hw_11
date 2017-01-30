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

    public void persist(BaseEntity entity) throws DAOException;

    public void closeConnection() throws DAOException;

    public List<? extends BaseEntity> findAll(char className) throws DAOException;

    public User getUser(String username, String password) throws DAOException;
}
