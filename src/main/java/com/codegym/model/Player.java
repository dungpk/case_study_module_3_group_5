package com.codegym.model;

public class Player {
    private int id;
    private String name;
    private String img;
    private String activate;
    private String rank;
    private String price;


    public Player() {
    }

    public Player(int id, String name, String img, String activate, String rank, String price) {
        this.id = id;
        this.name = name;
        this.img = img;
        this.activate = activate;
        this.rank = rank;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getActivate() {
        return activate;
    }

    public void setActivate(String activate) {
        this.activate = activate;
    gt

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
