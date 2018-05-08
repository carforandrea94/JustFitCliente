package com.example.andreacarfora.justfit_cliente.Adapters;

import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;

import com.example.andreacarfora.justfit_cliente.Classes.Esercizio;
import com.example.andreacarfora.justfit_cliente.R;

public class AdapterEsercizi extends ArrayAdapter<Esercizio> {
    private int resource;
    private LayoutInflater inflater;
    private int lastPosition = -1;

    public AdapterEsercizi(Context context, int resourceId, List<Esercizio> objects) {
        super(context, resourceId, objects);
        resource = resourceId;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View v, ViewGroup parent) {
        if (v == null) {
            Log.d("DEBUG","Inflating view");
            v = inflater.inflate(resource, null);
        }

        Esercizio c = getItem(position);

        Animation animation = AnimationUtils.loadAnimation(getContext(), (position > lastPosition) ? R.anim.up_to_down : R.anim.down_from_up);
        v.startAnimation(animation);
        lastPosition = position;

        Log.d("DEBUG","contact c="+c);


        return v;
    }


}

