package com.ntdairy.basic_login.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.ntdairy.basic_login.API.ApiServices;
import com.ntdairy.basic_login.Controllers.cart_Adapter;
import com.ntdairy.basic_login.Models.*;
import com.ntdairy.basic_login.R;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class show_list_cartAcitvity extends AppCompatActivity {
    ImageView imgleftlistcart;
    TextView tvtongtien;
    Button btthanhtoan;
    GridView gridView;
    Adaptercustom donhangadapter;
    Dialog dialog;

    //    private ArrayList<CTHoaDon> listcthoadon;
//    private ArrayList<HoaDon> listhoadonview;
    private ArrayList<Cart> listCart;
    public int giatong;

    final int[] finalGiacu = {giatong};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_show_list_cart);
        imgleftlistcart  = findViewById(R.id.imgleftlistcart);
        tvtongtien = findViewById(R.id.tv_tienthanhtoan);
        btthanhtoan = findViewById(R.id.bt_thanhtoantien_list_cart);
        giatong = 0;
        dialog = new Dialog(this);

        NumberFormat formatter = new DecimalFormat("#,###");

        Intent intent1 = getIntent();
        Bundle bundle1 = intent1.getBundleExtra("Bun_IDAccount");

        int ID_Account = bundle1.getInt("ID_Account");

        imgleftlistcart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btthanhtoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiServices.iApi.GetCartByIDAccount(ID_Account).enqueue(new Callback<ArrayList<Cart>>() {
                    @Override
                    public void onResponse(Call<ArrayList<Cart>> call, Response<ArrayList<Cart>> response) {
                        ArrayList<Cart> cartArrayList = response.body();
                        if(cartArrayList != null)
                        {
                            ArrayList<Integer> ID_Sach = new ArrayList<>();
                            ArrayList<Integer> soLuong = new ArrayList<>();
                            ArrayList<Integer> tien = new ArrayList<>();
                            for(Cart i : cartArrayList)
                            {
                                ID_Sach.add(i.getiD_Sach());
                                soLuong.add(i.getSoLuong());
                                tien.add((i.getTongTien()));
                                Log.e("ID_Cart", Integer.toString(i.getiD_Cart()));
                                ApiServices.iApi.deleteCart(i.getiD_Cart()).enqueue(new Callback<Integer>() {
                                    @Override
                                    public void onResponse(Call<Integer> call, Response<Integer> response) {
                                        int id_sach_remove = response.body();
                                        if(id_sach_remove != 0)
                                            Log.e("Delete Cart", "OK");
                                        else return;

                                    }

                                    @Override
                                    public void onFailure(Call<Integer> call, Throwable t) {

                                    }
                                });

                            }

                            Thanhtoan thanhtoan = new Thanhtoan(ID_Account, ID_Sach, soLuong, tien);
                            thanhtoan.Taohoadon();
                            Toast.makeText(show_list_cartAcitvity.this, "Thanh toán thành công", Toast.LENGTH_SHORT).show();


                            opendialog();

//                            filterList();

                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<Cart>> call, Throwable t) {

                    }
                });
            }
        });


        ApiServices.iApi.GetCartByIDAccount(ID_Account).enqueue(new Callback<ArrayList<Cart>>() {
            @Override
            public void onResponse(Call<ArrayList<Cart>> call, Response<ArrayList<Cart>> response) {
                listCart = response.body();
                if(listCart != null)
                {
                    for(Cart index : listCart)
                    {
                        giatong += index.getTongTien();
                    }
                    finalGiacu[0] = giatong;

                    donhangadapter = new Adaptercustom(show_list_cartAcitvity.this, listCart);
                    gridView = findViewById(R.id.grvDStruyen_listcart);
//                    LinearLayoutManager llm = new LinearLayoutManager(show_list_cartAcitvity.this);
//                    llm.setOrientation(LinearLayoutManager.VERTICAL);
//                    gridView.setLayoutManager(llm);
                    gridView.setAdapter(donhangadapter);

                    tvtongtien.setText(formatter.format(giatong) +"đ" );
                }
                else
                {
                    Toast.makeText(show_list_cartAcitvity.this, "Chưa có đơn hàng", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Cart>> call, Throwable t) {

            }
        });


//        ApiServices.iApi.GetHoaDons().enqueue(new Callback<ArrayList<HoaDon>>() {
//            @Override
//            public void onResponse(Call<ArrayList<HoaDon>> call, Response<ArrayList<HoaDon>> response) {
//                listhoadonview = response.body();
//                int ID_Hoadon = 0;
//                for(HoaDon i : listhoadonview)
//                {
//                    if(i.getiD_Account() == ID_Account)
//                    {
//                        ID_Hoadon = i.getiD_HoaDon();
//                        giatong = Integer.valueOf(i.getTongTien().toString());
//                        break;
//                    }
//                }
//                Log.e("ID_HoaDon", Integer.toString(ID_Hoadon));
//                ApiServices.iApi.GetChitietHoaDon(ID_Hoadon).enqueue(new Callback<ArrayList<CTHoaDon>>() {
//                    @Override
//                    public void onResponse(Call<ArrayList<CTHoaDon>> call, Response<ArrayList<CTHoaDon>> response) {
//                        listcthoadon = response.body();
//
//                        donhangadapter = new show_list_cartAcitvity.Adaptercustom(show_list_cartAcitvity.this, listcthoadon);
//                        gridView = (GridView) findViewById(R.id.grvDStruyen_listcart);
//                        gridView.setAdapter(donhangadapter);
//                        tvtongtien.setText(formatter.format(giatong) +"đ" );
//
//                    }
//
//                    @Override
//                    public void onFailure(Call<ArrayList<CTHoaDon>> call, Throwable t) {
//
//                    }
//                });
//            }
//
//            @Override
//            public void onFailure(Call<ArrayList<HoaDon>> call, Throwable t) {
//
//            }
//        });

    }

    private void opendialog() {
        dialog.setContentView(R.layout.layout_thanhtoan);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        TextView OkeyText = dialog.findViewById(R.id.okay_text);
        OkeyText.setOnClickListener(v -> {
            dialog.dismiss();
            finish();
        });
        Window window = dialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();

        wlp.gravity = Gravity.CENTER;
        window.setAttributes(wlp);
        dialog.show();
    }

    private void filterList() {
        Intent intent1 = getIntent();
        Bundle bundle1 = intent1.getBundleExtra("Bun_IDAccount");
        NumberFormat formatter = new DecimalFormat("#,###");


        int ID_Account = bundle1.getInt("ID_Account");

        ApiServices.iApi.GetCartByIDAccount(ID_Account).enqueue(new Callback<ArrayList<Cart>>() {
            @Override
            public void onResponse(Call<ArrayList<Cart>> call, Response<ArrayList<Cart>> response) {
                listCart = response.body();
                if(listCart != null)
                {
                    donhangadapter.setfilteredList(listCart);
                }
                else
                {
                    Toast.makeText(show_list_cartAcitvity.this, "oh no", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<ArrayList<Cart>> call, Throwable t) {

            }
        });
    }

    public class Adaptercustom extends BaseAdapter
    {
        private Context ct;
        private ArrayList<Cart> listCart;

        public void setListCart(ArrayList<Cart> listCart) {
            this.listCart = listCart;
        }

        public Adaptercustom(Context context, List<Cart> objects)
        {
            this.ct = context;
            this.listCart = new ArrayList<>(objects);
        }
        public int totalmoney()
        {
            int tien = 0;
            for(Cart i : listCart)
            {
                tien += i.getTongTien();
            }
            return tien;
        }
        @Override
        public int getCount() {
            return this.listCart.size();
        }
        @Override
        public Object getItem(int position) {
            return this.listCart.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View ConvertView, ViewGroup parent) {

            View view1 = ConvertView;
            if (view1 == null)
            {
                LayoutInflater inflater = (LayoutInflater)ct.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                ConvertView = inflater.inflate(R.layout.layout_viewholder_cart, null);
            }

            if(this.listCart.size() > 0)
            {

                NumberFormat formatter = new DecimalFormat("#,###");
                Cart Thanhtoan = this.listCart.get(position);
                int ID_Cart_1 = listCart.get(position).getiD_Cart();
                TextView tv_soluongconlai_viewholder = ConvertView.findViewById(R.id.tv_soluongconlai_viewholder);
                TextView tv_title_viewholder_cart = ConvertView.findViewById(R.id.tv_title_viewholder_cart);
                TextView tv_gia1_viewholder_cart = ConvertView.findViewById(R.id.tv_gia1_viewholder_cart);
                TextView tv_giasl_viewholder_cart = ConvertView.findViewById(R.id.tv_giasl_viewholder_cart);
                TextView tv_soluong_viewholder = ConvertView.findViewById(R.id.tv_soluong_viewholder);
                Button bt_minus_soluong_viewholder = ConvertView.findViewById(R.id.bt_minus_soluong_viewholder);
                Button bt_add_soluong_viewholder = ConvertView.findViewById(R.id.bt_add_soluong_viewholder);
                Button bt_remove = ConvertView.findViewById(R.id.bt_remove_item_cart);
                ImageView imgbook_viewholder_cart = ConvertView.findViewById(R.id.imgbook_viewholder_cart);

                ApiServices.iApi.GetSachByID(Thanhtoan.getiD_Sach()).enqueue(new Callback<sach>() {
                    @Override
                    public void onResponse(Call<sach> call, Response<sach> response) {
                        sach newsach = response.body();
                        tv_title_viewholder_cart.setText(newsach.getTenSach());
                        Glide.with(show_list_cartAcitvity.this).load(newsach.getImgSach()).into(imgbook_viewholder_cart);
                        tv_soluong_viewholder.setText(Integer.toString(Thanhtoan.getSoLuong()));
                        tv_soluongconlai_viewholder.setText(Integer.toString(newsach.getSoLuong()));
                        tv_giasl_viewholder_cart.setText(formatter.format(Thanhtoan.getSoLuong()*newsach.getGiaBan())+"đ");
                        tv_gia1_viewholder_cart.setText(formatter.format(newsach.getGiaBan())+"đ");
                    }

                    @Override
                    public void onFailure(Call<sach> call, Throwable t) {

                    }
                });

                bt_remove.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ApiServices.iApi.deleteCart(ID_Cart_1).enqueue(new Callback<Integer>() {
                            @Override
                            public void onResponse(Call<Integer> call, Response<Integer> response) {
                                listCart.remove(position);

                                tvtongtien.setText(formatter.format(totalmoney())+"đ");
                                notifyDataSetChanged();


                            }

                            @Override
                            public void onFailure(Call<Integer> call, Throwable t) {

                            }
                        });

                    }
                });

                bt_add_soluong_viewholder.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int soluongdangchon = Integer.valueOf(tv_soluong_viewholder.getText().toString().trim());
                        int soluongconlai = Integer.valueOf(tv_soluongconlai_viewholder.getText().toString().trim());


                        String tienn = tv_gia1_viewholder_cart.getText().toString().trim().replaceAll(",", "");
                        tienn = tienn.replaceAll("đ","");
                        int tienofitem = Integer.valueOf(tienn);

                        if(soluongdangchon < soluongconlai)
                        {
                            tv_soluong_viewholder.setError(null);
                            soluongdangchon++;
                        }
                        else
                        {
                            tv_soluong_viewholder.setError("!");
                        }

                        listCart.get(position).setTongTien(tienofitem*soluongdangchon);
                        listCart.get(position).setSoLuong(soluongdangchon);
                        tv_soluong_viewholder.setText(Integer.toString(soluongdangchon));
                        tvtongtien.setText(formatter.format(totalmoney())+"đ");
                        putcart();


                    }
                });
                bt_minus_soluong_viewholder.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int soluongdangchon = Integer.valueOf(tv_soluong_viewholder.getText().toString().trim());
                        String tienn = tv_gia1_viewholder_cart.getText().toString().trim().replaceAll(",", "");
                        tienn = tienn.replaceAll("đ","");
                        int tienofitem = Integer.valueOf(tienn);

                        if(soluongdangchon > 1)
                        {
                            tv_soluong_viewholder.setError(null);
                            soluongdangchon--;
                        }
                        else
                        {
                            tv_soluong_viewholder.setError("!");
                        }
                        listCart.get(position).setSoLuong(soluongdangchon);
                        listCart.get(position).setTongTien(tienofitem*soluongdangchon);
                        tvtongtien.setText(formatter.format(totalmoney())+"đ");
                        tv_soluong_viewholder.setText(Integer.toString(soluongdangchon));
                        putcart();

                    }
                });
            }
            return ConvertView;
        }

        public void putcart()
        {
            for(Cart cart : listCart)
            {
                ApiServices.iApi.PutCart_SL(cart).enqueue(new Callback<Integer>() {
                    @Override
                    public void onResponse(Call<Integer> call, Response<Integer> response) {
                        int id_cart = response.body();
                        if(id_cart == cart.getiD_Cart())
                            Log.e("Sua So Luong", "OK em");
                        else {
                            Log.e("Sua So Luong", "Bun qua huhu");
                        }
                    }

                    @Override
                    public void onFailure(Call<Integer> call, Throwable t) {

                    }
                });
            }
        }
        public void setfilteredList(ArrayList<Cart> filteredList)
        {
            this.listCart =filteredList;
            notifyDataSetChanged();
        }
    }
}