package com.aditya.projectdemo.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.aditya.projectdemo.Helper.Connection;
import com.aditya.projectdemo.Helper.Helper;
import com.aditya.projectdemo.R;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class UploadImage extends AppCompatActivity implements View.OnClickListener
{
    ProgressDialog loading;
    private Bitmap bitmap;
    private Uri filePath;
    String option = "";
    ImageView mimageView;
    String email="";
    private int PICK_IMAGE_REQUEST = 1;
    private static final int CAMERA_REQUEST = 1888;
    Button btncamra, btngallary, btnupload;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_image);
        bind();
        btncamra.setOnClickListener(this);
        btngallary.setOnClickListener(this);
        btnupload.setOnClickListener(this);
    }

    private void showFileChooser()
    {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }
    public void takeImageFromCamera(View view)
    {
        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, CAMERA_REQUEST);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if(option.equals("CAMERA"))
        {
            Toast.makeText(getApplicationContext()," hhhhh"+bitmap,Toast.LENGTH_LONG).show();

            if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK)
            {
                bitmap = (Bitmap) data.getExtras().get("data");
                mimageView.setImageBitmap(getRoundedShape(bitmap,200));

            }
        }
        if (option.equals("GALLERY"))
        {
            super.onActivityResult(requestCode, resultCode, data);

            if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {

                filePath = data.getData();
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);

                    mimageView.setImageBitmap(getRoundedShape(bitmap,200));

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    public String getStringImage(Bitmap bmp)
    {
      //  Bitmap bitmap=((BitmapDrawable) mimageView.getDrawable()).getBitmap();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }
    public void uploadImage()
    {
        String uploadImage = getStringImage(bitmap);
    Toast.makeText(getApplicationContext(),uploadImage,Toast.LENGTH_LONG).show();
        Bundle bundle=getIntent().getExtras();
     email=bundle.getString("email");
        Log.d("Camera",""+email.toString());
        try
        {
            HttpURLConnection connection=Connection.getConnection("Upload.php");
            //URL url=new URL("http://192.168.43.190/Login/Upload.php");
            //HttpURLConnection connection=(HttpURLConnection)url.openConnection();
            //connection.setRequestMethod("POST");
            //connection.setDoOutput(true);
            //Toast.makeText(getApplicationContext()," "+"connection ",Toast.LENGTH_LONG).show();
            OutputStream outputStream=connection.getOutputStream();
            OutputStreamWriter outputStreamWriter=new OutputStreamWriter(outputStream,"UTF-8");
            BufferedWriter bufferedWriter=new BufferedWriter(outputStreamWriter);
            String data= URLEncoder.encode("Name","UTF-8")+"="+URLEncoder.encode(email,"UTF-8")+
                    "&"+URLEncoder.encode("image","UTF-8")+"="+URLEncoder.encode(uploadImage,"UTF-8");
            bufferedWriter.write(data);
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStreamWriter.close();
            outputStream.close();
            InputStream inputStream=connection.getInputStream();
            connection.disconnect();
            Toast.makeText(getApplicationContext()," "+"Image Upload",Toast.LENGTH_LONG).show();
        }
        catch (Exception e)
        {
            Toast.makeText(getApplicationContext()," "+e.toString(),Toast.LENGTH_LONG).show();
        }


    }

    @Override
    public void onClick(View v)
    {
        int id=v.getId();
        if(id==R.id.BtnCamara)
//            Toast.makeText(getApplicationContext(),"Permission ",Toast.LENGTH_LONG).show();
        {
            if (isReadStorageAllowed())

            {
                Toast.makeText(getApplicationContext()," Permission ",Toast.LENGTH_LONG).show();
                option="CAMERA";
                takeImageFromCamera(v);
              //  return;
            }

            requestStoragePermission();




        }
        if (id==R.id.btnGallary)
        {
            option="GALLERY";
            showFileChooser();
        }
        //option="camara";
        //takeImageFromCamera(v);

        if (id==R.id.btnGallary)
        {
            option="GALLERY";
            showFileChooser();
        }
        if(R.id.btnupload==id)
        {

            // updatedata(licno,phtoidno);
    uploadImage();
        }
    }
    private boolean isReadStorageAllowed()
    {

        int result = ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA);


        if (result == PackageManager.PERMISSION_GRANTED)
            return true;


        return false;
    }


    private void requestStoragePermission()
    {

        if (ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.CAMERA))
        {

        }


        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, CAMERA_REQUEST);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {


        if (requestCode == CAMERA_REQUEST) {


            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {


                Toast.makeText(this, "Permission granted now you can read the storage", Toast.LENGTH_LONG).show();
            } else {

                Toast.makeText(this, "Oops you just denied the permission", Toast.LENGTH_LONG).show();
            }
        }
    }



    public void bind()
    {
        mimageView=(ImageView)findViewById(R.id.imgview);
        btncamra=(Button)findViewById(R.id.BtnCamara);
        btngallary=(Button)findViewById(R.id.btnGallary);
        btnupload=(Button)findViewById(R.id.btnupload);
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
