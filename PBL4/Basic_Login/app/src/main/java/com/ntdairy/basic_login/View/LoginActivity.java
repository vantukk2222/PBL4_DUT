package com.ntdairy.basic_login.View;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.button.MaterialButton;
import com.ntdairy.basic_login.API.ApiServices;
import com.ntdairy.basic_login.Models.Account;
import com.ntdairy.basic_login.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@SuppressWarnings("ConstantConditions")
public class LoginActivity extends AppCompatActivity {
    private EditText edtusername;
    private EditText edtpassword;
    private TextView forgotpass;
    private MaterialButton mbtloginbtn;
    private MaterialButton mbtsignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtusername = findViewById(R.id.Uname);
        edtpassword = findViewById(R.id.Pword);
        mbtloginbtn = findViewById(R.id.loginbtn);
        mbtsignup = findViewById(R.id.signup);
        forgotpass = findViewById(R.id.forgotpass);

        forgotpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, forgotpass_activity.class);
                startActivity(intent);
            }
        });



        mbtsignup.setOnClickListener(view -> SignUp());

        mbtloginbtn.setOnClickListener(view -> Login());
    }



    @Override
    protected void onStop() {
        super.onStop();
    }

    private void SignUp() {
        Intent intent = new Intent (LoginActivity.this, Registraion.class);
        startActivity(intent);
    }

    private void Login() {
        String strUsername = edtusername.getText().toString().trim();
        String strPassword = edtpassword.getText().toString().trim();

//        BO_CheckLogin checkLogin = new BO_CheckLogin();
//        Log.e("Number: ", Integer.toString(checkLogin.IsExistUser(strUsername,strPassword)));
//        if(checkLogin.IsExistUser(strUsername,strPassword) == 1)
//        {
////            HOME_LIST_BOOK
//            Intent intent = new Intent(LoginActivity.this, home_listtruyen.class);
//            Bundle bundle = new Bundle();
//            bundle.putString("Username", strUsername);
//            intent.putExtra("Bun_Username", bundle);
//
//            startActivity(intent);
////                    go to profile
////                    Intent intent = new Intent (LoginActivity.this, Main_Infor.class);
////                    Bundle bundle = new Bundle();
////                    bundle.putString("Username",strUsername);
////                    intent.putExtra("Bun_Username",bundle);
////                    startActivity(intent);
//        }
//        else if(checkLogin.IsExistUser(strUsername,strPassword) == 0)
//        {
//            Toast.makeText(LoginActivity.this, "Sai tài khoản hoặc mật khẩu!!", Toast.LENGTH_LONG).show();
//        }
//        else if(checkLogin.IsExistUser(strUsername,strPassword) == 2)
//        {
//            Toast.makeText(LoginActivity.this, "Lỗiiiiiiii", Toast.LENGTH_LONG).show();
//        }
//
//


        ApiServices.iApi.GetIDAccount(strUsername,strPassword).enqueue(new Callback<Account>() {
            @Override
            public void onResponse(@NonNull Call<Account> call, @NonNull Response<Account> response) {
                Log.e("response_login", response.toString());
                if(response.body() != null )
                {
                    Account account  = response.body();
//                        HOME_LIST_BOOK
                    Intent intent = new Intent(LoginActivity.this, home_listtruyen.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("ID_Account", account.getiD_Account());
                    bundle.putString("Username", strUsername);
                    intent.putExtra("Bun_Username", bundle);

                    startActivity(intent);
//                    go to profile
//                    Intent intent = new Intent (LoginActivity.this, Main_Infor.class);
//                    Bundle bundle = new Bundle();
//                    bundle.putString("Username",strUsername);
//                    intent.putExtra("Bun_Username",bundle);
//                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(LoginActivity.this, "Login Unsuccessful", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<Account> call, @NonNull Throwable t) {
                Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("onFailure", "Deo goi duoc api");
            }
        });

    }

}