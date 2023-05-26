package com.codegym.dao;

import com.codegym.model.Player;
import com.codegym.model.User;

import java.sql.SQLException;
import java.util.List;

public interface IUserDAO {
    public List<Player> searchPlayer(String name);
}