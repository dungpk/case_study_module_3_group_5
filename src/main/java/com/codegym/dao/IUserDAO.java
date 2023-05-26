package com.codegym.dao;

import com.codegym.model.Player;
import com.codegym.model.User;

import java.sql.SQLException;
import java.util.List;

public interface IUserDAO {
    void createUser(String name,int coin,int foreign_account);
}