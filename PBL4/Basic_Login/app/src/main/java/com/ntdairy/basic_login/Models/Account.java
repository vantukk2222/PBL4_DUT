package com.ntdairy.basic_login.Models;

public class Account {
    private int iD_Account;
    private String userName;
    private String password;
    private int position;

    public Account() {
    }

    public Account(int iD_Account, String userName, String password, int position) {
        this.iD_Account = iD_Account;
        this.userName = userName;
        this.password = password;
        this.position = position;
    }

    public int getiD_Account() {
        return iD_Account;
    }

    public void setiD_Account(int iD_Account) {
        this.iD_Account = iD_Account;
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
