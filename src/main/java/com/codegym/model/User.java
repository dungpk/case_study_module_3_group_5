package com.codegym.model;

public class User {
    protected int id;
    protected String name;
    private  int age;
    private String email;
    private String phone;
    private String address;
    private String interest;
   private String image_source;
   private int coin;

    public User(int id, String name, int coin) {
        this.id = id;
        this.name = name;
        this.coin = coin ;
        this.image_source = "../image/player/default.jpg";
    }

    public User() {
    }

    public String getImage_source() {
        return image_source;
    }

    public void setImage_source(String image_source) {
        this.image_source = image_source;
    }

    public int getCoin() {
        return coin;
    }

    public void setCoin(int coin) {
        this. coin= coin;
    }

    public User(String name, int age, String email, String phone, String address, String interest) {
        this.name = name;
        this.age = age;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.interest = interest;
        this.image_source = "../image/player/default.jpg";
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

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }
}