package com.aditya.projectdemo.ui.gallery;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.aditya.projectdemo.Activity.Home;
import com.aditya.projectdemo.Activity.RequestHistory;
import com.aditya.projectdemo.Activity.RideRequest;
import com.aditya.projectdemo.Adaptor.HistoryAdaptor;
import com.aditya.projectdemo.Data.Data;
import com.aditya.projectdemo.Helper.Helper;
import com.aditya.projectdemo.R;

import org.json.JSONArray;
import org.json.JSONObject;

public class GalleryFragment extends Fragment {
   Button btnridehistory,btnrequesthistory;
    private GalleryViewModel galleryViewModel;
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        galleryViewModel =ViewModelProviders.of(this).get(GalleryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        Helper.StrictMode();
        btnridehistory=(Button)root.findViewById(R.id.button16);
        btnrequesthistory=(Button)root.findViewById(R.id.button18);
        btnridehistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity().getApplicationContext(), RideRequest.class);
                startActivity(intent);
            }
        });

        btnrequesthistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity().getApplicationContext(), RequestHistory.class);
                startActivity(intent);
            }
        });


        return root;
    }




}