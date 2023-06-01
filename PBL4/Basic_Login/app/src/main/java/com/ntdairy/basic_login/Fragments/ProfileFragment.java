package com.ntdairy.basic_login.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ntdairy.basic_login.R;
import com.ntdairy.basic_login.View.Main_Infor;


public class ProfileFragment extends Fragment {

    public ProfileFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
//
//                    Intent intent = new Intent (getActivity(), Main_Infor.class);
//                    Bundle bundle = new Bundle();
//                    bundle.putString("Username",Username);
//                    intent.putExtra("Bun_Username",bundle);
//                    startActivity(intent);
        return view;
    }
}