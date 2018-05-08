package com.example.andreacarfora.justfit_cliente.CustomViews;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.example.andreacarfora.justfit_cliente.R;

public class CustomDialodNumberPicker extends Dialog{

    public Activity activity;
    public Button btnYes, btnNo;
    private int resourceId, resourceOK,resourceCanc;
    private int numOfPicker;
    private com.shawnlin.numberpicker.NumberPicker numberPicker;

    public CustomDialodNumberPicker(Activity activity, int resourceId, int resourceOK, int resourceCanc, int numOfPicker) {
        super(activity);
        this.activity = activity;
        this.resourceCanc = resourceCanc;
        this.resourceId = resourceId;
        this.resourceOK = resourceOK;
        this.numOfPicker = numOfPicker;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(resourceId);
        btnYes = findViewById(resourceOK);
        btnNo = findViewById(resourceCanc);
        numberPicker = findViewById(R.id.number_picker);
        numberPicker.setValue(numOfPicker);
    }

    public void setOnOKClickListner(View.OnClickListener listner){
        btnYes.setOnClickListener(listner);
    }

    public void setOnCancClickListener(View.OnClickListener listener){
        btnNo.setOnClickListener(listener);
    }


    public int getNumSelected(){
        return numberPicker.getValue();
    }

}
