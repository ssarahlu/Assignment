package com.example.assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

//this class displays the splash screen before starting the login activity
public class MainActivity extends AppCompatActivity {

    private static int SPLASH_SCREEN_TIME_OUT = 2500;

    /**
     * REFERENCE for Splash Screen threading
     * https://abhiandroid.com/programming/splashscreen
     */

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.splash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this,
                        LoginActivity.class);

                startActivity(intent);

                finish();
            }
        }, SPLASH_SCREEN_TIME_OUT);
    }


}
