package com.ntdairy.basic_login.Models;

import java.math.BigDecimal;

public class CTHoaDon {
    private int iD_HoaDon;
    private int iD_Sach;
    private int soLuong;
    private int tongTien;

    public CTHoaDon(int iD_HoaDon, int iD_Sach, int soLuong, int tongTien) {
        this.iD_HoaDon = iD_HoaDon;
        this.iD_Sach = iD_Sach;
        this.soLuong = soLuong;
        this.tongTien = tongTien;
    }

    public int getiD_HoaDon() {
        return iD_HoaDon;
    }

    public void setiD_HoaDon(int iD_HoaDon) {
        this.iD_HoaDon = iD_HoaDon;
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
