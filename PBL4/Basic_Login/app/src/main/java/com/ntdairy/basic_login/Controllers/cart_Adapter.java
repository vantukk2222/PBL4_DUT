package com.ntdairy.basic_login.Controllers;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ntdairy.basic_login.*;
import com.ntdairy.basic_login.API.ApiServices;
import com.ntdairy.basic_login.Models.Cart;
import com.ntdairy.basic_login.Models.sach;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

import retrofit2.*;

public class cart_Adapter extends RecyclerView.Adapter<DemoVH>{

    ArrayList<Cart> listCart;
    Context ct;

    public cart_Adapter(ArrayList<Cart> listCart, Context context) {
        this.listCart = listCart;
        this.ct = context;
    }


    @NonNull
    @Override
    public DemoVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_viewholder_cart,parent,false);

        return new DemoVH(view, listCart).linkAdapter(this);
    }

    @Override
    public void onBindViewHolder(@NonNull DemoVH holder, int position) {

        Cart Thanhtoan = listCart.get(position);
        int ID_CART = listCart.get(position).getiD_Cart();
        NumberFormat formatter = new DecimalFormat("#,###");
        ApiServices.iApi.GetSachByID(Thanhtoan.getiD_Sach()).enqueue(new Callback<sach>() {
            @Override
            public void onResponse(Call<sach> call, Response<sach> response) {
                sach newsach = response.body();
                holder.tv_title_viewholder_cart.setText(newsach.getTenSach());
                Glide.with(ct).load(newsach.getImgSach()).into(holder.imgbook_viewholder_cart);
                holder.tv_soluong_viewholder.setText(Integer.toString(Thanhtoan.getSoLuong()));
                holder.tv_giasl_viewholder_cart.setText(formatter.format(Thanhtoan.getSoLuong()*newsach.getGiaBan())+"đ");
                holder.tv_gia1_viewholder_cart.setText(formatter.format(newsach.getGiaBan())+"đ");
                holder.tv_soluongconlai_viewholder.setHint(Integer.toString(ID_CART));
                holder.tv_soluongconlai_viewholder.setText("SL  "+Integer.toString(newsach.getSoLuong()));
                Log.e("slcl_hint",holder.tv_soluongconlai_viewholder.getHint().toString().trim());

            }

            @Override
            public void onFailure(Call<sach> call, Throwable t) {

            }
        });


    }
    public void setfilteredList(ArrayList<Cart> filteredList)
    {
        for(int i = 0; i <  filteredList.size(); i++)
        {
            listCart.remove(i);
            notifyItemRemoved(i);
        }
    }

    @Override
    public int getItemCount() {
        return listCart.size();
    }
}

class DemoVH extends RecyclerView.ViewHolder{

    TextView tv_soluongconlai_viewholder;
    TextView tv_title_viewholder_cart;
    TextView tv_gia1_viewholder_cart;
    TextView tv_giasl_viewholder_cart;
    TextView tv_soluong_viewholder;
    Button bt_minus_soluong_viewholder;
    Button bt_add_soluong_viewholder;
    Button bt_remove;
    ImageView imgbook_viewholder_cart;


    private cart_Adapter adapter;
    public DemoVH(@NonNull View itemView, ArrayList<Cart> listCart) {
        super(itemView);

        tv_soluongconlai_viewholder = itemView.findViewById(R.id.tv_soluongconlai_viewholder);
        tv_title_viewholder_cart = itemView.findViewById(R.id.tv_title_viewholder_cart);
        tv_gia1_viewholder_cart = itemView.findViewById(R.id.tv_gia1_viewholder_cart);
        tv_giasl_viewholder_cart = itemView.findViewById(R.id.tv_giasl_viewholder_cart);
        tv_soluong_viewholder = itemView.findViewById(R.id.tv_soluong_viewholder);
        bt_minus_soluong_viewholder = itemView.findViewById(R.id.bt_minus_soluong_viewholder);
        bt_add_soluong_viewholder = itemView.findViewById(R.id.bt_add_soluong_viewholder);
        bt_remove = itemView.findViewById(R.id.bt_remove_item_cart);
        imgbook_viewholder_cart = itemView.findViewById(R.id.imgbook_viewholder_cart);


        bt_add_soluong_viewholder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



            }
        });
        bt_remove.setOnClickListener(view ->
            {
                int ID_Cart = Integer.valueOf(tv_soluongconlai_viewholder.getHint().toString().trim());

                adapter.listCart.remove(getAdapterPosition());
                adapter.notifyItemRemoved(getAdapterPosition());
                ApiServices.iApi.deleteCart(ID_Cart).enqueue(new Callback<Integer>() {
                            @Override
                            public void onResponse(Call<Integer> call, Response<Integer> response) {
                                Log.e("ID_CART_REMOVED", Integer.toString(ID_Cart));
                            }

                            @Override
                            public void onFailure(Call<Integer> call, Throwable t) {

                            }
                        });
            });
    }


    public DemoVH linkAdapter(cart_Adapter adapter)
    {
        this.adapter = adapter;
        return this;
    }

}
