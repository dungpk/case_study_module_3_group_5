package com.codegym.model;

public class Profile {
    int id;
    int age;

    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Profile(int age, String name, String address, String email) {
        this.age = age;
        this.name = name;
        this.address = address;
        this.email = email;
    }

    String address;
    String email;
    String linkMess;

    public Profile() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setLinkMess(String linkMess) {
        this.linkMess = linkMess;
    }

    public int getId() {
        return id;
    }

    public int getAge() {
        return age;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getLinkMess() {
        return linkMess;
    }

    public Profile(int id, int age, String address, String email, String linkMess) {
        this.id = id;
        this.age = age;
        this.address = address;
        this.email = email;
        this.linkMess = linkMess;
    }

    public Profile(int id) {
        this.id = id;
    }
}
