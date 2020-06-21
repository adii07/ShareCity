package com.aditya.projectdemo.ui.send;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.aditya.projectdemo.Activity.login;
import com.aditya.projectdemo.Helper.Helper;
import com.aditya.projectdemo.R;
import com.aditya.projectdemo.ui.home.HomeFragment;

public class SendFragment extends Fragment {
    Button btnYes,btnNo;

    private SendViewModel sendViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_send, container, false);
        Helper.StrictMode();
        bind(root);

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getActivity().getApplicationContext(), login.class);
                startActivity(intent);
            }
        });

        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity().getApplicationContext(), HomeFragment.class);
                startActivity(intent);
            }
        });

        return root;
    }
    public void bind(View view)
    {
        btnYes=(Button)view.findViewById(R.id.yes);
        btnNo=(Button)view.findViewById(R.id.no);

    }
}