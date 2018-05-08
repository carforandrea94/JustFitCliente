package com.example.andreacarfora.justfit_cliente.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.andreacarfora.justfit_cliente.R;

public class DetailsExActivity extends AppCompatActivity {


    private ImageView close, check;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_ex);
        bindUiViewActivity();
        checkCloseListener();
    }


    private void checkCloseListener(){
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                finish();
            }
        });
    }

    private void bindUiViewActivity() {
        close = findViewById(R.id.closeDetailEx);
    }
}
