package com.codegym.dao;

import com.codegym.model.Request;
import com.codegym.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RequestDAO implements IRequestDAO {
    private final String jdbcURL = "jdbc:mysql://localhost:3306/quatduo?useSSL=false";
    private final String jdbcUsername = "root";
    private final String jdbcPassword = "123456";

    static private String GET_ID_USER_BY_ID_REQUEST = "SELECT user_id FROM request WHERE id = ?";
    static private String GET_ID_PLAYER_BY_ID_REQUEST = "SELECT player_id FROM request WHERE id = ?";

    static private String DELETE_REQUEST_BY_ID_REQUEST = "DELETE FROM request WHERE id = ?";
    static private String INSERT_REQUEST = "INSERT INTO request (hours, description, user_id, player_id)\n" +
            "    VALUES (?,?,?,?)";


    public RequestDAO() {
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
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
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

                requests.add(new Request(id, hours, description, userId, playerId, user.getName()));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }

        return requests;
    }

    @Override
    public int getIdUserByIdRequest(int idRequest) {
        int userId = 0;
        try (Connection connection = getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ID_USER_BY_ID_REQUEST);
            preparedStatement.setInt(1, idRequest);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                userId = rs.getInt("user_id");
                return userId;

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userId;
    }

    @Override
    public void deleteRecordByRequestId(int requestId) {
        try (Connection connection = getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_REQUEST_BY_ID_REQUEST);
            preparedStatement.setInt(1, requestId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void insertRequest(int hours, String description, int userId, int playerId) {
        try (Connection connection = getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_REQUEST);
            preparedStatement.setInt(1, hours);
            preparedStatement.setString(2, description);
            preparedStatement.setInt(3, userId);
            preparedStatement.setInt(4, playerId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int getIdPlayerByIdRequest(int idRequest) {
        int playerId = 0;
        try (Connection connection = getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ID_PLAYER_BY_ID_REQUEST);
            preparedStatement.setInt(1, idRequest);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                playerId = rs.getInt("player_id");
                return playerId;

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return playerId;
    }
}
