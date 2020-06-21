package com.aditya.projectdemo.ui.share;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.aditya.projectdemo.Data.Data;
import com.aditya.projectdemo.Helper.Connection;
import com.aditya.projectdemo.Helper.Helper;
import com.aditya.projectdemo.R;

import org.json.JSONObject;

import java.net.URLEncoder;

public class ShareFragment extends Fragment {
    RatingBar rating;
    TextView txtReview;
    EditText Review;
    Button submitt;

    private ShareViewModel shareViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View root = inflater.inflate(R.layout.fragment_share, container, false);
        Helper.StrictMode();
        bind(root);


       submitt.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Connection.getConnection("rating.php");
               final float rate= rating.getRating();
               String txtreview=Review.getText().toString();
               try {
                   Data d= new Data(getActivity().getApplicationContext());
                   String data= URLEncoder.encode("rate","UTF-8")+"="+URLEncoder.encode(String.format("%s", rate),"UTF-8")+
                           "&"+URLEncoder.encode("review","UTF-8")+"="+URLEncoder.encode(txtreview,"UTF-8");
                   Log.d("rate",data);
                   String retdata=d.insertData("rating.php",data);
                   JSONObject obj =new JSONObject(retdata);
                   int code=obj.getInt("code");
                   if(code==1)
                   {
                       Toast.makeText(getActivity().getApplicationContext(),"Rating added",Toast.LENGTH_LONG).show();
                   }
                   else
                   {
                       Toast.makeText(getActivity().getApplicationContext(),"Failed to review.Try again.",Toast.LENGTH_LONG).show();
                   }
               }
               catch (Exception e)
               {
                    Log.d("rate",e.toString());
               }
           }
       });

        return root;
    }

    public void bind( View view)
    {
        rating=(RatingBar)view.findViewById(R.id.ratingBar2);
        txtReview=(TextView)view.findViewById(R.id.textView3);
        Review=(EditText)view.findViewById(R.id.editText16);
        submitt=(Button)view.findViewById(R.id.button13);

    }
}