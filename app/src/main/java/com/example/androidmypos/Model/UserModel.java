package com.example.androidmypos.Model;

public class UserModel {
    private int user_id, level;
    private String name, address, username;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setLevel(int level){
        this.level = level;
    }
    public int getLevel() {
        return level;
    }

    public void setAddress(String address) { this.address = address;}
    public String getAddress() { return address; }

    public void setUsername(String username) { this.username = username;}
    public String getUsername() { return username; }




}
