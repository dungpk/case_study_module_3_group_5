package com.codegym.dao;

import com.codegym.model.Request;
import com.codegym.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RequestDAO implements IRequestDAO{
    private final String jdbcURL = "jdbc:mysql://localhost:3306/quatduo?useSSL=false";
    private final String jdbcUsername = "root";
    private final String jdbcPassword = "123456";

    public RequestDAO(){
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
    public List<Request> getRequestByIdPlayer(int idPlayer) {
        List<Request> requests = new ArrayList<>();
        String query = "{CALL get_requests_by_id_player(?)}";

        // Step 1: Establishing a Connection
        try (Connection connection = getConnection();

             // Step 2:Create a statement using connection object
             CallableStatement callableStatement = connection.prepareCall(query)) {

            callableStatement.setInt(1, idPlayer);

            // Step 3: Execute the query or update query
            ResultSet rs = callableStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                int id = rs.getInt("id");
                int hours = rs.getInt("hours");
                String description = rs.getString("description");
                int userId = rs.getInt("user_id");
                int playerId = rs.getInt("player_id");

                UserDAO userDAO = new UserDAO();
                User user = userDAO.getUserByUserID(userId);

                requests.add( new Request(id, hours, description, userId,playerId,user.getName())) ;
            }
        } catch (SQLException e) {
            printSQLException(e);
        }

        return requests;
    }
}
