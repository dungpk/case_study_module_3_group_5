package com.codegym.dao;

import com.codegym.model.Profile;

import java.sql.*;

public class ProfileDao implements IProfileDao {
    private final String jdbcURL = "jdbc:mysql://localhost:3306/quatduo?useSSL=false";
    private final String jdbcUsername = "root";
    private final String jdbcPassword = "123456";


    public ProfileDao(){
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
    public Profile getProfileByAccountId(int accountId) {
        Profile profile = new Profile();
        String query = "{CALL get_profile_by_account_id(?)}";
        try (Connection connection = getConnection();
             // Step 2:Create a statement using connection object

             CallableStatement callableStatement = connection.prepareCall(query)) {
            // Step 3: Execute the query or update query
            callableStatement.setInt(1,accountId);
            ResultSet rs = callableStatement.executeQuery();
            while (rs.next()){
                int id = rs.getInt("id");
                int age = rs.getInt("age");
                String address = rs.getString("address");
                String email = rs.getString("email");
                String linkMess = rs.getString("link_mess");
                profile = new Profile(id,age,address,email,linkMess);
                return profile;
            }
            // Step 4: Process the ResultSet object
        } catch (SQLException e) {
            printSQLException(e);
        }
        return profile;
    }
}
