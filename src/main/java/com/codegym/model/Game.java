package com.codegym.model;

public class Game {
    private int id;
    private String name;
    private String src_img;


    public Game() {
    }

    public Game(int id, String name, String src_img) {
        this.id = id;
        this.name = name;

        this.src_img = src_img;
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


    public String getSrc_img() {
        return src_img;
    }

    public void setSrc_img(String src_img) {
        this.src_img = src_img;
    }
}
