package com.ntdairy.basic_login.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.ntdairy.basic_login.API.ApiServices;
import com.ntdairy.basic_login.Models.Account;
import com.ntdairy.basic_login.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class changePassword extends AppCompatActivity {

    private EditText edtCurrent, edtNew, edtRetype;
    private Button btnChange;
    private ImageView imgleft;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        edtCurrent = findViewById(R.id.currentPassword);
        edtNew = findViewById(R.id.newPassword);
        edtRetype = findViewById(R.id.retypetPassword);
        btnChange = findViewById(R.id.btnChangePassword);
        imgleft = findViewById(R.id.left_changepass);


        Intent intent1 = getIntent();
        Bundle bundle1 = intent1.getBundleExtra("Bun_IDAccount");
        int ID_Account = bundle1.getInt("ID_Account");
        // Event change Password
        imgleft.setOnClickListener(v-> finish());
        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String newpass = edtNew.getText().toString().trim();
                String retype_newpass = edtRetype.getText().toString().trim();
                String currentpass = edtCurrent.getText().toString().trim();
                if(newpass.length() < 6)
                {
                    edtNew.setError("");
                }
                else if(!newpass.equals(retype_newpass))
                {
                    edtNew.setError("");
                    Toast.makeText(changePassword.this, "Không khớp mật khẩu", Toast.LENGTH_SHORT).show();
                }

                else {
                    ApiServices.iApi.CheckPass(ID_Account, currentpass).enqueue(new Callback<Integer>() {
                        @Override
                        public void onResponse(Call<Integer> call, Response<Integer> response) {
                            int ID_Account_check = response.body();
                            if (ID_Account_check == ID_Account) {
                                Log.e("Check Pass", "OK");
                                ApiServices.iApi.PutAccPass(ID_Account, newpass).enqueue(new Callback<Integer>() {
                                    @Override
                                    public void onResponse(Call<Integer> call, Response<Integer> response) {
                                        int ID_Account_Change = response.body();
                                        if(ID_Account_Change == ID_Account)
                                        {
                                            Toast.makeText(changePassword.this, "Đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
                                            edtCurrent.setText("");
                                            edtNew.setText("");
                                            edtRetype.setText("");
                                        }
                                        else
                                        {
                                            Toast.makeText(changePassword.this, "Lỗi", Toast.LENGTH_SHORT).show();
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<Integer> call, Throwable t) {

                                        Toast.makeText(changePassword.this, "Lỗi API", Toast.LENGTH_SHORT).show();
                                    }
                                });

                            } else {
                                edtCurrent.setError("");
                                Toast.makeText(changePassword.this, "Mật khẩu cũ không đúng!!", Toast.LENGTH_SHORT).show();

                            }
                        }

                        @Override
                        public void onFailure(Call<Integer> call, Throwable t) {

                        }
                    });
                }
            }
        });
    }
}