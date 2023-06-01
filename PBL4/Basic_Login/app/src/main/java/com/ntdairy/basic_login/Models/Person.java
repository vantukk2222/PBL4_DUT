package com.ntdairy.basic_login.Models;

import java.sql.Timestamp;

public class Person {
    private int iD_Account;
    private String name;
    private boolean gender;
    private String dateOfBirth;
    private String email;
    private String phoneNumber;

    public Person() {
    }

    public Person(int iD_Account, String name, boolean gender, String dateOfBirth, String email, String phoneNumber) {
        this.iD_Account = iD_Account;
        this.name = name;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public int getiD_Account() { return iD_Account;}

    public void setiD_Account(int iD_Account) {
        this.iD_Account = iD_Account;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    @Override
    public String toString()
    {
        return "\nID: " + Integer.toString(getiD_Account()) + "\n"
                + "Name: " + getName() +"\n"
                + "Gender: " + (isGender() ? "1\n": "0\n")
                + "DoB: " + getDateOfBirth() + "\n"
                + "Mail: " + getEmail() + "\n"
                + "Phone: " + getPhoneNumber();
    }
}
