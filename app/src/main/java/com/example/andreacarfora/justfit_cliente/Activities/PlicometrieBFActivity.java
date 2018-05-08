package com.example.andreacarfora.justfit_cliente.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.andreacarfora.justfit_cliente.R;

public class PlicometrieBFActivity extends AppCompatActivity {


    private Button  calcola;
    private EditText edit_M1, edit_M2,edit_M3;
    private TextView textPercent;
    private ImageView imgGender, imgM1,imgM2,imgM3,close, check;
    private String SESSO="Maschio";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plicometrie_bf);

        bindUiViewActivity();
        checkCloseListener();


        calcola.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcolaBf();
            }
        });

    }


    private void checkCloseListener() {
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                finish();
            }
        });

        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                finish();
            }
        });
    }


   private void bindUiViewActivity() {
        close = findViewById(R.id.closepliche);
        check = findViewById(R.id.chekpliche);
        edit_M1 = findViewById(R.id.editM1);
        edit_M2 = findViewById(R.id.editM2);
        edit_M3 = findViewById(R.id.editM3);
        textPercent = findViewById(R.id.percentualeBF);
        imgGender = findViewById(R.id.imageGender);
        calcola = findViewById(R.id.calcolaBf);
    }




   private void calcolaBf(){

        //equazione di Pollock per il calcolo della percentuale di massa grassa
        //Questa equazione di Pollock permette di calcolare la percentuale di grasso corporeo di un uomo
        //tramite la misura dello spessore di tre pliche: pettorale, addominale e quadricipitale.
        //Tratto da http://www.my-personaltrainer.it/pollock_uomini.htm


        double m1 = Double.parseDouble(edit_M1.getText().toString());
        double m2 = Double.parseDouble(edit_M2.getText().toString());
        double m3 = Double.parseDouble(edit_M3.getText().toString());

        double s = m1 + m2 + m3;
        int eta = 24;
        String sesso = SESSO;
        double d = 0;

        if(sesso.equals("Maschio")) {
            d = (1.10938-(0.0008267*s)+(0.0000016*(s*s))-(0.0002574*eta));
        }
        if(sesso.equals("Femmina")){
            d = 1.0994921-(0.0009929*s) + (0.0000023*(s*s))-(0.0001392*eta);
        }

       double percent = 495/d-450;
       textPercent.setText(String.format("%.2f",percent)+" %");

   }
}