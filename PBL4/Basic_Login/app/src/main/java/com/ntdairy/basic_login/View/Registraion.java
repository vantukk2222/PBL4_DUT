package com.ntdairy.basic_login.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.button.MaterialButton;
import com.ntdairy.basic_login.API.ApiServices;
import com.ntdairy.basic_login.Models.Account_Add;
import com.ntdairy.basic_login.Models.Person;
import com.ntdairy.basic_login.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@SuppressWarnings("ConstantConditions")
public class Registraion extends AppCompatActivity {
    private EditText edtusername_Reg;
    private TextView tvleftreg;
    private EditText edtpassword_Reg;
    private EditText edtRepassword_Reg;
    private MaterialButton mbtsighup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registraion);
        edtusername_Reg = findViewById(R.id.username);
        edtpassword_Reg = findViewById(R.id.password);
        edtRepassword_Reg = findViewById(R.id.repassword);
        tvleftreg = findViewById(R.id.tvleftregister);
        mbtsighup = findViewById(R.id.signupbtn);
        mbtsighup.setOnClickListener(view -> SignupAccount());
        tvleftreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (Registraion.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    private void SignupAccount()
    {
        String strUsername = edtusername_Reg.getText().toString().trim();
        String strPassword = edtpassword_Reg.getText().toString().trim();
        String strRePassword = edtRepassword_Reg.getText().toString().trim();
        if(strUsername.equals(""))
        {
            edtusername_Reg.setError("Enter Username");
        }
        else if  (strPassword.equals(""))
        {
            edtpassword_Reg.setError("Enter Password");
        }
        else if(strRePassword.equals(""))
        {
            edtpassword_Reg.setError("Enter Confirm Password");
        }
        else if(strPassword.length() < 6 || strUsername.length() < 6)
        {
            edtpassword_Reg.setError("Password length must be least 6");
            edtusername_Reg.setError("Username length must be least 6");

        }

        if (!strPassword.equals(strRePassword)) {
            edtpassword_Reg.setError("Passwords Do Not Match");
        } else {
            RegAccount(strUsername, strPassword);
        }

    }

    private void RegAccount(String UserName, String Password)
    {
//        BO_CheckRegister checkRegister = new BO_CheckRegister();
//        if(checkRegister.CheckRegister(strUsername,strPassword) == 1)
//        {
//            Toast.makeText(Registraion.this, "Đăng ký thành công!", Toast.LENGTH_SHORT).show();
//                            edtusername_Reg.setText("");
//                            edtpassword_Reg.setText("");
//                            edtRepassword_Reg.setText("");
//
//        }
//        else if(checkRegister.CheckRegister(strUsername,strPassword) == 0)
//        {
//            Toast.makeText(Registraion.this, "Tài khoản đã tồn tại!", Toast.LENGTH_LONG).show();
//                    edtusername_Reg.setText("");
//                    edtpassword_Reg.setText("");
//                    edtRepassword_Reg.setText("");
//        }
//        else {
//            Toast.makeText(Registraion.this, "Lỗi không check được tài khoản", Toast.LENGTH_LONG).show();
//        }
        ApiServices.iApi.CheckUserName(UserName).enqueue(new Callback<Integer>() {
            @Override
            public void onResponse( Call<Integer> call,  Response<Integer> response) {
                int CheckUser = response.body();
                if(CheckUser == 0) {
                    Account_Add account_add = new Account_Add(UserName, Password, 1);
                    ApiServices.iApi.PostAccount(account_add).enqueue(new Callback<Integer>() {
                        @Override
                        public void onResponse(Call<Integer> call, Response<Integer> response) {

                            int numID_Account = response.body();
                            Bundle bundle = new Bundle();
                            bundle.putString("Username",UserName);
                            bundle.putString("Password",Password);
                            bundle.putInt("ID_Account",numID_Account);
                            Intent intent = new Intent(Registraion.this, Registration_information_Activity.class);
                            intent.putExtra("Account",bundle);
                            startActivity(intent);


                        }

                        @Override
                        public void onFailure(Call<Integer> call, Throwable t) {
                            Toast.makeText(Registraion.this, "Đăng ký thất bại", Toast.LENGTH_SHORT).show();

                        }
                    });



//                    ApiServices.iApi.PostAccount(account_add).enqueue(new Callback<Integer>() {
//                        @Override
//                        public void onResponse( Call<Integer> call,  Response<Integer> response) {
//                            ApiServices.iApi.GetIDAccount(Username,Password).enqueue(new Callback<Account>() {
//                                @Override
//                                public void onResponse(Call<Account> call, Response<Account> response) {
//
//                                    Log.e("Get_ID_Account","OK");
//                                    if(response.body() != null)
//                                    {
//                                        boolean gender = r_male.isChecked();
//                                        Account account = response.body();
//                                        ApiServices.iApi.PostPerson(
//                                                new Person(account.getiD_Account(), strName, gender, dateButton.getText().toString().trim(), strEmail, strPhone)
//                                        ).enqueue(new Callback() {
//                                            @Override
//                                            public void onResponse(Call call, Response response) {
//                                                Toast.makeText(Registration_information_Activity.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
//                                                edtName.setText("");
//                                                edtMail.setText("");
//                                                edtPhone.setText("");
//                                            }
//
//                                            @Override
//                                            public void onFailure(Call call, Throwable t) {
//                                                Toast.makeText(Registration_information_Activity.this, "Đăng ký thất bại", Toast.LENGTH_SHORT).show();
//                                            }
//                                        });
//
//                                    }
//                                }
//
//                                @Override
//                                public void onFailure(Call<Account> call, Throwable t) {
//                                    Log.e("Get_ID_Account","Failure");
//                                }
//                            });
//                        }
//
//                        @Override
//                        public void onFailure(Call<Integer> call, Throwable t) {
//                            Toast.makeText(Registraion.this, "Lỗi không xác định \n" + t.getMessage(), Toast.LENGTH_SHORT).show();
//                        }
//                    });
                }
                else
                {
                    Toast.makeText(Registraion.this, "Account already exists", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure( Call<Integer> call, Throwable t) {
                Log.e("Register", "Failure API");
            }
        });
    }

}