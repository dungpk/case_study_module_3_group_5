package com.codegym.dao;

import com.codegym.model.Game;

import java.sql.SQLException;
import java.util.List;

public interface IGameDAO {
    public List<Game> getAllGame();
    public Game getGameById(int id);
    public void addGame(Game game) throws SQLException;
    public boolean updateGame(Game game) throws SQLException;
    public boolean deleteGame(int id) throws SQLException;
}
