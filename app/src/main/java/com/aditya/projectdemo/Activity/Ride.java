package com.aditya.projectdemo.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.aditya.projectdemo.Data.Data;
import com.aditya.projectdemo.Helper.Connection;
import com.aditya.projectdemo.Helper.Helper;
import com.aditya.projectdemo.R;

import org.json.JSONArray;
import org.json.JSONObject;

public class Ride extends AppCompatActivity
{
    Button btncreate;
    EditText txtcId,txtoId,txtStart,txtEnd,txtDate,txtTime,intSeats,intCost;
    String[] sourceName;
    String[] desiname;
    int sourseid=0;
    int destid=0;
    AutoCompleteTextView source,destination;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_ride);
        Helper.StrictMode();
        bind();
        selectsource("searchcity.php");
        ArrayAdapter<String> sourceAdapter=new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item,sourceName);
        source.setAdapter(sourceAdapter);
        ArrayAdapter<String>destiadapter=new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item,desiname);
        destination.setAdapter(destiadapter);

        btncreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Connection.getConnection("ride.php");
                //String carID=txtcId.getText().toString();
                //String ownerID=txtoId.getText().toString();
                String start=source.getText().toString();
                String end=destination.getText().toString();
                String date=txtDate.getText().toString();
                String time=txtTime.getText().toString();
                String seats=intSeats.getText().toString();
                String cost=intCost.getText().toString();
                Double c=Math.random();
                Double carID=c;
                SharedPreferences sharedPreferences=getSharedPreferences("Login",MODE_PRIVATE);
                String ownerID=sharedPreferences.getString("email","");



                try {
                    Data d=new Data(getApplicationContext());
                    String data= "idCar="+carID+"&idOwner="+ownerID+"&startDes="+start+"&endDes="+end+"&date="+date+"&time="+time+"&seatNumber="+seats+"&cost="+cost;
                    Log.d("data",data);
                    String retdata=d.insertData("ride.php",data);
                    JSONObject obj =new JSONObject(retdata);
                    int code=obj.getInt("code");
                    if(code==1)
                    {
                        Toast.makeText(getApplicationContext(),"Ride Created",Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"Failed.Try again.",Toast.LENGTH_LONG).show();
                    }

                }
                catch (Exception e){
                   Log.d("Data",""+e.toString());

                }




            }
        });
    }

    public void bind()
    {
//        txtcId=(EditText)findViewById(R.id.editText19);
//        txtoId=(EditText)findViewById(R.id.editText20);
        source=(AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);
        destination=(AutoCompleteTextView) findViewById(R.id.autoCompleteTextView2);
        txtDate=(EditText)findViewById(R.id.editText23);
        txtTime=(EditText)findViewById(R.id.editText24);
        intSeats=(EditText)findViewById(R.id.editText11);
        intCost=(EditText)findViewById(R.id.editText12);
        btncreate=(Button)findViewById(R.id.button10);
    }
    public void selectsource(String filename)
    {
        try
        {
            Data data=new Data(getApplicationContext());
            JSONArray mydata=data.selectAll(filename);
            sourceName=new String[mydata.length()];
            desiname=new String[mydata.length()];
            JSONObject jsonObject=null;
            for (int i=0;i<mydata.length();i++)
            {
                jsonObject=mydata.getJSONObject(i);
                sourceName[i]=jsonObject.getString("name");
                desiname[i]=jsonObject.getString("name");
            }

        }
        catch (Exception e)
        {
            Log.d("Activity",e.toString());
        }
    }

}
