package com.codegym.model;

public class Request {
    int id;
    int hours;
    String description;
    int id_user;
    int id_player;

    String userName;

    public Request(int id, int hours, String description, String userName) {
        this.id = id;
        this.hours = hours;
        this.description = description;
        this.userName = userName;
    }

    public Request(int id, int hours, String description, int id_user, int id_player) {
        this.id = id;
        this.hours = hours;
        this.description = description;
        this.id_user = id_user;
        this.id_player = id_player;
    }

    public Request(int id, int hours, String description, int id_user, int id_player, String userName) {
        this.id = id;
        this.hours = hours;
        this.description = description;
        this.id_user = id_user;
        this.id_player = id_player;
        this.userName = userName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId_player() {
        return id_player;
    }

    public void setId_player(int id_player) {
        this.id_player = id_player;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Request() {
    }

}
