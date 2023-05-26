package com.codegym.model;

public class Game {
    private int id;
    private String name;
    private String image_source;


    public Game() {
    }

    public Game(int id, String name, String image_source) {
        this.id = id;
        this.name = name;

        this.image_source = image_source;
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


    public String getImageSource() {
        return image_source;
    }

    public void setSrc_img(String src_img) {
        this.image_source = src_img;
    }
}
