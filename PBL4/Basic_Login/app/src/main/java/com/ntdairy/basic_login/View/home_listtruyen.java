package com.ntdairy.basic_login.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.bumptech.glide.Glide;
import com.google.android.material.navigation.NavigationView;
import com.ntdairy.basic_login.API.ApiServices;
import com.ntdairy.basic_login.Controllers.adapter;
import com.ntdairy.basic_login.Fragments.*;
import com.ntdairy.basic_login.Models.Person;
import com.ntdairy.basic_login.Models.sach;
import com.ntdairy.basic_login.R;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.*;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class home_listtruyen extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private GridView grvDSTruyen;
    private ImageView imginfor;
    private CustomAdapter truyenadapter;
    private ArrayList<sach> mSaches;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;

    boolean checkKey = true;
    SearchView searchView;
    Dialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_listtruyen);

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("Bun_Username");
        dialog = new Dialog(this);

//        NavigationView navigationView = findViewById(R.id.navigationView);
////        navigationView.setItemIconTintList(null);
//        NavController navController  = Navigation.findNavController(this, R.id.navHostFragment);
//        NavigationUI.setupWithNavController(navigationView, navController);

//        imginfor = findViewById(R.id.imagemenu);
        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigationView);
        toolbar = findViewById(R.id.toolbar_homelist);
        searchView = findViewById(R.id.search_filter);
        searchView.clearFocus();


        setSupportActionBar(toolbar);
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout,toolbar,R.string.Open_drawler,R.string.Close_Drawler);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);


//        imginfor.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                drawerLayout.openDrawer(GravityCompat.START);
//            }
//        });
        init();
        UpdateNavHeader();
        anhXa();
        setup();
        setClick();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText);
                return false;
            }
        });

    }
    private void filterList(String text)
    {
        ArrayList<sach> filteredList = new ArrayList<>();

        ApiServices.iApi.GetSaches().enqueue(new Callback<ArrayList<sach>>() {
            @Override
            public void onResponse(Call<ArrayList<sach>> call, Response<ArrayList<sach>> response) {
                mSaches = (ArrayList<sach>) response.body();
                for(sach item : mSaches)
                {
                    if(item.getTenSach().toLowerCase().contains(text.toLowerCase()))
                    {
                        filteredList.add(item);
                    }
                }
                if(filteredList.isEmpty())
                {
                    Toast.makeText(home_listtruyen.this, "Không có truyện này", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    truyenadapter.setfilteredList(filteredList);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<sach>> call, Throwable t) {

            }
        });


    }
    public void UpdateNavHeader()
    {
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("Bun_Username");
        NavigationView navigationView = (NavigationView)findViewById(R.id.navigationView);
        View headerView = navigationView.getHeaderView(0);
        Menu menu = navigationView.getMenu();
        TextView fragmentname = headerView.findViewById(R.id.tvfragmentname);
        TextView fragmentuser = headerView.findViewById(R.id.tvfragmentuser);


        if(bundle != null)
        {
            int ID_Account = bundle.getInt("ID_Account");
            ApiServices.iApi.GetPersonByID(ID_Account).enqueue(new Callback<Person>() {
                @Override
                public void onResponse(Call<Person> call, Response<Person> response) {
                    Person person = response.body();
                    fragmentname.setText(person.getName());
                    fragmentuser.setText(bundle.getString("Username"));
                }

                @Override
                public void onFailure(Call<Person> call, Throwable t) {

                }
            });
        }
        //fragmentname.setText(bundle.getString("Username"));

    }
    private void init() {

        ApiServices.iApi.GetSaches().enqueue(new Callback<ArrayList<sach>>() {
            @Override
            public void onResponse(Call<ArrayList<sach>> call, Response<ArrayList<sach>> response) {

                mSaches = (ArrayList<sach>)response.body();
                Log.e("Number_of_Book", Integer.toString(mSaches.size()));
                for(sach S : mSaches)
                {
                    Log.e("Number : ", Integer.toString(S.getiD_Sach()));
                }
//                Log.e("Number 1", mSaches.get(0).getTenSach());

                truyenadapter = new CustomAdapter(home_listtruyen.this, mSaches);
                grvDSTruyen = (GridView) findViewById(R.id.grvDSTruyen);
                grvDSTruyen.setAdapter(truyenadapter);

            }

            @Override
            public void onFailure(Call<ArrayList<sach>> call, Throwable t) {

            }
        });

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.menuHome:
            {
                init();
                break;
            }
            case R.id.menuProfile:
            {

                Intent intent1 = getIntent();
                Bundle bundle1 = intent1.getBundleExtra("Bun_Username");

                Intent intentz = new Intent(home_listtruyen.this, Main_Infor.class);
                Bundle bundle = new Bundle();

                bundle.putInt("ID_Account", bundle1.getInt("ID_Account"));
                bundle.putString("Username", bundle1.getString("Username"));
                intentz.putExtra("Bun_Username", bundle);
                startActivity(intentz);
                break;
            }
            case R.id.menucart:
            {
                Intent intent1 = getIntent();
                Bundle bundle1 = intent1.getBundleExtra("Bun_Username");
                Intent intent = new Intent(home_listtruyen.this, show_list_cartAcitvity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("ID_Account", bundle1.getInt("ID_Account"));
                intent.putExtra("Bun_IDAccount",bundle);
                startActivity(intent);
                break;
            }
            case  R.id.menuaboutus:
            {
                dialog.setContentView(R.layout.dialog_aboutus);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                Button buttonOK = dialog.findViewById(R.id.ok_btn_id);
                buttonOK.setOnClickListener(v -> dialog.dismiss());
                dialog.show();
                break;
            }
            case R.id.menusupport:
            {
                dialog.setContentView(R.layout.dialog_support);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                Button buttonOK = dialog.findViewById(R.id.ok_btn_sp);
                buttonOK.setOnClickListener(v -> dialog.dismiss());
                dialog.show();
                break;
            }
            case R.id.menuLogout:
            {
                Intent intent = new Intent(home_listtruyen.this, LoginActivity.class);
                startActivity(intent);
            }

        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK);

        return false;
        // Disable back button..............
    }
    public class CustomAdapter extends BaseAdapter
    {
        private ArrayList<sach> arr;
        private Context ct;
        public CustomAdapter(Context context, List<sach> objects)
        {
            this.ct = context;
            this.arr = new ArrayList<>(objects);
            mSaches = new ArrayList<>(objects);
        }

        @Override
        public int getCount() {
            return arr.size();
        }

        @Override
        public Object getItem(int i) {
            return arr.get(i);
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int position, View ConvertView, ViewGroup parent) {
            View view1 = ConvertView;
            if (view1 == null)
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
            ConvertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent1 = getIntent();
                    Bundle bundle1 = intent1.getBundleExtra("Bun_Username");

                    Intent intent = new Intent(home_listtruyen.this, detailBookActivity.class);
                    Bundle bundle = new Bundle();

                    bundle.putInt("ID_Book",position+1);
                    bundle.putInt("ID_Account", bundle1.getInt("ID_Account"));
                    bundle.putString("Username", bundle1.getString("Username"));
                    intent.putExtra("Bundle_ID", bundle);
                    startActivity(intent);
                }
            });
            return ConvertView;
        }
        public void setfilteredList(ArrayList<sach> filteredList)
        {
            this.arr =filteredList;
            notifyDataSetChanged();
        }
    }
    private void anhXa(){

    }
    private void setup(){


    }
    private void setClick(){

    }

}