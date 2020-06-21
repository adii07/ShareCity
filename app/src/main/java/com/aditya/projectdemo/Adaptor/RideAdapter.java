package com.aditya.projectdemo.Adaptor;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SearchRecentSuggestionsProvider;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Rect;
import android.media.session.PlaybackState;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.aditya.projectdemo.Activity.MainActivity;
import com.aditya.projectdemo.Activity.RideDetails;
import com.aditya.projectdemo.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class RideAdapter extends RecyclerView.Adapter<RideAdapter.MYviewHolder>
{

    String[] date;
    int[] cost;
    int[]seatNumber;
    String[] time;
    String[] endDes;
    String[] startDes;
    String [] idOwner;
    String[] imageURL;
    String url1="";


    public RideAdapter(String[] date, int[] cost,int[]seatNumber, String[] time,String[] startDes,String[] endDes,String [] idOwner, String[] imageURL)
    {
        this.date=date;
        this.cost=cost;
        this.seatNumber=seatNumber;
        this.time=time;
        this.startDes=startDes;
        this.endDes=endDes;
        this.idOwner=idOwner;
        this.imageURL=imageURL;
    }

    @NonNull
    @Override
    public MYviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.ride_content,parent,false);
        final MYviewHolder myViewHolder=new MYviewHolder(view);
        myViewHolder.book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             Intent intent=new Intent( myViewHolder.itemView.getContext(), RideDetails.class );
                String mydate=myViewHolder.txtdate.getText().toString();
                String mycost=myViewHolder.txtcost.getText().toString();
                String myseatnumber=myViewHolder.txtseatnum.getText().toString();
                String mytime=myViewHolder.txtTime.getText().toString();
                String myendDes=myViewHolder.txtend.getText().toString();
                String mystartDes=myViewHolder.txtstart.getText().toString();
                String myidOwner=myViewHolder.txtownerid.getText().toString();
                //String myimageURL=imageURL[];
                intent.putExtra("Date",mydate);
                intent.putExtra("cost",mycost);
                intent.putExtra("seat",myseatnumber);
                intent.putExtra("time",mytime);
                intent.putExtra("end",myendDes);
                intent.putExtra("start",mystartDes);
                intent.putExtra("id",myidOwner);
                intent.putExtra("url",url1);
                myViewHolder.itemView.getContext().startActivity(intent);

            }
        });

        return myViewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull final MYviewHolder holder, int position) {



        holder.txtdate.setText(date[position]);
        holder.txtcost.setText(cost[position]+"");
        holder.txtseatnum.setText(seatNumber[position]+"");
        holder.txtTime.setText(time[position]);
        holder.txtend.setText(endDes[position]);
        holder.txtstart.setText(startDes[position]);
        holder.txtownerid.setText(idOwner[position]);
        //holder.txtstart.setText(startDes[position]);
        url1=imageURL[position];
        try {
            Bitmap bitmap = BitmapFactory.decodeStream((InputStream) new URL(imageURL[position]).getContent());
            holder.img.setImageBitmap(getRoundedShape(bitmap,20));
        } catch (Exception e) {
            Log.d("data",e.toString());
        }

    }



    @Override
    public int getItemCount() {
        return idOwner.length;
    }



   public class MYviewHolder extends RecyclerView.ViewHolder {
         ImageView img;
        TextView txtdate,txtcost,txtTime,txtseatnum,txtstart,txtend,txtownerid,txt;
       Button book;


        public MYviewHolder(@NonNull View view) {
            super(view);
            txtdate=(TextView)itemView.findViewById(R.id.date);
            txtcost=(TextView)itemView.findViewById(R.id.fare);
            txtseatnum=(TextView)itemView.findViewById(R.id.seatnum);
            txtend=(TextView)itemView.findViewById(R.id.end);
            txtstart=(TextView)itemView.findViewById(R.id.start);
            txtTime=(TextView)itemView.findViewById(R.id.time);
            txtownerid=(TextView)itemView.findViewById(R.id.owner);
            img=(ImageView)itemView.findViewById(R.id.imageView1);
            book=(Button)itemView.findViewById(R.id.Book);


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

