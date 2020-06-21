package com.aditya.projectdemo.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.Toast;

import com.aditya.projectdemo.R;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_splash);
        Thread thread = new Thread() {
            public void run()
            {
                try {
                    Thread.sleep(3000);

                    SharedPreferences sharedPreferences = getSharedPreferences("Login", MODE_PRIVATE);
                    boolean test = sharedPreferences.getBoolean("Status", false);
                    if (test) {
                        Intent intent = new Intent(getApplicationContext(), User.class);
                        startActivity(intent);
                    } else {
                        Intent intent = new Intent(getApplicationContext(), login.class);
                        startActivity(intent);
                    }
                }

                catch(Exception e)
                {
                    Log.d("ErrorSplash",e.toString());

                }
        }
            };
        thread.start();
    }
}
