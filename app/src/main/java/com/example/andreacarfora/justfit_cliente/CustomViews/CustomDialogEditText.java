package com.example.andreacarfora.justfit_cliente.CustomViews;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.example.andreacarfora.justfit_cliente.Classes.Misura;
import com.example.andreacarfora.justfit_cliente.R;

import java.util.GregorianCalendar;

public class CustomDialogEditText extends Dialog{

    public Activity activity;
    public Button btnYes, btnNo;
    private int resourceId, resourceOK,resourceCanc;
    private double peso;
    private EditText editTextPeso;

    public CustomDialogEditText(Activity activity, int resourceId, int resourceOK, int resourceCanc, double peso) {
        super(activity);
        this.activity = activity;
        this.resourceCanc = resourceCanc;
        this.resourceId = resourceId;
        this.resourceOK = resourceOK;
        this.peso = peso;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(resourceId);
        btnYes = findViewById(resourceOK);
        btnNo = findViewById(resourceCanc);
        editTextPeso = findViewById(R.id.editPeso);
        editTextPeso.setText(String.format("%.2f",peso).replace(",","."));
    }

    public void setOnOKClickListner(View.OnClickListener listner){
        btnYes.setOnClickListener(listner);
    }

    public void setOnCancClickListener(View.OnClickListener listener){
        btnNo.setOnClickListener(listener);
    }


    public Misura getMisuraPeso(){
        double d = Double.parseDouble(editTextPeso.getText().toString().replace(",","."));
        Misura m = new Misura(d);
        GregorianCalendar cal = new GregorianCalendar();
        m.setDataMisura(cal);
        return m;
    }

}
