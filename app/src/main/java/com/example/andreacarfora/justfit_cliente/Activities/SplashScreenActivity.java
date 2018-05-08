package com.example.andreacarfora.justfit_cliente.Activities;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.andreacarfora.justfit_cliente.R;

public class SplashScreenActivity extends AppCompatActivity {
    int timeout = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        TextView textView = (TextView) findViewById(R.id.textLogoSplash);
        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/font.ttf");
        textView.setTypeface(font);

        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                finish();
            }
        }, timeout);
    }
}