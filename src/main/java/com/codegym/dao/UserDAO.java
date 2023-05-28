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

    private static final String GET_USER_BY_ACCOUNT_ID = "SELECT * from user where foreign_account = ?";
    private static final String DEPOSIT_COIN = "UPDATE quatduo.user SET coin = ? where foreign_account = ?";

    private static final String GET_COIN_BY_ID_USER = "select coin from user where id = ?";

    private static final String UPDATE_COIN_USER = "UPDATE quatduo.user SET coin = ? where id = ?";
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
    public User getUserByAccountId(int account_id){
        User user = new User();
        try(Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement(GET_USER_BY_ACCOUNT_ID);
            statement.setInt(1, account_id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int coin = rs.getInt("coin");
                user = new User(id, name, coin);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }
    public void deposit(int coin, int account_id){
        try(Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement(DEPOSIT_COIN);
            statement.setInt(1, coin);
            statement.setInt(2, account_id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int checkCoinUserByName(int userId) {
        int coin=0;

        try (Connection connection = getConnection();
             // Step 2:Create a statement using connection object

             CallableStatement callableStatement = connection.prepareCall(GET_COIN_BY_ID_USER)) {
            // Step 3: Execute the query or update query
            callableStatement.setInt(1,userId);
            ResultSet rs = callableStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                coin = rs.getInt("coin");
                return coin;
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return 0;
    }

    @Override
    public void updateCoinUser(int userId, int coin) {
        try (Connection connection = getConnection();
             CallableStatement callableStatement = connection.prepareCall(UPDATE_COIN_USER)) {

            callableStatement.setInt(1,coin);
            callableStatement.setInt(2,userId);
            callableStatement.executeUpdate();

        } catch (SQLException e) {
            printSQLException(e);
        }
    }
}
