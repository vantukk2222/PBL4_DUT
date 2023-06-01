package com.ntdairy.basic_login.Models;

import java.math.BigDecimal;

public class sach {

    private int iD_Sach;
    private String tenSach;
    private String theloai;
    private String imgSach;
    private String tenTacGia;
    private int solanTaiBan;
    private String namXuatBan;
    private int giaBan;
    private int soLuong;

    public sach(int iD_Sach, String tenSach, String theloai, String imgSach, String tenTacGia, int solanTaiBan, String namXuatBan, int giaBan, int soLuong) {
        this.iD_Sach = iD_Sach;
        this.tenSach = tenSach;
        this.theloai = theloai;
        this.imgSach = imgSach;
        this.tenTacGia = tenTacGia;
        this.solanTaiBan = solanTaiBan;
        this.namXuatBan = namXuatBan;
        this.giaBan = giaBan;
        this.soLuong = soLuong;
    }

    public int getiD_Sach() {
        return iD_Sach;
    }

    public void setiD_Sach(int iD_Sach) {
        this.iD_Sach = iD_Sach;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public String getTheloai() {
        return theloai;
    }

    public void setTheloai(String theloai) {
        this.theloai = theloai;
    }

    public String getImgSach() {
        return imgSach;
    }

    public void setImgSach(String imgSach) {
        this.imgSach = imgSach;
    }

    public String getTenTacGia() {
        return tenTacGia;
    }

    public void setTenTacGia(String tenTacGia) {
        this.tenTacGia = tenTacGia;
    }

    public int getSolanTaiBan() {
        return solanTaiBan;
    }

    public void setSolanTaiBan(int solanTaiBan) {
        this.solanTaiBan = solanTaiBan;
    }

    public String getNamXuatBan() {
        return namXuatBan;
    }

    public void setNamXuatBan(String namXuatBan) {
        this.namXuatBan = namXuatBan;
    }

    public int getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(int giaBan) {
        this.giaBan = giaBan;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    @Override
    public String toString()
    {
        return Integer.toString(getiD_Sach()) + "\n" + getTenSach();

    }
}
