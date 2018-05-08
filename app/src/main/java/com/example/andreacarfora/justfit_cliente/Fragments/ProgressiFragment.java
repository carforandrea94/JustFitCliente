package com.example.andreacarfora.justfit_cliente.Fragments;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.andreacarfora.justfit_cliente.Activities.PlicometrieBFActivity;
import com.example.andreacarfora.justfit_cliente.Activities.TimeLinePeso;
import com.example.andreacarfora.justfit_cliente.Classes.Misura;
import com.example.andreacarfora.justfit_cliente.Classes.Progressi;
import com.example.andreacarfora.justfit_cliente.Classes.TipoMisura;
import com.example.andreacarfora.justfit_cliente.CustomViews.CustomDialodNumberPicker;
import com.example.andreacarfora.justfit_cliente.R;
import com.github.anastr.speedviewlib.SpeedView;
import com.jjoe64.graphview.GraphView;

import java.util.ArrayList;


public class ProgressiFragment extends Fragment{


    private SpeedView speedView;
    private ImageButton dotsAlt, dotsPeso, dotsBf;
    private GraphView graphView;
    private TextView textAltezza, textStato, textPercet;
    private CustomDialodNumberPicker customDialog;
    private Progressi progressi;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        View v = bindUiFragment(inflater,container);
        recuperoDati();
        dotsListener();
        return v;
    }



    private View bindUiFragment(LayoutInflater inflater, ViewGroup container) {
        View v = inflater.inflate(R.layout.fragment_progressi, container, false);

        dotsAlt = v.findViewById(R.id.dotsSetAltezza);
        dotsPeso = v.findViewById(R.id.dotsSetPeso);
        dotsBf = v.findViewById(R.id.dotsSetBf);
        graphView = v.findViewById(R.id.graphBf);
        textAltezza = v.findViewById(R.id.id_altezza);
        textPercet = v.findViewById(R.id.id_percentBf);
        textStato = v.findViewById(R.id.id_stato);
        speedView = v.findViewById(R.id.speedView);
        speedView.setOnSpeedChangeListener(null);
        speedView.setOnSectionChangeListener(null);
        speedView.setMaxSpeed(250);
        speedView.setMinSpeed(0);

        return v;
    }


    private void recuperoDati(){

        //PESO
        Misura misuraPeso = new Misura(93);
        TipoMisura peso = new TipoMisura("Peso",new ArrayList<Misura>());
        peso.getMisure().add(misuraPeso);

       //BodyFat
        Misura misuraBF = new Misura(93);
        TipoMisura bodyFat = new TipoMisura("Body Fat",new ArrayList<Misura>());
        bodyFat.getMisure().add(misuraBF);


        progressi = new Progressi("",193,peso,bodyFat,new ArrayList<TipoMisura>(),new ArrayList<TipoMisura>());

        textAltezza.setText(""+progressi.getAltezza());
        speedView.speedTo((float) progressi.getLastPeso().getMisura());
    }



    private void createDialog(){
        customDialog = new CustomDialodNumberPicker(getActivity(),R.layout.layput_set_altezza,R.id.okAltezza,R.id.noAltezza,progressi.getAltezza());
        customDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        customDialog.show();

        customDialog.setOnOKClickListner(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressi.setAltezza(customDialog.getNumSelected());
                textAltezza.setText(String.format("%d",progressi.getAltezza()));
                customDialog.dismiss();
            }
        });

        customDialog.setOnCancClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customDialog.dismiss();
            }
        });
    }



    private void dotsListener(){

        dotsAlt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createDialog();
            }
        });
        dotsPeso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), TimeLinePeso.class);
                intent.putExtra("PESO",progressi.getLastPeso().getMisura());
                startActivity(intent);
            }
        });

        dotsBf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PlicometrieBFActivity.class);
                startActivity(intent);
            }
        });

    }




}


