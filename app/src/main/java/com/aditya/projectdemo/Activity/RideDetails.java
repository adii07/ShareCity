package com.aditya.projectdemo.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.aditya.projectdemo.Data.Data;
import com.aditya.projectdemo.Helper.Connection;
import com.aditya.projectdemo.Helper.Helper;
import com.aditya.projectdemo.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.URL;

public class RideDetails extends AppCompatActivity {
    ImageView dp;
    Button confirm;
    TextView txtstart, txtend, txtphone, txtemail, txtname, txtseats, txtdate, txttime, txtfare;
    String date;
    String cost;
    String seatNumber;
    String time;
    String endDes;
    String startDes;
    String idOwner;
    String phone;
    String imageURL, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ride_details);
        Helper.StrictMode();
        bind();
        get();
        setText();
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    SharedPreferences sharedPreferences = getSharedPreferences("Login", MODE_PRIVATE);
                    String mail = sharedPreferences.getString("email", "");
                    Connection.getConnection("request.php");
                    Data d = new Data(getApplicationContext());
                    String data = "riderId=" + mail + "&startDes=" + startDes + "&finalDes=" + endDes + "&date=" + date;
                    Log.d("da", data);
                    String retdata = d.insertData("request.php", data);
                    JSONObject obj = new JSONObject(retdata);
                    int code = obj.getInt("code");
                    if (code == 1) {
                        Toast.makeText(getApplicationContext(), "Ride Booked", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Booking failed.Try again.", Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    Log.d("confirm", e.toString());
                }
            }
        });

        txtphone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Context context;
                context = v.getContext();
                Intent callIntent=null;
                Toast.makeText(context,"Phone",Toast.LENGTH_LONG).show();

                if (checkSelfPermission(Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {

                    String call = txtphone.getText().toString();
                    callIntent = new Intent(Intent.ACTION_CALL);
                    callIntent.setData(Uri.parse("tel:"+call));
                    context.startActivity(callIntent);
                }

            }
        });
    }
    public void bind()
    {
        confirm=(Button)findViewById(R.id.button14);
        dp=(ImageView)findViewById(R.id.imageView8);
        txtname=(TextView)findViewById(R.id.textView11);
        txtemail=(TextView)findViewById(R.id.textView13);
        txtphone=(TextView)findViewById(R.id.pn);
        txtstart=(TextView)findViewById(R.id.textView15);
        txtend=(TextView)findViewById(R.id.textView16);
        txtdate=(TextView)findViewById(R.id.textView17);
        txtfare=(TextView)findViewById(R.id.textView18);
        txttime=(TextView)findViewById(R.id.textView19);
        txtseats=(TextView)findViewById(R.id.textView20);


   }

   public void get()
   {
       Bundle bundle=getIntent().getExtras();
       date=bundle.getString("Date");
       cost=bundle.getString("cost");
       seatNumber=bundle.getString("seat");
       time=bundle.getString("time");
       endDes=bundle.getString("end");
       startDes=bundle.getString("start");
       idOwner=bundle.getString("id");
       imageURL=bundle.getString("url");
       email=bundle.getString("id");

  }

   public void setText()
   {
       txtname.setText("Owner:"+idOwner);
       txtend.setText("End Destination :"+endDes);
       txtstart.setText("Start Destination:"+startDes);
       txtdate.setText("Date:"+date);
       txtfare.setText("Fare:"+cost);
       txttime.setText("Time:"+time);
       txtseats.setText("Seats:"+seatNumber);
       txtemail.setText("Email:"+email);
//       SharedPreferences sharedPreferences=getSharedPreferences("Login",Context.MODE_PRIVATE);
//       String mail=sharedPreferences.getString("email","");
       String data="mail="+email;
       Log.d("email",data);
       Data d= new Data(getApplicationContext());
       String ret=d.select("phone.php",data);
       try {
           JSONObject jsonObject = new JSONObject(ret);
           String phone = jsonObject.getString("mobile");
           txtphone.setText(phone);
       }
       catch (Exception e)
       {
           Log.d("error",e.toString());
       }


       Log.d("qwerrty",imageURL+"");
       try
       {
           Bitmap bitmap = BitmapFactory.decodeStream((InputStream) new URL(imageURL).getContent());
           dp.setImageBitmap(getRoundedShape(bitmap,400));
       }
       catch (Exception e)
       {
           Log.d("data",e.toString());
       }

   }
    public static Bitmap getRoundedShape(Bitmap scaleBitmapImage,int width) {

        int targetWidth = width;
        int targetHeight = width;
        Bitmap targetBitmap = Bitmap.createBitmap(targetWidth,
                targetHeight,Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(targetBitmap);
        Path path = new Path();
        path.addCircle(((float) targetWidth - 1) / 2,
                ((float) targetHeight - 1) / 2,
                (Math.min(((float) targetWidth),
                        ((float) targetHeight)) / 2),
                Path.Direction.CCW);
        canvas.clipPath(path);
        Bitmap sourceBitmap = scaleBitmapImage;
        canvas.drawBitmap(sourceBitmap,
                new Rect(0, 0, sourceBitmap.getWidth(),
                        sourceBitmap.getHeight()),
                new Rect(0, 0, targetWidth,
                        targetHeight), null);
        return targetBitmap;
    }
}














//    public void showride(String filename)
//    {
//        try
//        {
//            Bundle bundle=getIntent().getExtras();
//            String sid=bundle.getString("sourceid");
//            String did=bundle.getString("destinationid");
//            Log.d("did",""+did);
//            Log.d("sid",""+sid);
//            String dd="source="+sid+"&destination="+did;
//            Data d=new Data(getApplicationContext());
//            String mydata=d.select(filename,dd);
//            Log.d("data",mydata);
//            JSONArray jsonArray=new JSONArray(mydata);
//            date=new String();
//            cost=new String();
//            seatNumber=new String();
//            time=new String();
//            endDes=new String();
//            startDes=new String();
//            idOwner=new String();
//            phone=new String();
//            imageURL=new String();
//            SharedPreferences sharedPreferences=getSharedPreferences("Login", Context.MODE_PRIVATE);
//            String mail=sharedPreferences.getString("email","");
//             data="mail="+mail;
//            Log.d("email",mail);
//            Data da= new Data(getApplicationContext());
//            String ret=da.select("booking.php",data);
//
//            JSONObject jsonObject=null;
//                jsonObject=new JSONObject(ret);
//                date=jsonObject.getString("date");
//                cost=jsonObject.getString("cost");
//                seatNumber=jsonObject.getString("seatNumber");
//                time=jsonObject.getString("TIME");
//                endDes=jsonObject.getString("endDes");
//                startDes=jsonObject.getString("startDes");
//                idOwner=jsonObject.getString("idOwner");
//                phone=jsonObject.getString("mobile");
//                imageURL=jsonObject.getString("imageURL");
//
//        }
//        catch (Exception e)
//        {
//            Log.d("data",e.toString());
//        }
//
//    }
//    public static int getRandom(int max){
//
//        return (int) (Math.random()*max);
//    }



























//        txtname.setText(idOwner);
//                txtemail.setText(data);
//                txtphone.setText(phone);
//                txtseats.setText(seatNumber);
//                txtstart.setText(startDes);
//                txtend.setText(endDes);
//                txttime.setText(time);
//                txtdate.setText(date);
//
//                confirm.setOnClickListener(new View.OnClickListener() {
//@Override
//public void onClick(View v) {
//
//        try {
//        Data d=new Data(getApplicationContext());
//        String data= "requestId="+1+"&riderId="+10+"&startDes="+startDes+"&finalDes="+endDes+"&time="+time+"&seats="+seatNumber+"&date="+date;
//        Log.d("da",data);
//        String retdata=d.insertData("request.php",data);
//        JSONObject obj =new JSONObject(retdata);
//        int code=obj.getInt("code");
//        if(code==1)
//        {
//        Toast.makeText(getApplicationContext(),"Ride Book",Toast.LENGTH_LONG).show();
//        }
//        else
//        {
//        Toast.makeText(getApplicationContext(),"Registration failed.Try again.",Toast.LENGTH_LONG).show();
//        }
//
//        }
//        catch (Exception e){
//        Toast.makeText(getApplicationContext(),""+e.toString(),Toast.LENGTH_LONG).show();
//        Log.d("Data",""+e.toString());
//
//        }
//
//        }
//        });