package com.codegym.model;

public class Player {
    private int player_id;
    private int account_id;
    private String name;
    private String img;
    private int rate;
    private int price;
    private int coin;


    public Player() {
    }

    public Player(int player_id,int account_id ,String name, int rate, int price, int coin, String img) {
        this.player_id = player_id;
        this.account_id = account_id;
        this.name = name;
        this.img = img;
        this.rate = rate;
        this.price = price;
        this.coin = coin;
    }

    public int getPlayer_id() {
        return player_id;
    }

    public void setPlayer_id(int id) {
        this.player_id = id;
    }

    public int getAccount_id() {
        return account_id;
    }

    public void setAccount_id(int account_id) {
        this.account_id = account_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getRank() {
        return rate;
    }

    public void setRate(int rank) {
        this.rate = rank;
    }

    public int getPrice() {
        return price;
    }

    public int getRate() {
        return rate;
    }

    public int getCoin() {
        return coin;
    }

    public void setCoin(int coin) {
        this.coin = coin;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}