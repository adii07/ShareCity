package com.aditya.projectdemo.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.aditya.projectdemo.Data.Data;
import com.aditya.projectdemo.Helper.Helper;
import com.aditya.projectdemo.R;

import org.json.JSONArray;
import org.json.JSONObject;

public class Search extends AppCompatActivity {

    Spinner source,destination;
    Button btnsearch;
    String[] sourceName;
    String[] desiname;
    int sourseid=0;
    int destid=0;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Helper.StrictMode();
        getView();
        selectsource("searchcity.php");
        ArrayAdapter<String>sourceAdapter=new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item,sourceName);
        source.setAdapter(sourceAdapter);
        ArrayAdapter<String>destiadapter=new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item,desiname);
        destination.setAdapter(destiadapter);

        source.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String item=source.getSelectedItem().toString();
                selectsourceid("selectsid.php",item);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        destination.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item=destination.getSelectedItem().toString();
                selectdesid("selectdid.php",item);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), Show.class);
                intent.putExtra("sourceid",sourseid);
                Log.d("Data",""+sourseid);
                intent.putExtra("destinationid",destid);
                Log.d("Data",""+destid);
                startActivity(intent);
            }
        });
    }

   void getView()
    {
     source=(Spinner)findViewById(R.id.spinner);
     destination=(Spinner)findViewById(R.id.spinner2);
     btnsearch=(Button)findViewById(R.id.button12);

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
               sourceName[i]=jsonObject.getString("city");
               desiname[i]=jsonObject.getString("city");
           }

        }
        catch (Exception e)
        {
            Log.d("Activity",e.toString());
        }
    }
    public void selectsourceid(String filename,String sname)
    {


         try
         {
             Data mydata=new Data(getApplicationContext());

         String data="city="+sname;
         String dd=mydata.select(filename,data);
         JSONObject jsonObject=new JSONObject(dd);
         sourseid=jsonObject.getInt("cityid");
             //Toast.makeText(getApplicationContext()," "+sourseid,Toast.LENGTH_LONG).show();
         }
         catch (Exception e)
         {
            Log.d("Activity",e.toString());
         }
    }

    public void selectdesid(String filename,String sname)
    {


        try
        {
            Data mydata=new Data(getApplicationContext());

            String data="city="+sname;
            String dd=mydata.select(filename,data);
            JSONObject jsonObject=new JSONObject(dd);
            destid=jsonObject.getInt("cityid");
            //Toast.makeText(getApplicationContext()," "+destid,Toast.LENGTH_LONG).show();
        }
        catch (Exception e)
        {
         Log.d("Activity",e.toString());
        }
    }
}
