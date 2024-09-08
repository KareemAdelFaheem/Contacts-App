package com.example.contacts;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SplashScreen extends AppCompatActivity {
    private static final String FIRST_TIME_KEY="isFirstTime";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash_screen);
        SharedPreferences sp= PreferenceManager.getDefaultSharedPreferences(this);



        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent;
                if(sp.getString(FIRST_TIME_KEY,"")==""){
                   intent=new Intent(SplashScreen.this, onBoarding.class);
                }else{
                     intent=new Intent(SplashScreen.this, MainActivity.class);
                }

                startActivity(intent);
                finish();
            }
        },3000);

    }
}