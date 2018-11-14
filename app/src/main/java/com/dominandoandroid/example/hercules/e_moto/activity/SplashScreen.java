package com.dominandoandroid.example.hercules.e_moto.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.dominandoandroid.example.hercules.e_moto.R;

public class SplashScreen extends AppCompatActivity implements Runnable{
    private final static int DELAY = 0; //2000
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_splash_screen);
        Handler h = new Handler();
        h.postDelayed(this, DELAY);

    }

    @Override
    public void run() {

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
