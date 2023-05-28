package com.codegym.dao;

import com.codegym.model.Game;
import com.codegym.model.Player;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlayerDAO implements IPlayerDAO{
    private final String jdbcURL = "jdbc:mysql://localhost:3306/quatduo?useSSL=false";
    private final String jdbcUsername = "root";
    private final String jdbcPassword = "123456";

    private static final String SELECT_VIP_PLAYER = "SELECT * from player where rate = 5";
    private static final String SELECT_HOT_PLAYER = "SELECT * from player where rate = 4";
    private static final String SELECT_ALL_PLAYER = "SELECT * from player";
    private static final String GET_PLAYER_BY_ID = "SELECT * FROM player WHERE id_player = ?";

    private static final String CREATE_PLAYER = "insert into player (name, coin, rate, matchs, foreign_account, source_img, price) VALUES (?,?,?,?,?,?,?)";

    private static final String GET_LIST_COIN = "SELECT coin from player";
    private static final String GET_ID_BY_IDFOREIGN = "SELECT id_player FROM player WHERE foreign_account =  ?";

    private static final String UPDATE_COIN_PLAYER = "UPDATE quatduo.player SET coin = ? where id_player = ?";




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
    @Override
    public List<Player> vipPlayer() {
        List<Player> list = new ArrayList<>();
        try(Connection connection = getConnection()){
            PreparedStatement statement = connection.prepareStatement(SELECT_VIP_PLAYER);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                int account_id = rs.getInt("foreign_account");
                int player_id = rs.getInt("id_player");
                String name = rs.getString("name");
                String source_img = rs.getString("source_img");
                int coin = rs.getInt("coin");
                int rate = rs.getInt("rate");
                int price = rs.getInt("price");
                Player player = new Player(player_id, account_id, name, rate, price, coin, source_img);
                list.add(player);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
    public List<Integer> listCoin(){
        List<Integer> list = new ArrayList<>();
        try(Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement(GET_LIST_COIN);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                list.add(rs.getInt("coin"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public List<Player> hotPlayer() {
        List<Player> list = new ArrayList<>();
        try(Connection connection = getConnection()){
            PreparedStatement statement = connection.prepareStatement(SELECT_HOT_PLAYER);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                int account_id = rs.getInt("foreign_account");
                int player_id = rs.getInt("id_player");
                String name = rs.getString("name");
                String source_img = rs.getString("source_img");
                int coin = rs.getInt("coin");
                int rate = rs.getInt("rate");
                int price = rs.getInt("price");
                Player player = new Player(player_id, account_id, name, rate, price, coin, source_img);
                list.add(player);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
    public int getIdByIdForegin(int idForeign) {
        int id;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ID_BY_IDFOREIGN)) {
            preparedStatement.setInt(1,idForeign);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                id = rs.getInt("id_player");
                return id;
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return 0;
    }


    public void createPlayer(String name, int coin, int rate,int matchs, int foreign_account,String source_img,int price){
        try (Connection connection = getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_PLAYER);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2,coin);
            preparedStatement.setInt(3,rate);
            preparedStatement.setInt(4,matchs);
            preparedStatement.setInt(5,foreign_account);
            preparedStatement.setString(6,source_img);
            preparedStatement.setInt(7,price);
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e){
            printSQLException(e);
        }
    }

    @Override
    public List<Player> listPlayer() {
        List<Player> list = new ArrayList<>();
        try(Connection connection = getConnection()){
            PreparedStatement statement = connection.prepareStatement(SELECT_ALL_PLAYER);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                int account_id = rs.getInt("foreign_account");
                int player_id = rs.getInt("id_player");
                String name = rs.getString("name");
                String source_img = rs.getString("source_img");
                int coin = rs.getInt("coin");
                int rate = rs.getInt("rate");
                int price = rs.getInt("price");
                Player player = new Player(player_id, account_id, name, rate, price, coin, source_img);
                list.add(player);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public boolean updateProfile(Player player) throws SQLException {
        return false;
    }

    @Override
    public Player searchPlayerById(int id) {
        Player player = new Player();
        try(Connection connection = getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(GET_PLAYER_BY_ID);
            preparedStatement.setInt(1,id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                int account_id = rs.getInt("foreign_account");
                int player_id = rs.getInt("id_player");
                String name = rs.getString("name");
                String source_img = rs.getString("source_img");
                int coin = rs.getInt("coin");
                int rate = rs.getInt("rate");
                int price = rs.getInt("price");
                player = new Player(player_id, account_id, name, rate, price, coin, source_img);
                return  player;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return player;
    }

    @Override
    public List<Player> searchPlayer(String name_search) {
        List<Player> list = new ArrayList<>();
        String query = "{CALL search_player(?)}";
        try (Connection connection = getConnection();
             // Step 2:Create a statement using connection object

             CallableStatement callableStatement = connection.prepareCall(query)) {
            // Step 3: Execute the query or update query
            callableStatement.setString(1,name_search);
            ResultSet rs = callableStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                int account_id = rs.getInt("foreign_account");
                int player_id = rs.getInt("id_player");
                String name = rs.getString("name");
                String source_img = rs.getString("source_img");
                int coin = rs.getInt("coin");
                int rate = rs.getInt("rate");
                int price = rs.getInt("price");
                Player player = new Player(player_id, account_id, name, rate, price, coin, source_img);
                list.add(player);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return list;
    }
    @Override
    public List<Game> searchGameByIdPlayer(int id) {
        List<Game> games = new ArrayList<>();
        String  query = "{CALL find_player_games(?)}";
        try (Connection connection = getConnection();
             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(query);) {
            preparedStatement.setInt(1, id);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                int idGame = rs.getInt("id");
                String name = rs.getString("name");
                String source = rs.getString("image_source");
                games.add(new Game(idGame, name, source));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return games;
    }

    @Override
    public void updateCoinPlayer(int playerId, int coin) {
        try (Connection connection = getConnection();
             CallableStatement callableStatement = connection.prepareCall(UPDATE_COIN_PLAYER)) {

            callableStatement.setInt(1,coin);
            callableStatement.setInt(2,playerId);
            callableStatement.executeUpdate();

        } catch (SQLException e) {
            printSQLException(e);
        }
    }


    public List<Player> searchPlayerByGame(int id) {
        List<Player> list = new ArrayList<>();
        String query = "{CALL search_player_by_game(?)}";
        try (Connection connection = getConnection();
             // Step 2:Create a statement using connection object

             CallableStatement callableStatement = connection.prepareCall(query)) {
            // Step 3: Execute the query or update query
            callableStatement.setInt(1,id);
            ResultSet rs = callableStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {

                int player_id = rs.getInt("id_player");
                String name = rs.getString("name");
                String source_img = rs.getString("source_img");
                int price = rs.getInt("price");
                int rate = rs.getInt("rate");
                Player player = new Player(player_id,name,source_img,rate,price);
                list.add(player);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return list;
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
