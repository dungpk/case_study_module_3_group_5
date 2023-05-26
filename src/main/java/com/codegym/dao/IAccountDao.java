package com.codegym.dao;

import com.codegym.model.Account;

public interface IAccountDao {
    Account confirmLogin(String userName, String passWord);
}
