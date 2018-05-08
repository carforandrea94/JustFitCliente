package com.example.andreacarfora.justfit_cliente.CustomViews;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.example.andreacarfora.justfit_cliente.R;
import com.shawnlin.numberpicker.NumberPicker;


public class CustomDialogSetRecupero extends Dialog {
    public Activity activity;
    public Button btnYes, btnNo;
    private NumberPicker pickerMinuti, pickerSecondi;
    private int resourceId, resourceOK, resourceCanc, min, sec;

    public CustomDialogSetRecupero(Activity activity, int resourceId, int resourceOK, int resourceCanc, int min, int sec) {
        super(activity);
        this.activity = activity;
        this.resourceCanc = resourceCanc;
        this.resourceId = resourceId;
        this.resourceOK = resourceOK;
        this.min = min;
        this.sec = sec;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(resourceId);
        btnYes = findViewById(resourceOK);
        btnNo = findViewById(resourceCanc);
        pickerMinuti = findViewById(R.id.picker_minuti);
        pickerSecondi = findViewById(R.id.picker_secondi);
        pickerMinuti.setValue(min);
        pickerSecondi.setValue(sec);
    }

    public void setOnOKClickListner(View.OnClickListener listner) {
        btnYes.setOnClickListener(listner);
    }

    public void setOnCancClickListener(View.OnClickListener listener) {
        btnNo.setOnClickListener(listener);
    }


    public int getMinuti() {
        return pickerMinuti.getValue();
    }

    public int getSecondi() {
        return pickerSecondi.getValue();
    }

}