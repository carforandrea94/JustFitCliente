package com.example.andreacarfora.justfit_cliente.Adapters;

import java.util.Calendar;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.andreacarfora.justfit_cliente.Classes.Misura;
import com.example.andreacarfora.justfit_cliente.R;

public class AdapterListPeso extends ArrayAdapter<Misura> {
    private int resource;
    private LayoutInflater inflater;
    private TextView textViewpeso, textViewData,textViewDif;
    private ImageView arrow;
    private Misura after,before;


    public AdapterListPeso(Context context, int resourceId, List<Misura> objects) {
        super(context, resourceId, objects);
        resource = resourceId;
        inflater = LayoutInflater.from(context);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public View getView(int position, View v, ViewGroup parent) {
        if (v == null) {
            Log.d("DEBUG","Inflating view");
            v = inflater.inflate(resource, null);
        }

        textViewData = v.findViewById(R.id.dataOfPeso);
        textViewpeso = v.findViewById(R.id.peso);
        arrow = v.findViewById(R.id.arrowPeso);
        textViewDif = v.findViewById(R.id.differenzaPeso);

        if(position>0) {
            after = getItem(position - 1);
            before = getItem(position);

            double diff = (-after.getMisura() + before.getMisura());

            if (diff<0) {
                textViewDif.setText("" + String.format("%.2f",diff*(-1)).replace(",","."));
                textViewDif.setTextColor(R.color.check_color);
                arrow.setBackgroundResource(R.drawable.arrow_down_bold);

            } else if (diff>0){
                textViewDif.setText("" + String.format("%.2f",diff).replace(",","."));
                textViewDif.setTextColor(R.color.error_color);
                arrow.setBackgroundResource(R.drawable.arrow_up_bold);
            }
            else{
                textViewDif.setText("");
                arrow.setBackgroundResource(R.drawable.equal);
                arrow.setColorFilter(ContextCompat.getColor(getContext(), R.color.equal));
            }
        }
        else{
            before = getItem(position);
            textViewDif.setTextColor(Color.DKGRAY);
            textViewDif.setText("");
            arrow.setBackgroundResource(R.drawable.equal);
            arrow.setColorFilter(ContextCompat.getColor(getContext(), R.color.equal));
        }




        int giorno = before.getDataMisura().get(Calendar.DAY_OF_MONTH);
        int mese = before.getDataMisura().get(Calendar.MONTH)+1;
        int anno = before.getDataMisura().get(Calendar.YEAR);


        textViewData.setText("Data: "+String.format("%02d",giorno)+"/"+String.format("%02d",mese)+"/"+anno);
        textViewpeso.setText(+before.getMisura()+" kg");

        return v;
    }
}

