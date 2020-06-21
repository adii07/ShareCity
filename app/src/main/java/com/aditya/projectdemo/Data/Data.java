package com.aditya.projectdemo.Data;

import android.content.Context;

import com.aditya.projectdemo.Helper.Connection;
import com.aditya.projectdemo.Helper.Helper;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;

public class Data
{
     Context context;
    public Data(Context context)
    {
        this.context=context;
    }

    public String insertData(String filename,String data)
    {
        String line="";
        StringBuilder stringBuilder=new StringBuilder();

        try {
            HttpURLConnection connection= Connection.getConnection(filename);
            OutputStream outputStream=connection.getOutputStream();
            PrintWriter printWriter=new PrintWriter(outputStream);
            printWriter.write(data);
            printWriter.flush();
            printWriter.close();
            InputStream inputStream=connection.getInputStream();
            InputStreamReader ins=new InputStreamReader(inputStream);
            BufferedReader bufferedReader=new BufferedReader(ins);
            while((line=bufferedReader.readLine())!= null)
            {
                stringBuilder.append(line).append("\n");
            }
            connection.disconnect();
        }

        catch (Exception e)
        {
            Helper.Log("Data",e.toString());
        }

        return stringBuilder.toString();
    }

    public String updateData(String filename,String data)
    {
        String line="";
        StringBuilder stringBuilder=new StringBuilder();

        try {
            HttpURLConnection connection= Connection.getConnection(filename);
            OutputStream outputStream=connection.getOutputStream();
            PrintWriter printWriter=new PrintWriter(outputStream);
            printWriter.write(data);
            printWriter.flush();
            printWriter.close();
            InputStream inputStream=connection.getInputStream();
            InputStreamReader ins=new InputStreamReader(inputStream);
            BufferedReader bufferedReader=new BufferedReader(ins);
            while((line=bufferedReader.readLine())!= null)
            {
                stringBuilder.append(line).append("/n");
            }
        }

        catch (Exception e)
        {
            Helper.Log("Data",e.toString());
        }

        return stringBuilder.toString();
    }

    public String deleteData(String filename,String data)
    {
        String line="";
        StringBuilder stringBuilder=new StringBuilder();

        try {
            HttpURLConnection connection= Connection.getConnection(filename);
            OutputStream outputStream=connection.getOutputStream();
            PrintWriter printWriter=new PrintWriter(outputStream);
            printWriter.write(data);
            printWriter.flush();
            printWriter.close();
            InputStream inputStream=connection.getInputStream();
            InputStreamReader ins=new InputStreamReader(inputStream);
            BufferedReader bufferedReader=new BufferedReader(ins);
            while((line=bufferedReader.readLine())!= null)
            {
                stringBuilder.append(line).append("/n");
            }
        }

        catch (Exception e)
        {
            Helper.Log("Data",e.toString());
        }

        return stringBuilder.toString();
    }

    public String select(String filename,String data)
    {
        String line="";
        StringBuilder stringBuilder=new StringBuilder();

        try {
            HttpURLConnection connection= Connection.getConnection(filename);
            OutputStream outputStream=connection.getOutputStream();
            PrintWriter printWriter=new PrintWriter(outputStream);
            printWriter.write(data);
            printWriter.flush();
            printWriter.close();
            InputStream inputStream=connection.getInputStream();
            InputStreamReader ins=new InputStreamReader(inputStream);
            BufferedReader bufferedReader=new BufferedReader(ins);
            while((line=bufferedReader.readLine())!= null)
            {
                stringBuilder.append(line).append("/n");
            }
        }

        catch (Exception e)
        {
            Helper.Log("Data",e.toString());
        }

        return stringBuilder.toString();
    }

    public JSONArray selectAll(String filename)
    {
        JSONArray jsonArray=null;
        String line="";
        StringBuilder stringBuilder=new StringBuilder();

        try {
            HttpURLConnection connection= Connection.getConnection(filename);
            InputStream inputStream=connection.getInputStream();
            InputStreamReader ins=new InputStreamReader(inputStream);
            BufferedReader bufferedReader=new BufferedReader(ins);
            while((line=bufferedReader.readLine())!= null)
            {
                stringBuilder.append(line).append("/n");
            }
            jsonArray =new JSONArray(stringBuilder.toString());
        }

        catch (Exception e)
        {
            Helper.Log("Data",e.toString());
        }

        return jsonArray;
    }
}
