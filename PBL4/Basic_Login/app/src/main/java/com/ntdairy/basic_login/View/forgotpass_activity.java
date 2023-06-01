package com.ntdairy.basic_login.View;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.widget.*;

import com.ntdairy.basic_login.R;

public class forgotpass_activity extends AppCompatActivity {

    ImageView left_forgot;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpass);

        left_forgot = findViewById(R.id.left_changepass_f);

        left_forgot.setOnClickListener(v->finish());
    }
}