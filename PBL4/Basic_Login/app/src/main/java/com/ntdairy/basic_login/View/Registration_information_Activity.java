package com.ntdairy.basic_login.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.ntdairy.basic_login.API.ApiServices;
import com.ntdairy.basic_login.Models.Account;
import com.ntdairy.basic_login.Models.Account_Add;
import com.ntdairy.basic_login.Models.Person;
import com.ntdairy.basic_login.R;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Registration_information_Activity extends AppCompatActivity {
    private DatePickerDialog datePickerDialog;
    private Button dateButton, checkregbutton;
    private EditText edtName, edtMail, edtPhone;
    private RadioButton r_male, r_female;
    private TextView  leftEdit;
//    private TextView checkEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_registration_information);initDatePicker();

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("Account");

        r_male = findViewById(R.id.radioM_infor);
        r_female = findViewById(R.id.radioF_infor);
        edtName = findViewById(R.id.edtNameChange_infor);
        edtMail = findViewById(R.id.edtMailChange_infor);
        edtPhone = findViewById(R.id.edtPhoneChange_infor);
        dateButton = findViewById(R.id.datePickerButton_infor);
        checkregbutton  = findViewById(R.id.btnregistraion_infor);
//        checkEdit = findViewById(R.id.txtCheckEdit_infor);
        leftEdit = findViewById(R.id.txtLeftEdit_infor);
        dateButton.setText(getTodaysDate());
        edtName.setText("");
        edtPhone.setText("");
        edtMail.setText("");

        checkregbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strEmail = edtMail.getText().toString().trim();
                String strName = edtName.getText().toString().trim();
                String strPhone = edtPhone.getText().toString().trim();
                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                if(strName.equals(""))
                {
                        edtName.setError("Error");
                }
                if(strPhone.equals("")) {
                    edtPhone.setError("Error");
                }
                if(strEmail.equals("")) {
                    edtMail.setError("Error");
                }
                if(!checkNameis((strName)))
                {
                    edtName.setError("Sai tên");
                }
                if(!strEmail.matches(emailPattern))
                {
                    edtMail.setError("Địa chỉ mail sai");
                }
                if(strPhone.length() != 10)
                {
                    edtPhone.setError("Sai SDT");
                }
                else
                {
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(Registration_information_Activity.this);
                    alertDialog.setTitle("Khởi tạo thông tin cá nhân");
                    alertDialog.setIcon(R.drawable.ic_baseline_warning_24);
                    alertDialog.setMessage("Bạn chắc chưa?");

                    alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            String Username = bundle.getString("Username");

                            String Password = bundle.getString("Password");
                            Log.e("Date", dateButton.getText().toString().trim());
                            Account_Add account_add = new Account_Add(Username, Password, 2);
                            int ID_Account = bundle.getInt("ID_Account");
                            String strUser = bundle.getString("Username");
                            String strPass = bundle.getString("Password");
                            boolean gender = r_male.isChecked();
//                            Account account = response.body();
                            Log.e("Get_ID_Account",Integer.toString(ID_Account));
                            Person person = new Person();
                            person.setiD_Account(ID_Account);
                            person.setName(strName);
                            person.setGender(gender);
                            person.setEmail(strEmail);
                            person.setDateOfBirth(dateButton.getText().toString().trim());
                            person.setPhoneNumber(strPhone);
                            Log.e("Person_Input", person.toString());
                            ApiServices.iApi.PostPersonText(ID_Account, strName, gender, dateButton.getText().toString().trim(), strEmail, strPhone).enqueue(new Callback<String>() {
                                @Override
                                public void onResponse(Call<String> call, Response<String> response) {

                                        Log.e("PostPerson OK", response.toString());
                                        edtMail.setText("");
                                        edtPhone.setText("");
                                        edtName.setText("");
                                        Intent intentLogin = new Intent(Registration_information_Activity.this, LoginActivity.class);
                                        startActivity(intentLogin);
                                        Toast.makeText(Registration_information_Activity.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();

                                }

                                @Override
                                public void onFailure(Call<String> call, Throwable t) {
                                    Log.e("PostPerson NOT",t.getMessage());
                                }
                            });

                            ApiServices.iApi.PostAccount(account_add).enqueue(new Callback<Integer>() {
                                @Override
                                public void onResponse( Call<Integer> call,  Response<Integer> response) {
                                    ApiServices.iApi.GetIDAccount(Username,Password).enqueue(new Callback<Account>() {
                                        @Override
                                        public void onResponse(Call<Account> call, Response<Account> response) {


                                            if(response.body() != null)
                                            {
                                                boolean gender = r_male.isChecked();
                                                Account account = response.body();
                                                Log.e("Get_ID_Account",Integer.toString(account.getiD_Account()));
                                                Person person = new Person();
                                                person.setEmail(strEmail);
                                                person.setGender(gender);
                                                person.setiD_Account(account.getiD_Account());
                                                person.setName(strName);
                                                person.setPhoneNumber(strPhone);
                                                person.setDateOfBirth(dateButton.getText().toString().trim());
                                                ApiServices.iApi.PostPerson(person).enqueue(new Callback<Person>() {
                                                    @Override
                                                    public void onResponse(Call<Person> call, Response<Person> response) {
                                                        Toast.makeText(Registration_information_Activity.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                                                        edtName.setText("");
                                                        edtMail.setText("");
                                                        edtPhone.setText("");
                                                        Intent intentLogin = new Intent(Registration_information_Activity.this, LoginActivity.class);
                                                        startActivity(intentLogin);
                                                    }

                                                    @Override
                                                    public void onFailure(Call<Person> call, Throwable t) {
                                                        Toast.makeText(Registration_information_Activity.this, "Đăng ký thất bại", Toast.LENGTH_SHORT).show();
                                                    }
                                                });

                                            }
                                        }

                                        @Override
                                        public void onFailure(Call<Account> call, Throwable t) {
                                            Log.e("Get_ID_Account","Failure");
                                        }
                                    });
                                }

                                @Override
                                public void onFailure(Call<Integer> call, Throwable t) {
                                    Toast.makeText(Registration_information_Activity.this, "Lỗi không xác định \n" + t.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });

                        }
                    });
                    alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
                    alertDialog.show();
                }
            }
        });
        leftEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    public boolean checkNameis(String value)
    {
        for(int i = 0; i < value.length(); i++)
        {
            if(value.charAt(i) >= '0' && value.charAt(i) <= '9')
            {
                return false;
            }
        }
        return true;
    }

    private String getTodaysDate()
    {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day, month, year);
    }

    private void initDatePicker()
    {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day)
            {
                month = month + 1;
                String date = makeDateString(day, month, year);
                dateButton.setText(date);
            }
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());

    }

    private String makeDateString(int day, int month, int year)
    {
        return year + "-" + getMonthFormat(month)+ "-" + day;
    }

    private String getMonthFormat(int month)
    {
        if(month == 1)
            return "01";
        if(month == 2)
            return "02";
        if(month == 3)
            return "03";
        if(month == 4)
            return "04";
        if(month == 5)
            return "05";
        if(month == 6)
            return "06";
        if(month == 7)
            return "07";
        if(month == 8)
            return "08";
        if(month == 9)
            return "09";
        if(month == 10)
            return "10";
        if(month == 11)
            return "11";
        if(month == 12)
            return "12";

        //default should never happen
        return "1";
    }

    public void openDatePicker(View view)
    {
        datePickerDialog.show();
    }
}