package com.ntdairy.basic_login.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import com.ntdairy.basic_login.R;
import com.ntdairy.basic_login.View.Main_Infor;

public class navigation_header extends Fragment {
    private TextView fragmentname;
    private String username;
    public navigation_header() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

//        return inflater.inflate(R.layout.fragment_navigation_header, container, false);
            View view = inflater.inflate(R.layout.fragment_navigation_header, container, false);

            fragmentname = view.findViewById(R.id.tvfragmentname);
            Bundle data = getArguments();
            if(data != null)
            {
                    username = data.getString("Username");
            }
            else
            {
                Log.e("Error data: ", "deo co data bro");
            }
            fragmentname.setText(username);
            return view;
    }
}