package com.aditya.projectdemo.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.ListView;

import com.aditya.projectdemo.Adaptor.RideAdapter;
import com.aditya.projectdemo.Data.Data;
import com.aditya.projectdemo.Helper.Helper;
import com.aditya.projectdemo.R;

import org.json.JSONArray;
import org.json.JSONObject;

public class Show extends AppCompatActivity {

    String[] date;
    int[] cost;
    int[]seatNumber;
    String[] time;
    String[] endDes;
    String[] startDes;
    String [] idOwner;
    String[] imageURL;
    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_show);
        Helper.StrictMode();
      showride("booking.php");
        recyclerView=(RecyclerView)findViewById(R.id.rec);
        RideAdapter adp=new RideAdapter(date,cost,seatNumber,time,startDes,endDes,idOwner,imageURL);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.hasFixedSize();
        recyclerView.setAdapter(adp);



    }


    public void showride(String filename)
    {
        try
        {
            Bundle bundle=getIntent().getExtras();
            String sid=bundle.getString("sourceid");
            String did=bundle.getString("destinationid");
            String da=bundle.getString("Date");
            Log.d("did",""+did);
            Log.d("sid",""+sid);
            String dd="source="+sid+"&destination="+did+"&Date="+da;
           Data d=new Data(getApplicationContext());
           String mydata=d.select(filename,dd);
           Log.d("data",mydata);
            JSONArray jsonArray=new JSONArray(mydata);
            date=new String[jsonArray.length()];
            cost=new int[jsonArray.length()];
            seatNumber=new int[jsonArray.length()];
            time=new String[jsonArray.length()];
            endDes=new String[jsonArray.length()];
            startDes=new String[jsonArray.length()];
            idOwner=new String[jsonArray.length()];
            imageURL=new String[jsonArray.length()];

            JSONObject jsonObject=null;
            for(int i=0;i<jsonArray.length();i++)
            {
                jsonObject=jsonArray.getJSONObject(i);
                date[i]=jsonObject.getString("date");
                cost[i]=jsonObject.getInt("cost");
                seatNumber[i]=jsonObject.getInt("seatNumber");
                time[i]=jsonObject.getString("time");
                endDes[i]=jsonObject.getString("endDes");
                startDes[i]=jsonObject.getString("startDes");
                idOwner[i]=jsonObject.getString("idOwner");
                imageURL[i]=jsonObject.getString("imageURL");

             // Log.d("DATA",imageURL[i]+""+idOwner[i]+" "+startDes[i]+endDes[i]);
            }
        }
        catch (Exception e)
        {
            Log.d("data",e.toString());
        }
    }
}
