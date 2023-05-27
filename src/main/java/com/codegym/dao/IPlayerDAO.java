package com.codegym.dao;

import com.codegym.model.Player;
import com.codegym.model.User;

import java.sql.SQLException;
import java.util.List;

public interface IPlayerDAO {
    List<Player> vipPlayer();
    List<Player> hotPlayer();
    List<Player> listPlayer();

    public List<Player> searchPlayer(String name_search);
    public boolean updateProfile(Player player) throws SQLException;
    public Player searchPlayerById(int id);
}
