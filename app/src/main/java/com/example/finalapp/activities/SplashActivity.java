package com.example.finalapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.finalapp.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }

    public void Login_Splash(View v)
    {
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        finish();
    }

    public void Register_Splash(View v)
    {
        startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
    }
}