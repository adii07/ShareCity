package com.aditya.projectdemo.Adaptor;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Rect;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.aditya.projectdemo.R;

import java.io.InputStream;
import java.net.URL;

public class HistoryAdaptor extends BaseAdapter
{
    String[] source;
    String[] destination;
    String[] date;
    int[] fare;
    String[] imageurl;
    LayoutInflater layoutInflater=null;
    public HistoryAdaptor(Context context, String[] source, String[] destination, String[] date, int[] fare,String[] imageurl)
    {
        this.source=source;
        this.destination=destination;
        this.date=date;
        this.fare=fare;
        this.imageurl=imageurl;
        layoutInflater=LayoutInflater.from(context);
    }
    @Override
    public int getCount()
    {
        return source.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position)
    {
        return 0;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view=layoutInflater.inflate(R.layout.history_adaptor,null,false);
        TextView sou=(TextView)view.findViewById(R.id.textView7);
        TextView desti=(TextView)view.findViewById(R.id.textView9);
        TextView Mdate=(TextView)view.findViewById(R.id.textView10);
        TextView Mfare=(TextView)view.findViewById(R.id.textView12);
        ImageView dp=(ImageView)view.findViewById(R.id.imageView7);
        sou.setText("Start destination: "+source[position]);
        desti.setText("Final destination: "+destination[position]);
        Mdate.setText(date[position]);
        Mfare.setText("₹"+fare[position]+"");
        try
        {
            Bitmap bitmap = BitmapFactory.decodeStream((InputStream) new URL(imageurl[position]).getContent());
            dp.setImageBitmap(getRoundedShape(bitmap,300));
        }
        catch (Exception e)
        {
            Log.d("data",e.toString());
        }

        return view;
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
