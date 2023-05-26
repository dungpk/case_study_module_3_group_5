package com.codegym.dao;

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

}
