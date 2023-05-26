package com.codegym.dao;

import com.codegym.model.Game;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GameDAO implements IGameDAO {
    private final String jdbcURL = "jdbc:mysql://localhost:3306/";
    private final String jdbcUsername = "root";
    private final String jdbcPassword = "Haido123";

    private static final String INSERT_GAME_SQL = "";
    private static final String SELECT_GAME_BY_ID = "";
    private static final String SELECT_ALL_GAME = "";
    private static final String UPDATE_GAME_SQL = "";
    private static final String DETELE_GAME_SQL = "";

    public GameDAO(){
    }
    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL,jdbcUsername,jdbcPassword);
        }catch (SQLException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        return connection;
    }

    @Override
    public List<Game> getAllGame() {
        List<Game> games = new ArrayList<>();
        try(Connection connection = getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_GAME);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){
                int id =rs.getInt("id");
                String name = rs.getString("name");
                String src = rs.getString("src");

                games.add( new Game(id,name,src));

            }

        } catch (SQLException e){
            printSQLException(e);
        }
        return games;
    }

    @Override
    public Game getNameById(int id) {
        return null;
    }

    @Override
    public void addGame(Game game) {

    }

    @Override
    public void updateGame(Game game) {

    }

    @Override
    public void deleteGame(int id) {

    }
    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
