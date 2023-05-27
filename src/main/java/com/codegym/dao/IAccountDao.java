package com.codegym.dao;

import com.codegym.model.Account;
import com.codegym.model.User;

import java.util.List;

public interface IAccountDao {
    Account confirmLogin(String userName, String passWord);
    boolean checkAccountExist(String account);
    void createAccount(String user_name,String password,String role);

    int getIdByUserName(String userName);
}
