package com.aditya.projectdemo.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
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

import org.json.JSONObject;

import java.net.URLEncoder;

public class car extends AppCompatActivity {

    Button btnReg;
    EditText txtName, txtmaker, txtyear, txtcolor,txtrc,txtnumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car);
        Helper.StrictMode();
        bind();
       btnReg.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
//               Connection.getConnection("car.php");
               String name=txtName.getText().toString();
               String make=txtmaker.getText().toString();
               String year=txtyear.getText().toString();
               String color=txtcolor.getText().toString();
               String rc=txtrc.getText().toString();
//               String uid=txtuid.getText().toString();
               String num=txtnumber.getText().toString();
               SharedPreferences sharedPreferences=getSharedPreferences("Login", Context.MODE_PRIVATE);
               String uid=sharedPreferences.getString("email","");


               try {
                   Data d=new Data(getApplicationContext());
                   //String data= "username="+name+"&password="+passs+"&phone="+Phone+"&email="+Email;

                   String data= URLEncoder.encode("name","UTF-8")+"="+URLEncoder.encode(name,"UTF-8")+
                           "&"+URLEncoder.encode("maker","UTF-8")+"="+URLEncoder.encode(make,"UTF-8")+
                           "&"+URLEncoder.encode("modelYear","UTF-8")+"="+URLEncoder.encode(year,"UTF-8")+
                           "&"+URLEncoder.encode("color","UTF-8")+"="+URLEncoder.encode(color,"UTF-8")+
                           "&"+URLEncoder.encode("rc","UTF-8")+"="+URLEncoder.encode(rc,"UTF-8")+
                           "&"+URLEncoder.encode("uid","UTF-8")+"="+URLEncoder.encode(uid,"UTF-8")+
                           "&"+URLEncoder.encode("number","UTF-8")+"="+URLEncoder.encode(num,"UTF-8");
                   Log.d("data",data);
                   String retdata=d.insertData("car.php",data);
                   JSONObject obj =new JSONObject(retdata);
                   int code=obj.getInt("code");
                   if(code==1)
                   {
                       Toast.makeText(getApplicationContext(),"Registered",Toast.LENGTH_LONG).show();
                   }
                   else
                   {
                       Toast.makeText(getApplicationContext(),"Registration failed.Try again.",Toast.LENGTH_LONG).show();
                   }

               }
               catch (Exception e){
                   Log.d("Data",""+e.toString());

               }
           }
       });



    }

    public void bind() {
        txtName=(EditText)findViewById(R.id.editText13);
        txtmaker=(EditText)findViewById(R.id.editText14);
        txtyear=(EditText)findViewById(R.id.editText18);
        txtcolor=(EditText)findViewById(R.id.editText28);
        txtrc=(EditText)findViewById(R.id.editText29);
//        txtuid=(EditText)findViewById(R.id.editText30);
        txtnumber=(EditText)findViewById(R.id.editText32);
        btnReg=(Button)findViewById(R.id.button15);
    }
}