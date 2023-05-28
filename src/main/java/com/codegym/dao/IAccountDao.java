package com.codegym.dao;

import com.codegym.model.Account;
import com.codegym.model.Game;
import com.codegym.model.Player;
import com.codegym.model.User;

import java.util.List;

public interface IAccountDao {
    Account confirmLogin(String userName, String passWord);
    boolean checkAccountExist(String account);
    void createAccount(String user_name,String password,String role);

    int getIdByUserName(String userName);
    String getRoleByAccountId(int account_id);

    Player getPlayerByAccountId(int accountID);
    User selectUserById(int accountID);
    public User getUserByAccountId(int accountId);

    void updateUserNameByAccountId(int accountId,String name);
    void updateProfileUserByAccountId(int accountId,int age,String address,String email);
}
