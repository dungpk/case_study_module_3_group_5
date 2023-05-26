package com.codegym.dao;

import com.codegym.model.Game;

import java.sql.SQLException;
import java.util.List;

public interface IGameDAO {
    public List<Game> getAllGame();
    public Game getNameById(int id);
    public void addGame(Game game) throws SQLException;
    public void updateGame(Game game);
    public void deleteGame(int id);
}
