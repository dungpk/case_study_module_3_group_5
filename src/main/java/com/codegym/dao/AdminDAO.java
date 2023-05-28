package com.codegym.dao;

import com.codegym.model.Account;
import com.codegym.model.Player;

import java.sql.*;

public class AdminDAO {
    private final String jdbcURL = "jdbc:mysql://localhost:3306/quatduo?useSSL=false";
    private final String jdbcUsername = "root";
    private final String jdbcPassword = "123456";

    private static final String DELETE_ACCOUNT = "DELETE from account where id = ?";
    private static final String GET_ACCOUNT_BY_ID = "SELECT * from account where id = ?";
    private static final String UPDATE_ACCOUNT_BY_ID = "UPDATE account SET user_name = ?, password = ? where id = ?";
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
            CallableStatement statement = connection.prepareCall("{call createPlayer(?, ?, ?, ?, ?, ?, ?)}");
            statement.setString(1, username);
            statement.setString(2, password);
            statement.setString(3, name);
            statement.setInt(4, coin);
            statement.setInt(5, rate);
            statement.setInt(6, price);
            statement.registerOutParameter(7, Types.INTEGER);
            statement.execute();
            id = statement.getInt(7);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return id;
    }
    public void DeleteAccount(int account_id){
        try(Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement(DELETE_ACCOUNT);
            statement.setInt(1, account_id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public Account getAccountByID(int id){
        Account account = new Account();
        try(Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement(GET_ACCOUNT_BY_ID);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                int account_id = rs.getInt("id");
                String username = rs.getString("user_name");
                String password = rs.getString("password");
                String role = rs.getString("role");
                account = new Account(account_id, username, password, role);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return account;
    }
    public void updateAccount(String user_name, String password, int id){
        try(Connection connection =getConnection()){
            PreparedStatement statement = connection.prepareStatement(UPDATE_ACCOUNT_BY_ID);
            statement.setString(1, user_name);
            statement.setString(2, password);
            statement.setInt(3, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
