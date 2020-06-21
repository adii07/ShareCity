package com.aditya.projectdemo.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.aditya.projectdemo.Helper.Helper;
import com.aditya.projectdemo.R;

public class Home extends AppCompatActivity
{
    Button btncreate,btnbook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_home);
        Helper.StrictMode();
        bind();
        btncreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), Ride.class);
                startActivity(intent);

            }
        });

        btnbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),Request.class);
                startActivity(intent);

            }
        });


    }
    public void bind() {
        btncreate=(Button)findViewById(R.id.button5);
        btnbook=(Button)findViewById(R.id.button6);
    }
}
