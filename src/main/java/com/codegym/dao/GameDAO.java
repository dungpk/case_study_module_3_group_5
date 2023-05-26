package com.codegym.dao;

import com.codegym.model.Game;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GameDAO implements IGameDAO {
    private final String jdbcURL = "jdbc:mysql://localhost:3306/quatduo?useSSL=false";
    private final String jdbcUsername = "root";
    private final String jdbcPassword = "phungkhacdung1998";

    private static final String INSERT_GAME_SQL = "INSERT INTO Game (name, image_source) VALUES (?,?)";
    private static final String SELECT_GAME_BY_ID = "SELECT id,name,image_source FROM Game WHERE id = ?";
    private static final String SELECT_ALL_GAME = "SELECT * FROM Game";
    private static final String UPDATE_GAME_SQL = "UPDATE Game SET name = ?, image_source = ? WHERE id = ?";
    private static final String DETELE_GAME_SQL = "DETELE FROM Game WHERE id = ?";

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
                String image_source = rs.getString("image_source");

                games.add( new Game(id,name,image_source));
            }

        } catch (SQLException e){
            printSQLException(e);
        }
        return games;
    }

    @Override
    public Game getGameById(int id) {
        Game game = null;

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_GAME_BY_ID)){
            preparedStatement.setInt(1,id);
            System.out.println(preparedStatement);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){
                String name = rs.getString("name");
                String image_source = rs.getString("image_source");

                game = new Game(id, name, image_source);
            }
        } catch (SQLException e){
            printSQLException(e);
        }

        return game;
    }

    @Override
    public void addGame(Game game) {
        System.out.println(INSERT_GAME_SQL);

        try (Connection connection = getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_GAME_SQL);
            preparedStatement.setString(1,game.getName());
            preparedStatement.setString(2,game.getImageSource());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e){
            printSQLException(e);
        }
    }

    @Override
    public boolean updateGame(Game game) throws SQLException{
        boolean rowUpdated;

        try(Connection connection = getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_GAME_SQL);
            preparedStatement.setInt(1,game.getId());
            preparedStatement.setString(2,game.getName());
            preparedStatement.setString(3, game.getImageSource());

            rowUpdated = preparedStatement.executeUpdate() > 0;
        }
        return rowUpdated;
    }

    @Override
    public boolean deleteGame(int id) throws SQLException{
        boolean rowDeteled;

        try (Connection connection = getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(DETELE_GAME_SQL);
            preparedStatement.setInt(1, id);
            rowDeteled = preparedStatement.executeUpdate() > 0;
        }
        return rowDeteled;
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
