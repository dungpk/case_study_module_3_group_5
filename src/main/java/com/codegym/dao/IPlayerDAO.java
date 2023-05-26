package com.codegym.dao;

import com.codegym.model.Player;

import java.util.List;

public interface IPlayerDAO{
    public List<Player> selectAllUsers();
}
