package com.ntdairy.basic_login.Models;

public class hoadonModels {
    private int iD_HoaDon;
    private String ngayLap;
    private int tongTien;
    private int iD_Account;
    private Account account;
    private CTHoaDon chiTietHoaDons;

    public hoadonModels(int iD_HoaDon, String ngayLap, int tongTien, int iD_Account, com.ntdairy.basic_login.Models.Account account, CTHoaDon chiTietHoaDons) {
        this.iD_HoaDon = iD_HoaDon;
        this.ngayLap = ngayLap;
        this.tongTien = tongTien;
        this.iD_Account = iD_Account;
        this.account = account;
        this.chiTietHoaDons = chiTietHoaDons;
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

    public com.ntdairy.basic_login.Models.Account getAccount() {
        return this.account;
    }

    public void setAccount(com.ntdairy.basic_login.Models.Account account) {
        this.account = account;
    }

    public CTHoaDon getChiTietHoaDons() {
        return this.chiTietHoaDons;
    }

    public void setChiTietHoaDons(CTHoaDon chiTietHoaDons) {
        this.chiTietHoaDons = chiTietHoaDons;
    }
}
