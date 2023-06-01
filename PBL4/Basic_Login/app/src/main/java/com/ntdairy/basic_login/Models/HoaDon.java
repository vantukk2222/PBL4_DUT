package com.ntdairy.basic_login.Models;

import java.math.BigDecimal;

public class HoaDon {
    private int iD_HoaDon;
    private String ngayLap;
    private int tongTien;
    private int iD_Account;

    public HoaDon(int iD_HoaDon, String ngayLap, int tongTien, int iD_Account) {
        this.iD_HoaDon = iD_HoaDon;
        this.ngayLap = ngayLap;
        this.tongTien = tongTien;
        this.iD_Account = iD_Account;
    }

    public int getiD_HoaDon() {
        return iD_HoaDon;
    }

    public void setiD_HoaDon(int iD_HoaDon) {
        this.iD_HoaDon = iD_HoaDon;
    }

    public String getNgayLap() {
        return ngayLap;
    }

    public void setNgayLap(String ngayLap) {
        this.ngayLap = ngayLap;
    }

    public int getTongTien() {
        return tongTien;
    }

    public void setTongTien(int tongTien) {
        this.tongTien = tongTien;
    }

    public int getiD_Account() {
        return iD_Account;
    }

    public void setiD_Account(int iD_Account) {
        this.iD_Account = iD_Account;
    }
}
