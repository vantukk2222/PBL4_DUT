package com.ntdairy.basic_login.API;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ntdairy.basic_login.Models.*;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiServices {
    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();
    ApiServices iApi = new Retrofit.Builder()
            .baseUrl("http://192.168.177.143:1234/api/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiServices.class);


    //http://192.168.1.11:1234/api/Accounts/GetAccount?UserName=1&Password=1
    @GET("Accounts/GetAccount")
    Call<Account> GetIDAccount(@Query("UserName") String Username,
                               @Query("Password") String Password);



    @Headers("Accept:application/json")
    @POST("Carts/PostCart")
    Call<CartModel> PostCart(@Body CartModel Cart);

    @Headers("Accept:application/json")
    @POST("Hoadons/PostHoaDon")
    Call<Integer> PostHoaDon(@Body HoaDon hoadon);

    @DELETE("Carts/DeleteCart")
    Call<Integer> deleteCart(@Query("id") int id_cart);

    @Headers("Accept:application/json")
    @POST("ChiTietHoaDons/PostChiTietHoaDon")
    Call<CTHoaDon> PostCTHoaDon(@Body CTHoaDon cthoadon);

    @Headers("Accept:application/json")
    @POST("People/PostPerson")
    Call<Person> PostPerson(@Body Person person);

    @Headers("Accept:application/json")
    @POST("People/PostPersonText")
    Call<String> PostPersonText(@Query("iD_Account") int iD_Account,
                                @Query("name") String name,
                                @Query("gender") boolean gender,
                                @Query("dateOfBirth") String dob,
                                @Query("email") String email,
                                @Query("phoneNumber") String phone);

    @GET("Accounts/CheckPass")
    Call<Integer> CheckPass(@Query("ID_Account") int ID_Account,
                            @Query("Password") String Password);

    @Headers("Accept:application/json")
    @PUT("Accounts/PutAccountPass")
    Call<Integer> PutAccPass(@Query("ID_Account") int ID_Account,
                             @Query("newPass") String Password);


    @GET("Accounts/CheckUserName")
    Call<Integer> CheckUserName(@Query("UserName") String UserName);

    @GET("People/GetPerson")
    Call<Person> GetPersonByID(@Query("id") int ID_Account);

    @GET("Carts/GetCartByID")
    Call<Integer> GetCartByID(@Query("id_Account") int id,
                              @Query("id_Sach") int idS);
    @GET("Carts/GetCartByIdAccount")
    Call<ArrayList<Cart>> GetCartByIDAccount(@Query("id_Account") int ID);


    @Headers("Accept:application/json")
    @PUT("Carts/PutCart")
    Call<Integer> PutCart_SL(@Body Cart Cart);

    @Headers("Accept:application/json")
    @PUT("Carts/PutCartText")
    Call<Integer> PUTCart(@Query("ID_Account") int id,
                       @Query("ID_Sach") int id_S,
                       @Query("SoLuong") int sl,
                       @Query("TongTien") int tien);

    @Headers("Accept:application/json")
    @POST ("Accounts/PostAccount")
    Call<Integer> PostAccount(@Body Account_Add account_Add);

    @Headers("Accept:application/json")
    @POST ("Accounts/PostAccountText")
    Call<Integer> PostAccountText(@Query("UserName") String Username,
                                  @Query("Password") String Password);


//    @FormUrlEncoded
//    @PUT("api/v1/update/{id}")
//    Call<UpdateResponse> updateUser(@Path("id") int id,
//                                    @Field("name") String name,
//                                    @Field("salary") String salary,
//                                    @Field("age") String age);
//
//}
    @Headers("Accept:application/json")
    @PUT("ChiTietHoaDons/PutChiTietHoaDon")
    Call<CTHoaDon> PUTCTHoaDon(@Body CTHoaDon ctHoaDon);

    @GET("HoaDons/GetHoaDons")
    Call<ArrayList<HoaDon>> GetHoaDons();
    @GET("ChiTietHoaDons/GetChiTietHoaDons")
    Call<ArrayList<CTHoaDon>> GetChitietHoaDon(@Query("id") int ID_HoaDon);
    @GET("Saches/GetSaches")
    Call<ArrayList<sach>> GetSaches();
    @GET("Saches/GetSach")
    Call<sach> GetSachByID(@Query("id") int ID_Book);

    @Headers("Accept:application/json")
    @PUT("Saches/PutSachSoLuong")
    Call<Integer> PutSachSoLuong(@Query("ID_Sach") int id_sach,
                                 @Query("Soluong") int soluong);

    @Headers("Accept:application/json")
    @PUT("People/PutPerson")
    Call<Person> PutPerson(@Body Person person);
}