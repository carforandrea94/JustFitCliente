package com.example.andreacarfora.justfit_cliente.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.andreacarfora.justfit_cliente.Activities.GiornoAllenamentoActivity;
import com.example.andreacarfora.justfit_cliente.Classes.GiornoAllenameto;
import com.example.andreacarfora.justfit_cliente.R;

import java.util.List;

public class AdapterGiornoAllenamento extends ArrayAdapter<GiornoAllenameto>{

    private int resource;
    private LayoutInflater inflater;
    private Context mContext;

    public AdapterGiornoAllenamento(Context context, int resourceId, List<GiornoAllenameto> objects) {
        super(context, resourceId, objects);
        this.mContext = context;
        resource = resourceId;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(final int position, View v, ViewGroup parent) {
        if (v == null) {
            Log.d("DEBUG","Inflating view");
            v = inflater.inflate(resource, null);
        }

        final GiornoAllenameto g = getItem(position);
        CardView cardView = v.findViewById(R.id.card_day);
        TextView title = v.findViewById(R.id.idGiorno);
        title.setText("Giorno "+g.getGiorno());


        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,GiornoAllenamentoActivity.class);
                intent.putExtra("NUM_GIORNO",g.getGiorno());
                mContext.startActivity(intent);
            }
        });


        return v;
    }
}