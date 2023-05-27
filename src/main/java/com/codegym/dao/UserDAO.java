package com.codegym.dao;

import com.codegym.dao.IUserDAO;
import com.codegym.model.Player;
import com.codegym.model.User;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements IUserDAO {
    private final String jdbcURL = "jdbc:mysql://localhost:3306/quatduo?useSSL=false";
    private final String jdbcUsername = "root";
    private final String jdbcPassword = "123456";


    private static final String CREATE_USER = "insert into user (name, coin, foreign_account) VALUES (?,?,?)";
     private static final String GET_USER_BY_ID_USER = "SELECT * FROM user WHERE id = ?";


    public UserDAO() {
    }

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return connection;
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


    @Override
    public void createUser(String name, int coin, int foreign_account) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE_USER)) {
            preparedStatement.setString(1,name);
            preparedStatement.setInt(2, coin);
            preparedStatement.setInt(3, foreign_account);
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }


    @Override
    public User getUserByUserID(int userId) {

        User user = new User();
        try (Connection connection = getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_BY_ID_USER);
            preparedStatement.setInt(1, userId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int userID = rs.getInt("id");
                String name = rs.getString("name");
                user = new User(userID, name);
                return user;

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return user;
    }
}
