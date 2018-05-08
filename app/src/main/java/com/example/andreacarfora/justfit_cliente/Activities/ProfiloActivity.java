package com.example.andreacarfora.justfit_cliente.Activities;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.andreacarfora.justfit_cliente.Fragments.ProfiloFragment;
import com.example.andreacarfora.justfit_cliente.R;

public class ProfiloActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profilo);

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.container_profilo);
        if (fragment == null){
            fragment = new ProfiloFragment();
            fragmentManager.beginTransaction().add(R.id.container_profilo, fragment).commit();
        }
    }
}
