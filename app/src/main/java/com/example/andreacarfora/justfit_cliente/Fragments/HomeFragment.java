package com.example.andreacarfora.justfit_cliente.Fragments;


import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.andreacarfora.justfit_cliente.R;


public class HomeFragment extends Fragment  {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        View v = bindUiFragment(inflater,container);

        return v;
    }


    private View bindUiFragment(LayoutInflater inflater, ViewGroup container) {
        View v =  inflater.inflate(R.layout.fragment_home, container, false);
        return v;
    }


}
