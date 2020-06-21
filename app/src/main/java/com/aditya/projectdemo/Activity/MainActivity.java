package com.aditya.projectdemo.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.aditya.projectdemo.Data.Data;
import com.aditya.projectdemo.Helper.Connection;
import com.aditya.projectdemo.Helper.Helper;
import com.aditya.projectdemo.R;

import org.json.JSONObject;

import java.net.URLEncoder;

public class MainActivity extends AppCompatActivity {

    Button btnInsert;
    EditText txtName, txtpassword, txtmail, txtphone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        Helper.StrictMode();
        bind();
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(validate() == true)
                {
                    Connection.getConnection("insertuser.php");
                    String name = txtName.getText().toString();
                    String passs = txtpassword.getText().toString();
                    String Email = txtmail.getText().toString();
                    String Phone = txtphone.getText().toString();


                    try {
                        Data d = new Data(getApplicationContext());
                        //String data= "username="+name+"&password="+passs+"&phone="+Phone+"&email="+Email;

                        String data = URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(name, "UTF-8") +
                                "&" + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(passs, "UTF-8") +
                                "&" + URLEncoder.encode("phone", "UTF-8") + "=" + URLEncoder.encode(Phone, "UTF-8") +
                                "&" + URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(Email, "UTF-8");
                        Log.d("data", data);
                        String retdata = d.insertData("insertuser.php", data);
                        JSONObject obj = new JSONObject(retdata);
                        int code = obj.getInt("code");
                        if (code == 1) {
                            Toast.makeText(getApplicationContext(), "Registered", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Registration failed.Try again.", Toast.LENGTH_LONG).show();
                        }

                    } catch (Exception e)
                    {
                        Log.d("Data", "" + e.toString());

                    }
                    Intent intent = new Intent(getApplicationContext(), UploadImage.class);
                    intent.putExtra("email", Email);
                    startActivity(intent);
                }
                else
                {
                    Helper.makeToast(getApplicationContext(),"Enter correct data");
                }
            }


        });


    }

    public void bind() {
        txtName=(EditText)findViewById(R.id.editText);
        txtpassword=(EditText)findViewById(R.id.editText5);
        txtmail=(EditText)findViewById(R.id.editText3);
        txtphone=(EditText)findViewById(R.id.editText4);
        btnInsert=(Button)findViewById(R.id.button);
    }

    public boolean validate()
    {
        boolean valid = true;
        String MobilePattern="[0-9]{10}";
        String regexStr = "^[0-9]*$";
        String ValidationExpression="^(?=.*[0-9])(?=.*[a-z])(?=.*[@#$%^&+=])(?=\\S+$).{5,10}$";
        String docname = txtName.getText().toString();
        String email = txtmail.getText().toString();
        String password = txtpassword.getText().toString();
        String phone=txtphone.getText().toString();

        if (docname.isEmpty() || docname.length() < 5) {
            txtName.setError("at least 5 characters");
            valid = false;
        } else {
            txtName.setError(null);
        }
        if (password.isEmpty())
        {
            txtpassword.setError("password can not be empty");
            valid = false;
        } else
        {
            txtpassword.setError(null);
        }


        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            txtmail.setError("enter a valid email address");
            valid = false;
        } else {
            txtmail.setError(null);
        }

        if (password.matches(ValidationExpression))
        {
            txtpassword.setError(null);

        } else
        {
            txtpassword.setError("password should be combination of character special character and number");
            valid = false;
        }


        if(txtphone.getText().toString().matches(MobilePattern))
        {
            txtphone.setError(null);
        }
        else
        {
            txtphone.setError("Enter valid phone number");
            valid=false;

        }

        return valid;
    }

}