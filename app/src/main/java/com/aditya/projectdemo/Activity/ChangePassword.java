package com.aditya.projectdemo.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.aditya.projectdemo.Data.Data;
import com.aditya.projectdemo.Helper.Helper;
import com.aditya.projectdemo.R;
import com.aditya.projectdemo.ui.home.HomeFragment;

import org.json.JSONObject;

public class ChangePassword extends AppCompatActivity {
    Button change;
    EditText oldpass,newpass;
    String txtOldPassword,txtNewPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        Helper.StrictMode();
        bind();
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences=getSharedPreferences("Login",MODE_PRIVATE);
                String mail=sharedPreferences.getString("email","");
                try {
                    get();
                    Data d=new Data(getApplicationContext());
                    String data="email="+mail+"&pass="+txtOldPassword+"&newpass="+txtNewPassword;
                    Log.d("data",data);
                    String retdata=d.insertData("password.php",data);
                    Log.d("ret",retdata);
                    JSONObject obj =new JSONObject(retdata);
                    int code=obj.getInt("code");
                    if(code==1)
                    {
                        Toast.makeText(getApplicationContext(),"Password Changed",Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"Password change failed.Try again.",Toast.LENGTH_LONG).show();
                    }

                }
                catch (Exception e)
                {
                    Log.d("Data",""+e.toString());
                }

            }
        });

    }

    public void bind()//bind the xml elements
    {
        change=(Button)findViewById(R.id.subpass);
        oldpass=(EditText)findViewById(R.id.oldpass);
        newpass=(EditText)findViewById(R.id.newpass);
    }

    public void get()//get data from the xml elements
    {
        txtOldPassword=oldpass.getText().toString();
        txtNewPassword=newpass.getText().toString();
    }

}
