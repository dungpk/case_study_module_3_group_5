package com.codegym.model;

public class Request {
    int id;
    int hours;
    String description;
    int id_user;
    int id_player;

    String userName;

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

    public Request() {
    }
}
