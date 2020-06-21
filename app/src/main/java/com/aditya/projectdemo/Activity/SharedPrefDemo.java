package com.aditya.projectdemo.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.aditya.projectdemo.R;

public class SharedPrefDemo extends AppCompatActivity {

    Button btnAddpref,btnDeletepref,btnUpdate,btnSelect,btnDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_pref_demo);
        btnAddpref=(Button)findViewById(R.id.button3);
        btnDeletepref=(Button)findViewById(R.id.button4);
        btnUpdate=(Button)findViewById(R.id.button7);
        btnSelect=(Button)findViewById(R.id.button8);
        btnDisplay=(Button)findViewById(R.id.button9);
        btnAddpref.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences=getSharedPreferences("Demo",MODE_PRIVATE);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putString("FirstName","Aditya");
                editor.putString("LastName","Sharma");
                editor.putInt("Age",19);
                editor.putFloat("Salary",98765);
                editor.commit();
                Toast.makeText(getApplicationContext(),"Data Saved!",Toast.LENGTH_LONG).show();
            }
        });

        btnDeletepref.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                SharedPreferences sharedPreferences=getSharedPreferences("Demo",MODE_PRIVATE);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.remove("FirstName");
                editor.remove("LastName");
                editor.remove("Age");
                editor.remove("Salary");
                editor.apply();
                Toast.makeText(getApplicationContext(),"Data Delete!",Toast.LENGTH_LONG).show();
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences=getSharedPreferences("Demo",MODE_PRIVATE);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putString("FirstName","Addy");
                editor.putString("LastName","");
                editor.putInt("Age",19);
                editor.putFloat("Salary",98765);
                editor.apply();
                Toast.makeText(getApplicationContext(),"Data Updated!",Toast.LENGTH_LONG).show();

            }
        });
        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Data Selected!",Toast.LENGTH_LONG).show();
            }
        });

        btnDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                SharedPreferences sharedPreferences=getSharedPreferences("Demo",MODE_PRIVATE);
                String fName=sharedPreferences.getString("FirstName","");
                String lName=sharedPreferences.getString("LastName","");
                int age=sharedPreferences.getInt("Age",0);
                float sal=sharedPreferences.getFloat("Salary",0.00F);
                Toast.makeText(getApplicationContext(),"name = "+fName+" "+lName+"\nAge = "+age+"\nSalary = "+sal,Toast.LENGTH_LONG).show();


            }
        });

    }
}
