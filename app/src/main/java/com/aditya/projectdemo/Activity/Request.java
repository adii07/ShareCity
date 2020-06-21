package com.aditya.projectdemo.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.aditya.projectdemo.Data.Data;
import com.aditya.projectdemo.Helper.Connection;
import com.aditya.projectdemo.Helper.Helper;
import com.aditya.projectdemo.R;

import org.json.JSONArray;
import org.json.JSONObject;

public class Request extends AppCompatActivity {

    Button btnbook;
    EditText txtRider,txtDate,txtTime,txtSeats;
    TextView start,end;
    String[] sourceName;
    String[] desiname;
    String sourseid="";
    String destid="";
    String date="";
    AutoCompleteTextView source,destination;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_request);
        Helper.StrictMode();
        bind();
        selectsource("searchcity.php");
        ArrayAdapter<String> sourceAdapter=new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item,sourceName);
        source.setAdapter(sourceAdapter);
        ArrayAdapter<String>destiadapter=new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item,desiname);
        destination.setAdapter(destiadapter);

//        source.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//
//                String sname=source.getSelectedItem().toString();
//                //selectsourceid("selectsid.php",item);
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
//
//        destination.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                String dname=destination.getSelectedItem().toString();
//                //selectdesid("selectdid.php",item);
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });

        btnbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Connection.getConnection("request.php");
                //String riderID=txtRider.getText().toString();
                sourseid=source.getText().toString();
                destid=destination.getText().toString();
                date=txtDate.getText().toString();
//                String time=txtTime.getText().toString();
//                String seats=txtSeats.getText().toString();



//                try {
//                    Data d=new Data(getApplicationContext());
//                    String data= "requestId="+r+"&riderId="+riderID+"&startDes="+sourseid+"&finalDes="+destid+"&time="+time+"&seats="+seats+"&date="+date;
//                    Log.d("da",data);
////                    String retdata=d.insertData("request.php",data);
////                    JSONObject obj =new JSONObject(retdata);
////                    int code=obj.getInt("code");
////                    if(code==1)
////                    {
////                        Toast.makeText(getApplicationContext(),"Ride Book",Toast.LENGTH_LONG).show();
////                    }
////                    else
////                    {
////                        Toast.makeText(getApplicationContext(),"Registration failed.Try again.",Toast.LENGTH_LONG).show();
////                    }
//
//                }
//                catch (Exception e){
//                    Toast.makeText(getApplicationContext(),""+e.toString(),Toast.LENGTH_LONG).show();
//                    Log.d("Data",""+e.toString());
//
//                }
                Intent intent=new Intent(getApplicationContext(), Show.class);
                intent.putExtra("sourceid",sourseid);
                Log.d("Data",""+sourseid);
                intent.putExtra("destinationid",destid);
                Log.d("Data",""+destid);
                intent.putExtra("Date",date);
                Log.d("Data",""+date);;
                startActivity(intent);

            }
        });

    }
    public void bind()
    {
        btnbook=(Button)findViewById(R.id.button11);
//        start=(TextView)findViewById(R.id.textView22);
//        end=(TextView)findViewById(R.id.textView23);
        txtDate=(EditText)findViewById(R.id.editText25);
        source=(AutoCompleteTextView) findViewById(R.id.sc);
        destination=(AutoCompleteTextView) findViewById(R.id.ds);
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

