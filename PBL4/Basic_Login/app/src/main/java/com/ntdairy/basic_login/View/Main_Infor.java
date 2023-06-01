package com.ntdairy.basic_login.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.ntdairy.basic_login.API.ApiServices;
import com.ntdairy.basic_login.Models.Account;
import com.ntdairy.basic_login.Models.Person;
import com.ntdairy.basic_login.R;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Main_Infor extends AppCompatActivity {
    private ImageView imgeditinfor, imgcart;
    private TextView txtName_main, txtDoB_main, txtGender_main, txtPhoneNumber_main, txtMail_main, txtChangePassword_main, tvleftinfor;
    private TextView btnlogout;
    private Person person;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_infor);
        imgeditinfor = this.findViewById(R.id.imgEditProfile);
        imgcart = this.findViewById(R.id.imgNoti);
        txtName_main = this.findViewById(R.id.txtName);
        txtMail_main = this.findViewById(R.id.txtMail);
        txtDoB_main = this.findViewById(R.id.txtDoB);
        txtGender_main = this.findViewById(R.id.txtGender);
        txtPhoneNumber_main = this.findViewById(R.id.txtPhoneNumber);
        txtChangePassword_main = this.findViewById(R.id.txtChangePassword);
        tvleftinfor = findViewById(R.id.tvleftinfor);
        btnlogout = this.findViewById(R.id.btnlogout);

        imgcart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = getIntent();
                Bundle bundle1 = intent1.getBundleExtra("Bun_Username");
                Intent intent = new Intent(Main_Infor.this, show_list_cartAcitvity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("ID_Account", bundle1.getInt("ID_Account"));
                intent.putExtra("Bun_IDAccount",bundle);
                startActivity(intent);

            }
        });

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("Bun_Username");
        String username  = bundle.getString("Username");


        tvleftinfor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
//                Intent intentback = new Intent(Main_Infor.this, home_listtruyen.class);
//                Bundle bundleback = new Bundle();
//
//                bundleback.putInt("ID_Account", bundle.getInt("ID_Account"));
//                bundleback.putString("Username", username);
//                intentback.putExtra("Bun_Username",bundleback);
//                startActivity(intentback);

            }
        });
        btnlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

        txtChangePassword_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = getIntent();
                Bundle bundle1 = intent1.getBundleExtra("Bun_Username");
                Intent intent = new Intent(Main_Infor.this, changePassword.class);

                Bundle bundle = new Bundle();
                bundle.putInt("ID_Account", bundle1.getInt("ID_Account"));
                intent.putExtra("Bun_IDAccount",bundle);
                startActivity(intent);
            }
        });

        imgeditinfor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = getIntent();
                Bundle bundle1 = intent1.getBundleExtra("Bun_Username");

                Intent intentz = new Intent(Main_Infor.this, edit_Profile.class);
                Bundle bundle = new Bundle();

                bundle.putInt("ID_Account", bundle1.getInt("ID_Account"));
                bundle.putString("Username", bundle1.getString("Username"));
                intentz.putExtra("Bun_Username", bundle);
                startActivity(intentz);

            }
        });


        if(bundle != null)
        {

            ApiServices.iApi.CheckUserName(username).enqueue(new Callback<Integer>() {
                @Override
                public void onResponse(Call<Integer> call, Response<Integer> response) {
                    int ID_Account = response.body();
                    ApiServices.iApi.GetPersonByID(ID_Account).enqueue(new Callback<Person>() {
                        @Override
                        public void onResponse(Call<Person> call, Response<Person> response) {
                            Log.e("person-response", response.toString());
                            person = response.body();
                            txtName_main.setText(person.getName());
                            txtMail_main.setText(person.getEmail());
                            String[] Dob = person.getDateOfBirth().split("T");
                            txtDoB_main.setText(Dob[0]);
                            if(person.isGender())
                            {
                                txtGender_main.setText("Nam");
                            }
                            else
                            {
                                txtGender_main.setText("Nữ");
                            }
                            txtPhoneNumber_main.setText(person.getPhoneNumber());
                            }

                            @Override
                            public void onFailure(Call<Person> call, Throwable t) {
                                txtName_main.setText("null");
                                txtMail_main.setText("null");
                                txtDoB_main.setText("null");
                                txtGender_main.setText("null");
                                txtPhoneNumber_main.setText("null");
                                Toast.makeText(Main_Infor.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                    });
                }

                @Override
                public void onFailure(Call<Integer> call, Throwable t) {
                    Log.e("CheckUsernameFailed","Not found ID_Account");
                    txtName_main.setText("null");
                    txtMail_main.setText("null");
                    txtDoB_main.setText("null");
                    txtGender_main.setText("null");
                    txtPhoneNumber_main.setText("null");
                    Toast.makeText(Main_Infor.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
            }
        }

    }