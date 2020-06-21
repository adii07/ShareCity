package com.aditya.projectdemo.ui.tools;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.aditya.projectdemo.Activity.Request;
import com.aditya.projectdemo.Activity.Ride;
import com.aditya.projectdemo.Helper.Helper;
import com.aditya.projectdemo.R;

public class ToolsFragment extends Fragment
{
    Button btncreate,btnbook;

    private ToolsViewModel toolsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        toolsViewModel =
                ViewModelProviders.of(this).get(ToolsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_tools, container, false);
        Helper.StrictMode();
        bind(root);
        btncreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(), Ride.class);
                startActivity(intent);
            }
        });

        btnbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(), Request.class);
                startActivity(intent);
            }
        });
        return root;
    }
    public void bind(View view)
    {
        btncreate=(Button)view.findViewById(R.id.button17);
        btnbook=(Button)view.findViewById(R.id.button19);
    }
}