package com.ntdairy.basic_login.Models;

public class Account_Add {
    private String userName;
    private String password;
    private int position;

    public Account_Add()
    {}

    public Account_Add(String userName, String password, int position) {
        this.userName = userName;
        this.password = password;
        this.position = position;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
