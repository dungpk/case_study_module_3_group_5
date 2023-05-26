package com.codegym.dao;

import com.codegym.model.Account;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AccountDao implements IAccountDao{

    private final String jdbcURL = "jdbc:mysql://localhost:3306/quatduo?useSSL=false";
    private final String jdbcUsername = "root";

    private final String jdbcPassword = "Haido123";



    private static final String CHECK_ACCOUNT_EXIST = "select id,user_name,password,role from account where user_name = ? and password = ?";
            ;

    public AccountDao(){
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
    public Account confirmLogin(String userName, String passWord) {
        Account account = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CHECK_ACCOUNT_EXIST);) {
            preparedStatement.setString(1, userName);
            preparedStatement.setString(2, passWord);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("user_name");
                String password = rs.getString("password");
                String role = rs.getString("role");
                account = new Account(id, name, password, role);
            }

            // Step 4: Process the ResultSet object
        } catch (SQLException e) {
            printSQLException(e);
        }

        return account;
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
