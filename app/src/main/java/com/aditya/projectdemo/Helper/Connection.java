package com.aditya.projectdemo.Helper;

import android.webkit.URLUtil;

import java.net.HttpURLConnection;
import java.net.URL;

import static java.lang.Boolean.TRUE;

public class Connection
{
    public static HttpURLConnection getConnection(String filename)
    {
        HttpURLConnection connection=null;
        //String path="http://sharecity.000webhostapp.com/ride/"+filename;
        String path="http://192.168.43.190/login/"+filename;

        try
        {
            URL url=new URL(path);
            connection=(HttpURLConnection) url.openConnection();
            connection.setDoOutput(TRUE);
            connection.setRequestMethod("POST");

            Helper.Log("Connection","Connection Established");
        }
        catch (Exception e)
        {
            Helper.Log("Connection",e.toString());
        }
        return connection;
    }

}
