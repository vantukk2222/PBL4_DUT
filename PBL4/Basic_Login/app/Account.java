package com.ntdairy.basic_login;

import java.io.Serializable;


public class Account implements  Serializable{
    private int id;
    private String username;
    private String password;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        password = password;
    }

    @Override
    public String toString() {
        return "Account" +
                "\t\tID = " + getId() +
                "\nUsername = " + getUsername() +
                "\t\tPassword = " + getPassword() +
                "";
    }
}
