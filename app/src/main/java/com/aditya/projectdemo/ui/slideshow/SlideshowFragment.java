package com.aditya.projectdemo.ui.slideshow;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SharedMemory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.aditya.projectdemo.Activity.Request;
import com.aditya.projectdemo.Activity.RequestHistory;
import com.aditya.projectdemo.Activity.Ride;
import com.aditya.projectdemo.Activity.RideRequest;
import com.aditya.projectdemo.R;
import com.aditya.projectdemo.ui.gallery.GalleryFragment;
import com.aditya.projectdemo.ui.home.HomeFragment;
import com.aditya.projectdemo.ui.share.ShareFragment;

public class SlideshowFragment extends Fragment {
    ImageView btnuser,btnhistory,btncreate,btnbook,btnrate,btnhis;

    private SlideshowViewModel slideshowViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.fragment_slideshow, container, false);
        bind(root);
        btnuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
//                Toast.makeText(getActivity().getApplicationContext(),"Click",Toast.LENGTH_LONG).show();
                HomeFragment homeFragment=new HomeFragment();
                FragmentManager fragmentManager=getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment,homeFragment);
                fragmentTransaction.commit();

            }
        });
        btnhistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity().getApplicationContext(), RideRequest.class);
                startActivity(intent);
            }
        });
        btncreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity().getApplicationContext(), Ride.class);
                startActivity(intent);
            }
        });

        btnbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity().getApplicationContext(), Request.class);
                startActivity(intent);
            }
        });

        btnrate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShareFragment shareFragment=new ShareFragment();
                FragmentManager fragmentManager=getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.nav_share,shareFragment);
                fragmentTransaction.commit();
            }
        });

        btnhis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity().getApplicationContext(), RequestHistory.class);
                startActivity(intent);
            }
        });

        return root;
    }

    public void bind(View view)
    {
        btnuser=(ImageView) view.findViewById(R.id.userHome);
        btnhistory=(ImageView) view.findViewById(R.id.historyHome);
        btncreate=(ImageView) view.findViewById(R.id.imageView12);
        btnbook=(ImageView) view.findViewById(R.id.imageView11);
        btnrate=(ImageView) view.findViewById(R.id.imageView14);
        btnhis=(ImageView) view.findViewById(R.id.imageView13);


    }

}