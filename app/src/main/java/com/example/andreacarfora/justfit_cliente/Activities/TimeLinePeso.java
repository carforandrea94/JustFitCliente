package com.example.andreacarfora.justfit_cliente.Activities;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.andreacarfora.justfit_cliente.Adapters.AdapterListPeso;
import com.example.andreacarfora.justfit_cliente.Classes.Misura;

import com.example.andreacarfora.justfit_cliente.CustomViews.CustomDialogEditText;

import com.example.andreacarfora.justfit_cliente.R;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.PointsGraphSeries;

import java.util.ArrayList;

public class TimeLinePeso extends AppCompatActivity {


    private ImageView check, close;
    private GraphView graphPeso;
    private ImageView add_peso;
    private ListView listPeso;
    private ArrayList<Misura> elements;
    private double PESO;
    private CustomDialogEditText customDialog;
    private AdapterListPeso adapterListPeso;
    private Misura selectedM;
    private AlertDialog alertDialog;
    private LineGraphSeries<DataPoint> series;
    private PointsGraphSeries<DataPoint> series2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_line_peso);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {

            } else {
                PESO= extras.getDouble("PESO");
            }
        }


        bindUiActivity();
        caricaDati();
        chackcloseListener();


        adapterListPeso = new AdapterListPeso(this,R.layout.item_list_peso,elements);
        listPeso.setAdapter(adapterListPeso);

        listPeso.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                selectedM = elements.get(position);
                createAlert();
                return false;
            }
        });

        listenerAddPeso();
        graphManager();
    }




    private void createAlert(){
        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        alertDialogBuilder.setTitle("Elimina Misura");

        alertDialogBuilder.setMessage("Sicuro di voler eliminare questa misura?");
        alertDialogBuilder.setCancelable(false);

        alertDialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface arg0, int arg1) {
               elements.remove(selectedM);
               selectedM = null;
               adapterListPeso.notifyDataSetChanged();
               Toast.makeText(getApplicationContext(), "Misura eliminata", Toast.LENGTH_SHORT).show();

               alertDialog.dismiss();
            }
        });

        alertDialogBuilder.setNegativeButton("Annulla", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
               alertDialog.dismiss();
            }
        });

        alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }

    private void listenerAddPeso(){
        add_peso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createDialog();
            }
        });
    }


    private void createDialog(){
        customDialog = new CustomDialogEditText(this, R.layout.layout_popup_peso, R.id.okPeso, R.id.noPeso, PESO);
        customDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        customDialog.show();

        customDialog.setOnOKClickListner(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Misura m = customDialog.getMisuraPeso();
                elements.add(m);
                PESO = m.getMisura();
                adapterListPeso.notifyDataSetChanged();
                series.appendData(new DataPoint(elements.size(),m.getMisura()),true,1000);
                series2.appendData(new DataPoint(elements.size(),m.getMisura()),true,1000);
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



    private void bindUiActivity(){
        check = findViewById(R.id.checkStoriesPeso);
        close = findViewById(R.id.closeStoriesPeso);
        graphPeso = findViewById(R.id.graphPeso);
        add_peso = findViewById(R.id.setNewPeso);
        listPeso = findViewById(R.id.listOfPeso);
        graphPeso.getViewport().setMaxX(250);
    }

    private void caricaDati(){
        elements = new ArrayList<>();
    }




    private void chackcloseListener(){
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //update
                onBackPressed();
                finish();
            }
        });

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                finish();
            }
        });

    }


    private void graphManager(){
        series = new LineGraphSeries<DataPoint>();
        series2 = new PointsGraphSeries<DataPoint>();



        for(int i = 0; i<elements.size();i++){
            Misura m = elements.get(i);
            series.appendData(new DataPoint(m.getDataMisura().getTime(),m.getMisura()),true,1000);
            series2.appendData(new DataPoint(m.getDataMisura().getTime(),m.getMisura()),true,1000);
        }

        series2.setShape(PointsGraphSeries.Shape.POINT);
        series2.setColor(R.color.colorPrimaryDark);
        graphPeso.addSeries(series);
        graphPeso.addSeries(series2);
        series.setThickness(20);
        graphPeso.getGridLabelRenderer().setLabelFormatter(new DateAsXAxisLabelFormatter(graphPeso.getContext()));
        graphPeso.getGridLabelRenderer().setTextSize(20);
        graphPeso.getGridLabelRenderer().setHorizontalLabelsVisible(false);
        graphPeso.getGridLabelRenderer().setVerticalLabelsColor(R.color.colorPrimaryDark);
        graphPeso.getGridLabelRenderer().setLabelsSpace(2);
        graphPeso.getGridLabelRenderer().setVerticalAxisTitleTextSize(12);
        graphPeso.getViewport().setXAxisBoundsManual(true);
        graphPeso.getViewport().setMinY(40);
        graphPeso.getViewport().setMaxY(250);
        graphPeso.getGridLabelRenderer().setHumanRounding(false);

    }


}
