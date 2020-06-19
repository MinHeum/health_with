package com.example.healthwith;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.healthwith.db.AppDatabase;

public class SplashActivity extends AppCompatActivity {

    private final int SPLASH_DISPLAY_TIME = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getApplication(), MainActivity.class));
                SplashActivity.this.finish();
            }
        }, SPLASH_DISPLAY_TIME);
    }

    @Override
    public void onBackPressed() {

    }
}
