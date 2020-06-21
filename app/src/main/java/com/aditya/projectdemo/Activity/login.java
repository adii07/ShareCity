package com.aditya.projectdemo.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.aditya.projectdemo.Data.Data;
import com.aditya.projectdemo.Helper.Helper;
import com.aditya.projectdemo.R;

import org.json.JSONObject;

public class login extends AppCompatActivity {

        EditText txtUsername,txtpassword;
        Button btnLogin,btnregister;
        TextView signUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);
        Helper.StrictMode();
        bind();
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            //  2nd method
            String textName=txtUsername.getText().toString();
                String pass=txtpassword.getText().toString();
                SharedPreferences sharedPreferences=getSharedPreferences("Login",MODE_PRIVATE);
               SharedPreferences.Editor editor=sharedPreferences.edit();
                String data="username="+textName+"&userpass="+pass;
                Data d= new Data(getApplicationContext());
                String ret=d.select("login.php",data);
                try
                {
                    JSONObject jsonObject=new JSONObject(ret);
                    String uName=jsonObject.getString("username");
                    String pass1=jsonObject.getString("password");
                    String email=jsonObject.getString("email");
                    String image=jsonObject.getString("imageURL");
                    if ((textName.equals(email))&&(pass.equals(pass1)))
                    {
                        Toast.makeText(getApplicationContext(),"welcome",Toast.LENGTH_LONG).show();
                        Intent intent=new Intent(getApplicationContext(), User.class);
                        intent.putExtra("sourceid",uName);
                        Log.d("Data",""+uName);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(getApplicationContext(),"Wrong username or password",Toast.LENGTH_LONG).show();
                    }

                    editor.putString("username",uName);
                    editor.putString("password",pass1);
                    editor.putString("email",email);
                    editor.putString("imageURL",image);
                    editor.putBoolean("Status",true);
                   editor.commit();
                }




                //1st method
//                String txtUser= txtUsername.getText().toString();
//                String txtPass=txtpassword.getText().toString();
//                SharedPreferences sharedPreferences=getSharedPreferences("Login",MODE_PRIVATE);
//               SharedPreferences.Editor editor=sharedPreferences.edit();
//                String data="username="+txtUser+"&userpass="+txtPass;
//                Data d= new Data(getApplicationContext());
//                String ret=d.select("login.php",data);
//                try {
//                    JSONObject jsonObject=new JSONObject(ret);
////                    String uname=jsonObject.getString("username");
////                    String pass=jsonObject.getString("password");
//                    int code=jsonObject.getInt("code");
//                    if(code==1)
//                    {
//                        Toast.makeText(getApplicationContext(),"welcome",Toast.LENGTH_LONG).show();
//                    }
//                    else {
//                        Toast.makeText(getApplicationContext(),"Wrong username or password",Toast.LENGTH_LONG).show();
//                    }
////                    editor.putString("username",uname);
////                    editor.putString("password",pass);
////                    editor.putBoolean("Status",true);
////                    editor.commit();
//                }
                catch (Exception e)
                {
                    Log.d("Error",e.toString());
                }
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });

    }

    public void bind() {
        txtUsername=(EditText)findViewById(R.id.editText2);
        txtpassword=(EditText)findViewById(R.id.editText7);
        btnLogin=(Button)findViewById(R.id.button2);
        //btnregister=(Button)findViewById(R.id.button3);
        signUp=(TextView)findViewById(R.id.textView);
    }
}
