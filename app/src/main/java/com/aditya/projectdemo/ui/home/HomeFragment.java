package com.aditya.projectdemo.ui.home;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.aditya.projectdemo.Activity.ChangePassword;
import com.aditya.projectdemo.Activity.User;
import com.aditya.projectdemo.Activity.car;
import com.aditya.projectdemo.Data.Data;
import com.aditya.projectdemo.Helper.Helper;
import com.aditya.projectdemo.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.URL;

public class HomeFragment extends Fragment
{
    TextView change,name,phone,email,password;
    EditText txtphone,txtemail,txtpassword;
    ImageView profile;
    EditText txtname;
    Button reg,btnchange;



    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        Helper.StrictMode();
        bind(root);
        SharedPreferences sharedPreferences=getActivity().getSharedPreferences("Login",Context.MODE_PRIVATE);
        String mail=sharedPreferences.getString("email","");
        String data="mail="+mail;
        Log.d("email",mail);
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity().getApplicationContext(), car.class);
                startActivity(intent);
            }
        });


        Data d= new Data(getActivity().getApplicationContext());
        String ret=d.select("selectuser.php",data);
        try
        {
            JSONObject jsonObject=new JSONObject(ret);
            String uName=jsonObject.getString("username");
            String pass1=jsonObject.getString("password");
            String phone=jsonObject.getString("mobile");
            String image=jsonObject.getString("imageURL");

            txtname.setText(uName);
            txtphone.setText(phone);
            txtpassword.setText(pass1);
            txtemail.setText(mail);
            try
            {
                Bitmap bitmap= BitmapFactory.decodeStream((InputStream)new URL(image).getContent());
                profile.setImageBitmap(getRoundedShape(bitmap,200));
            }
            catch (Exception e)
            {
                Log.d("image",e.toString());
            }
        }
        catch (Exception e)
        {
            Log.d("error",e.toString());

        }
        btnchange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity().getApplicationContext(), ChangePassword.class);
                startActivity(intent);
            }
        });
        return root;
    }

    public void bind(View view)
    {
        profile=(ImageView)view.findViewById(R.id.imageView6);
        //change=(TextView)view.findViewById(R.id.textView2);
        name=(TextView)view.findViewById(R.id.textView4);
        phone=(TextView)view.findViewById(R.id.textView5);
        email=(TextView)view.findViewById(R.id.textView6);
        password=(TextView)view.findViewById(R.id.textView8);
        txtname=(EditText)view.findViewById(R.id.editText17);
        txtphone=(EditText)view.findViewById(R.id.editText31);
        txtemail=(EditText)view.findViewById(R.id.editText33);
        txtpassword=(EditText)view.findViewById(R.id.editText34);
        reg=(Button) view.findViewById(R.id.button20);
        btnchange=(Button) view.findViewById(R.id.button21);
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