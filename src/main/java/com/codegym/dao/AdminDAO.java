package com.codegym.dao;

import java.sql.*;

public class AdminDAO {
    private final String jdbcURL = "jdbc:mysql://localhost:3306/quatduo?useSSL=false";
    private final String jdbcUsername = "root";
    private final String jdbcPassword = "123456";


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
    public int CreatePlayer(String username, String password, String name, int coin, int rate, int price){
        int id = -1;
        try(Connection connection = getConnection()) {
            CallableStatement statement = connection.prepareCall("{?=call createPlayer(?, ?, ?, ?, ?, ?)}");
            statement.setString(2, username);
            statement.setString(3, password);
            statement.setString(4, name);
            statement.setInt(5, coin);
            statement.setInt(6, rate);
            statement.setInt(7, price);
            statement.registerOutParameter(1, Types.INTEGER);
//            statement.execute();
            statement.execute();
            id = statement.getInt(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return id;
    }
}
