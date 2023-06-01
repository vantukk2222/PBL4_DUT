package com.ntdairy.basic_login.View;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.ntdairy.basic_login.API.ApiServices;
import com.ntdairy.basic_login.Models.Cart;
import com.ntdairy.basic_login.Models.CartModel;
import com.ntdairy.basic_login.Models.Thanhtoan;
import com.ntdairy.basic_login.Models.sach;
import com.ntdairy.basic_login.R;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class detailBookActivity extends AppCompatActivity {

    private ImageView imgbook;
    private TextView tvnameofbook, tvnameofauthor, tvdetailofbook, tvmoneybook, tvslsach, tvtitlebook, tvurl;
    private Button btbuy, btaddcart;
    private ImageButton imgbtleft;
    private int idOfBook;
    private sach detailsach;
    Dialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_book);

        dialog = new Dialog(this);

        imgbtleft = findViewById(R.id.imgleft_detailbook);
        tvslsach = findViewById(R.id.tvsoluong_book);
        imgbook = findViewById(R.id.imgbook_cv);
        tvtitlebook = findViewById(R.id.tvtitleofBook);
        tvnameofbook = findViewById(R.id.tvnameofbook_details);
        tvnameofauthor = findViewById(R.id.nameofauthor_details);
        tvdetailofbook = findViewById(R.id.detailsbook_detail);
        tvmoneybook = findViewById(R.id.money_details);
        tvurl = findViewById(R.id.tvurl);
        btbuy = findViewById(R.id.btBuyBook);
        btaddcart = findViewById(R.id.btaddtocart);




        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("Bundle_ID");
        idOfBook = bundle.getInt("ID_Book");
        Log.e("ID_Book",Integer.toString(idOfBook));


        ApiServices.iApi.GetSachByID(idOfBook).enqueue(new Callback<sach>() {
            @Override
            public void onResponse(Call<sach> call, Response<sach> response) {
                Log.e("Call_Detail_Book","CALL API OK");
                detailsach = response.body();
                tvslsach.setText("SL  " + detailsach.getSoLuong() +"  ");
                tvnameofbook.setText(detailsach.getTenSach());
                tvtitlebook.setText(detailsach.getTenSach().toUpperCase());
                tvnameofauthor.setText(detailsach.getTenTacGia());
                tvdetailofbook.setText("Đây là sách của tác giả " + tvnameofauthor.getText() +" được xuất bản năm " + detailsach.getNamXuatBan() +". Đây là lần xuất bản thứ " +detailsach.getSolanTaiBan() +" của cuốn sách này");
                NumberFormat formatter = new DecimalFormat("#,###");
                tvmoneybook.setText("đ" + formatter.format(detailsach.getGiaBan()));
                tvurl.setText(detailsach.getImgSach());
                Glide.with(detailBookActivity.this).load(detailsach.getImgSach()).into(imgbook);
            }

            @Override
            public void onFailure(Call<sach> call, Throwable t) {

                Log.e("Call_Detail_Book","Call API Failure");
            }
        });

        imgbtleft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
//                Intent intentback = new Intent(detailBookActivity.this, home_listtruyen.class);
//                Bundle bundleback = new Bundle();
//
//                bundleback.putInt("ID_Account", bundle.getInt("ID_Account"));
//                bundleback.putString("Username", bundle.getString("Username"));
//                intentback.putExtra("Bun_Username",bundleback);
//                startActivity(intentback);
            }
        });
        btbuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openbuydialog("Mua hàng",bundle.getInt("ID_Account"));
            }
        });
        btaddcart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openbuydialog("Thêm vào giỏ hàng",bundle.getInt("ID_Account"));

            }
        });




    }

    private void openbuydialog(String checked, int ID_Account) {
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("Bundle_ID");

        dialog.setContentView(R.layout.layout_buy);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        btminussoluong, btaddsoluong, btthemgiohang;

        Button btminussoluong = dialog.findViewById(R.id.btminussoluong);
        Button btaddsoluong = dialog.findViewById(R.id.btaddsoluong);
        Button btleftdialog = dialog.findViewById(R.id.btn_leftdialog);
        Button btthemgiohang = dialog.findViewById(R.id.btaddtocart_buy);

        ImageView imgbook_cart = dialog.findViewById(R.id.img_book_buy_cart);

        TextView tvmoneybuy = dialog.findViewById(R.id.tvmoneybuy_cart);
        TextView tvslbuy = dialog.findViewById(R.id.tvslbuy_cart);
        TextView tvslcanbuy = dialog.findViewById(R.id.tvslcanbuy);
        tvslcanbuy.setText("1");
        idOfBook = bundle.getInt("ID_Book");
        Log.e("ID_Book",Integer.toString(idOfBook));
        ApiServices.iApi.GetSachByID(idOfBook).enqueue(new Callback<sach>() {
            @Override
            public void onResponse(Call<sach> call, Response<sach> response) {
                Log.e("Call_Detail_Book","CALL API OK");

                sach newsach = response.body();
                Glide.with(detailBookActivity.this).load(detailsach.getImgSach()).into(imgbook_cart);
                NumberFormat formatter = new DecimalFormat("#,###");
                tvmoneybuy.setText("đ"+formatter.format(newsach.getGiaBan()));
                tvslbuy.setText("Kho: "+newsach.getSoLuong());
                btthemgiohang.setText(checked);

            }

            @Override
            public void onFailure(Call<sach> call, Throwable t) {

            }
        });
        btaddsoluong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Log.e("Number_book_buy",tvslbuy.getText().toString().replaceAll("[^0-9]", ""));
                String numberOnly= tvslbuy.getText().toString().replaceAll("[^0-9]", "");
                int soluong = Integer.valueOf( tvslcanbuy.getText().toString());
                if(soluong < Integer.valueOf(numberOnly))
                {
                    soluong++;
                    tvslcanbuy.setError(null);
                }
                else
                {
                    tvslcanbuy.setError("Quá giới hạn");
                }
                tvslcanbuy.setText(Integer.toString(soluong));
            }
        });
        btminussoluong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int soluong = Integer.valueOf( tvslcanbuy.getText().toString());
                if(soluong > 1)
                {
                    soluong--;
                    tvslcanbuy.setError(null);
                }
                else
                {
                    tvslcanbuy.setError("Sai");
                }
                tvslcanbuy.setText(Integer.toString(soluong));
            }
        });




        btthemgiohang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String soluonggg = tvslbuy.getText().toString().trim().replaceAll("Kho: ","");
                int IsSoluong = Integer.valueOf(soluonggg);
                String money1 = tvmoneybuy.getText().toString().trim().replaceAll(",","");
                money1 = money1.replaceAll("đ","");
//                Toast.makeText(detailBookActivity.this, money1, Toast.LENGTH_SHORT).show();
                String sl = tvslcanbuy.getText().toString().trim();
                int soluong = Integer.valueOf(sl);

                int tien = Integer.valueOf(money1)*Integer.valueOf(sl);
                if(checked.equals("Mua hàng"))
                {

                    if (IsSoluong > 0){
                        ArrayList<Integer> idsach = new ArrayList<>();
                        ArrayList<Integer> soluong_sach = new ArrayList<>();
                        ArrayList<Integer> tongtien = new ArrayList<>();

                        int soluong_int = Integer.valueOf(tvslcanbuy.getText().toString().trim());

                        String tien0 = tvmoneybuy.getText().toString().trim().replaceAll(",", "");
                        tien0 = tien0.replaceAll("đ", "");
                        int tien_int = Integer.valueOf(tien0);

                        tongtien.add(tien_int);
                        idsach.add(idOfBook);
                        soluong_sach.add(Integer.valueOf(soluong_int));


                        Thanhtoan thanhtoan = new Thanhtoan(ID_Account, idsach, soluong_sach, tongtien);
                        thanhtoan.Taohoadon();
                        Toast.makeText(detailBookActivity.this, "Mua hàng thành công", Toast.LENGTH_SHORT).show();
                        String ssll = tvslsach.getText().toString().trim().replaceAll("SL", "");

                        ssll = ssll.replaceAll(" ", "");
                        int In_ssll = Integer.valueOf(ssll);
                        tvslsach.setText("SL  " + Integer.toString(In_ssll - soluong_sach.get(0)) + "  ");
                    }
                    else
                    {
                        dialog.dismiss();
                        Toast.makeText(detailBookActivity.this, "Hết hàng rồi", Toast.LENGTH_SHORT).show();
                    }
                    dialog.dismiss();
                }
                else{

                    if (IsSoluong > 0){
                        ApiServices.iApi.GetCartByID(ID_Account, idOfBook).enqueue(new Callback<Integer>() {
                            @Override
                            public void onResponse(Call<Integer> call, Response<Integer> response) {
                                if (response.body() == null) {
                                    ApiServices.iApi.PostCart(new CartModel(ID_Account, idOfBook, soluong, tien)).enqueue(new Callback<CartModel>() {
                                        @Override
                                        public void onResponse(Call<CartModel> call, Response<CartModel> response) {
                                            Toast.makeText(detailBookActivity.this, "Đã thêm vào giỏ hàng nha", Toast.LENGTH_SHORT).show();


                                        }

                                        @Override
                                        public void onFailure(Call<CartModel> call, Throwable t) {
                                            Toast.makeText(detailBookActivity.this, "Oh no no", Toast.LENGTH_SHORT).show();
                                            String ssll = tvslsach.getText().toString().trim().replaceAll("SL", "");
//                                        ssll = ssll.replaceAll(" ", "");
//                                        int In_ssll = Integer.valueOf(ssll);
//                                        tvslsach.setText(Integer.toString(In_ssll - soluong));
                                            dialog.dismiss();
                                        }
                                    });

                                } else {
                                    ApiServices.iApi.PUTCart(ID_Account, idOfBook, soluong, tien).enqueue(new Callback<Integer>() {
                                        @Override
                                        public void onResponse(Call<Integer> call, Response<Integer> response) {
                                            Toast.makeText(detailBookActivity.this, "Đã thêm vào giỏ hàng", Toast.LENGTH_SHORT).show();
                                            dialog.dismiss();
                                        }

                                        @Override
                                        public void onFailure(Call<Integer> call, Throwable t) {
//                               Toast.makeText(detailBookActivity.this, "Lôi", Toast.LENGTH_SHORT).show();
                                            Log.e("Add_to_Cart:", "Failed");
                                        }
                                    });
                                }
                            }

                            @Override
                            public void onFailure(Call<Integer> call, Throwable t) {

                            }
                        });
                    }
                    else
                    {
                        dialog.dismiss();
                        Toast.makeText(detailBookActivity.this, "Hết hàng rồi", Toast.LENGTH_SHORT).show();
                    }


                }

            }
        });

        btleftdialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        Window window = dialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();

        wlp.gravity = Gravity.BOTTOM;
        window.setAttributes(wlp);
        dialog.show();
    }

}