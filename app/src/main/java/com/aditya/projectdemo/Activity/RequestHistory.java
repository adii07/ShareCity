package com.aditya.projectdemo.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.aditya.projectdemo.Adaptor.HistoryAdaptor;
import com.aditya.projectdemo.Adaptor.RequestAdaptor;
import com.aditya.projectdemo.Data.Data;
import com.aditya.projectdemo.Helper.Helper;
import com.aditya.projectdemo.R;

import org.json.JSONArray;
import org.json.JSONObject;

public class RequestHistory extends AppCompatActivity {
    String[] source;
    String[] destnation;
    int[] fare;
    String[] date;
    String[] img;
    ListView reqhis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_history);
        Helper.StrictMode();
        reqhis=(ListView)findViewById(R.id.reqhis);
        ridehis("history1.php");
        RequestAdaptor adp=new RequestAdaptor(this,source,destnation,date,fare,img);
        reqhis.setAdapter(adp);
    }

    public void ridehis(String filename)
    {
        try {
            SharedPreferences sharedPreferences=getSharedPreferences("Login", Context.MODE_PRIVATE);
            String mail=sharedPreferences.getString("email","");
            String dd="username="+mail;
            Log.d("last",dd);
            Data d=new Data(getApplicationContext());
            String mydata=d.select(filename,dd);
            Log.d("data",mydata);
            JSONArray jsonArray=new JSONArray(mydata);
            date=new String[jsonArray.length()];
            fare=new int[jsonArray.length()];
            destnation=new String[jsonArray.length()];
            source=new String[jsonArray.length()];
            img=new String[jsonArray.length()];

            JSONObject jsonObject=null;
            for(int i=0;i<jsonArray.length();i++)
            {
                jsonObject=jsonArray.getJSONObject(i);
                date[i]=jsonObject.getString("date");
                fare[i]=jsonObject.getInt("fare");
                destnation[i]=jsonObject.getString("destinationid");
                source[i]=jsonObject.getString("sourceidtext");
                img[i]=jsonObject.getString("ownerURL");
            }
        }
        catch (Exception e)
        {
            Log.d("log",e.toString());
        }
    }
}
