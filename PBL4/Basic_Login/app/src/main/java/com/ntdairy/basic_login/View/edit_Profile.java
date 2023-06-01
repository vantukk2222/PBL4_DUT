package com.ntdairy.basic_login.View;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.ntdairy.basic_login.API.ApiServices;
import com.ntdairy.basic_login.Models.Person;
import com.ntdairy.basic_login.R;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class edit_Profile extends AppCompatActivity {
    private DatePickerDialog datePickerDialog;
    private TextView dateButton;
    private EditText edtName, edtMail, edtPhone;
    private TextView checkEdit, leftEdit;
    private RadioButton r_male, r_fmale;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        initDatePicker();
        dateButton = findViewById(R.id.datePickerButton);
        r_male = findViewById(R.id.radioM);
        edtName = findViewById(R.id.edtNameChange);
        edtMail = findViewById(R.id.edtMailChange);
        edtPhone = findViewById(R.id.edtPhoneChange);
        checkEdit = findViewById(R.id.txtCheckEdit);
        leftEdit = findViewById(R.id.txtLeftEdit);

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("Bun_Username");

        boolean gender = r_male.isChecked();

        int ID_Account = bundle.getInt("ID_Account");
        ApiServices.iApi.GetPersonByID(ID_Account).enqueue(new Callback<Person>() {
            @Override
            public void onResponse(Call<Person> call, Response<Person> response) {
                Person person = response.body();
                String[] Dob = person.getDateOfBirth().split("T");
                dateButton.setText(Dob[0]);
                edtName.setText(person.getName());
                edtMail.setText(person.getEmail());
                if(person.isGender())
                {
                    r_male.setChecked(true);
                }
                else
                {
                    r_male.setChecked(false);
                }
                edtPhone.setText(person.getPhoneNumber());
            }

            @Override
            public void onFailure(Call<Person> call, Throwable t) {
                dateButton.setText("");
                edtName.setText("");
                edtMail.setText("");
                r_male.setChecked(true);
                r_male.setChecked(false);
                edtPhone.setText("");

            }
        });

        dateButton.setText(getTodaysDate());
        checkEdit.setOnClickListener(new View.OnClickListener() {
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


                    Person person = new Person();
                    person.setiD_Account(ID_Account);
                    person.setName(strName);
                    person.setGender(gender);
                    person.setEmail(strEmail);
                    person.setDateOfBirth(dateButton.getText().toString().trim());
                    person.setPhoneNumber(strPhone);

                    ApiServices.iApi.PutPerson(person).enqueue(new Callback<Person>() {
                        @Override
                        public void onResponse(Call<Person> call, Response<Person> response) {
                            Log.e("PostPerson OK", response.toString());
                            edtMail.setText("");
                            edtPhone.setText("");
                            edtName.setText("");
                            Toast.makeText(edit_Profile.this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();

                            Intent intent1 = getIntent();
                            Bundle bundle1 = intent1.getBundleExtra("Bun_Username");

                            Intent intentz = new Intent(edit_Profile.this, Main_Infor.class);
                            Bundle bundle = new Bundle();

                            bundle.putInt("ID_Account", bundle1.getInt("ID_Account"));
                            bundle.putString("Username", bundle1.getString("Username"));
                            intentz.putExtra("Bun_Username", bundle);
                            startActivity(intentz);
                        }

                        @Override
                        public void onFailure(Call<Person> call, Throwable t) {

                        }
                    });

                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(edit_Profile.this);
                    alertDialog.setTitle("Cập nhật thông tin");
                    alertDialog.setIcon(R.drawable.ic_baseline_warning_24);
                    alertDialog.setMessage("Bạn chắc chứ??");
                    alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {


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