package com.example.andreacarfora.justfit_cliente.Fragments;


import android.graphics.Point;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.example.andreacarfora.justfit_cliente.R;



public class DiarioFragment extends Fragment {


    private ProgressBar progressBarKal;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {



        View v =  bindUiFragment(inflater,container);

        return v;
    }


    private View bindUiFragment(LayoutInflater inflater, ViewGroup container) {
        View v = inflater.inflate(R.layout.fragment_diario, container, false);
        progressBarKal = v.findViewById(R.id.progressBar_kal);

        Display display = getActivity().getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;

        return v;
    }


}
