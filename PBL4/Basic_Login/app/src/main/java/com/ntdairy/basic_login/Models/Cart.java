package com.ntdairy.basic_login.Models;

import java.math.BigDecimal;

public class Cart {
    private int iD_Cart;
    private int iD_Account;
    private int iD_Sach;
    private int soLuong;
    private int tongTien;

    public Cart(int iD_Cart, int iD_Account, int iD_Sach, int soLuong, int tongTien) {
        this.iD_Cart = iD_Cart;
        this.iD_Account = iD_Account;
        this.iD_Sach = iD_Sach;
        this.soLuong = soLuong;
        this.tongTien = tongTien;
    }

    public int getiD_Cart() {
        return iD_Cart;
    }

    public void setiD_Cart(int iD_Cart) {
        this.iD_Cart = iD_Cart;
    }

    public int getiD_Account() {
        return iD_Account;
    }

    public void setiD_Account(int iD_Account) {
        this.iD_Account = iD_Account;
    }

    public int getiD_Sach() {
        return iD_Sach;
    }

    public void setiD_Sach(int iD_Sach) {
        this.iD_Sach = iD_Sach;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int getTongTien() {
        return tongTien;
    }

    public void setTongTien(int tongTien) {
        this.tongTien = tongTien;
    }
}
