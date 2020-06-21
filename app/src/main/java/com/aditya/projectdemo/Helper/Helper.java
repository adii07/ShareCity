package com.aditya.projectdemo.Helper;

import android.content.Context;
import android.os.StrictMode;
import android.util.Log;
import android.widget.Toast;

public class Helper
{
    public static void StrictMode()
    {
        StrictMode.ThreadPolicy threadPolicy=new StrictMode.ThreadPolicy.Builder().build();
        StrictMode.setThreadPolicy(threadPolicy);
    }

    public static void makeToast(Context context,String msg)
    {
        Toast.makeText(context,msg,Toast.LENGTH_LONG).show();
    }

    public static  void Log(String type,String msg)
    {
        Log.e(type,msg);
    }
}
