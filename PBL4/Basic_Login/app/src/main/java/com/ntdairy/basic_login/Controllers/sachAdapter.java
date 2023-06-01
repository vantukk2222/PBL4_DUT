package com.ntdairy.basic_login.Controllers;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.bumptech.glide.Glide;
import com.ntdairy.basic_login.Models.sach;
import com.ntdairy.basic_login.R;
import com.ntdairy.basic_login.View.Book;

import java.util.ArrayList;

public class sachAdapter extends BaseAdapter {
    Context context;
    CardView cardView;
    private ArrayList<sach> arr;
    LayoutInflater inflater;

    public sachAdapter(Context context, ArrayList<sach> arr) {
        this.arr = arr;
        this.context = context;
    }


    @Override
    public int getCount() {
        return arr.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (inflater == null)
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null){

            convertView = inflater.inflate(R.layout.preview_book,null);

        }

        ImageView imgTruyen = convertView.findViewById(R.id.imgTruyenTranh);
        TextView tvTenTruyen = convertView.findViewById(R.id.tvTenTruyen);

        Glide.with(this.context).load(arr.get(position).getImgSach()).into(imgTruyen);
        tvTenTruyen.setText(arr.get(position).getTenSach());

        return convertView;
    }
}
