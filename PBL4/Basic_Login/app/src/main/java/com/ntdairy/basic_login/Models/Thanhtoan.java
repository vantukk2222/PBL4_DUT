package com.ntdairy.basic_login.Models;

import android.util.Log;

import com.ntdairy.basic_login.API.ApiServices;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Thanhtoan {
    private int ID_Account;
    private ArrayList<Integer> ID_Sach;
    private  ArrayList<Integer> SoLuong;
    private ArrayList<Integer> Tongtien;

    public  Thanhtoan(){}

    public Thanhtoan(int ID_Account, ArrayList<Integer> ID_Sach, ArrayList<Integer> soLuong, ArrayList<Integer> tongtien) {
        this.ID_Account = ID_Account;
        Tongtien = tongtien;
        this.ID_Sach = ID_Sach;
        SoLuong = soLuong;
    }
    public void Taohoadon()
    {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.0000");
        String date = df.format(Calendar.getInstance().getTime());
        int money = 0;

        for(int i : getTongtien())
        {
            money +=i;

        }
        HoaDon hoadon = new HoaDon(0, date, money, ID_Account);

        for(int idx_book = 0; idx_book < ID_Sach.size() ; idx_book++ )
        {
            int finalIdx_book = idx_book;
            ApiServices.iApi.PutSachSoLuong(getID_Sach().get(idx_book), getSoLuong().get(idx_book)).enqueue(new Callback<Integer>() {
                @Override
                public void onResponse(Call<Integer> call, Response<Integer> response) {
                    int ID_BOOK_PUT = response.body();
                    if(ID_BOOK_PUT == getID_Sach().get(finalIdx_book))
                    {
                        Log.e("PUT_SACH", "OK nhes");
                    }
                }

                @Override
                public void onFailure(Call<Integer> call, Throwable t) {

                }
            });
        }


        ApiServices.iApi.PostHoaDon(hoadon).enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                int id_hoadon = response.body();
                if(id_hoadon != 0)
                {
                    for(int j = 0  ; j < getID_Sach().size(); j++)
                    {
                        CTHoaDon ctHoaDon = new CTHoaDon(id_hoadon, getID_Sach().get(j), getSoLuong().get(j), getTongtien().get(j));
                        ApiServices.iApi.PostCTHoaDon(ctHoaDon).enqueue(new Callback<CTHoaDon>() {
                            @Override
                            public void onResponse(Call<CTHoaDon> call, Response<CTHoaDon> response) {
                                CTHoaDon newctHoaDon = response.body();
                                if(newctHoaDon != null)
                                {
                                    Log.e("Tao CTHOADON", "OK");

                                }
                            }

                            @Override
                            public void onFailure(Call<CTHoaDon> call, Throwable t) {

                            }
                        });
                    }
                }
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {

            }
        });


    }

    public int getID_Account() {
        return ID_Account;
    }

    public void setID_Account(int ID_Account) {
        this.ID_Account = ID_Account;
    }

    public ArrayList<Integer> getTongtien() {
        return Tongtien;
    }

    public void setTongtien(ArrayList<Integer> tongtien) {
        Tongtien = tongtien;
    }

    public ArrayList<Integer> getID_Sach() {
        return ID_Sach;
    }

    public void setID_Sach(ArrayList<Integer> ID_Sach) {
        this.ID_Sach = ID_Sach;
    }

    public ArrayList<Integer> getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(ArrayList<Integer> soLuong) {
        SoLuong = soLuong;
    }
}
