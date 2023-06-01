package com.ntdairy.basic_login.Controllers;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ntdairy.basic_login.Models.sach;
import com.ntdairy.basic_login.R;
import com.ntdairy.basic_login.View.LoginActivity;
import com.ntdairy.basic_login.View.Registraion;

import java.util.ArrayList;
import java.util.List;

public class adapter extends ArrayAdapter<sach> {

    private Context ct;
    private ArrayList<sach> arr;

    public adapter(Context context, int resoure, List<sach> objects)
    { super(context, resoure, objects);
        this.ct = context;
        this.arr = new ArrayList<>(objects);
    }
    @Override

    public View getView (int position, View ConvertView, ViewGroup parent) {
        if (ConvertView == null)
        {
            LayoutInflater inflater = (LayoutInflater)ct.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            ConvertView = inflater.inflate(R.layout.preview_book, null);
        }

        if(arr.size() > 0)
        {
            sach Truyentranh = this.arr.get(position);

            TextView tenTruyen = ConvertView.findViewById(R.id.tvTenTruyen);
            ImageView anhTruyen = ConvertView.findViewById(R.id.imgTruyenTranh);

            tenTruyen.setText(Truyentranh.getTenSach());
            Glide.with(this.ct).load(Truyentranh.getImgSach()).into(anhTruyen);
        }

        return ConvertView;
    }

}
