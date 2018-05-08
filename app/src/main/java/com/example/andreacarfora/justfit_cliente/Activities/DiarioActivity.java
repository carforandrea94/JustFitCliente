package com.example.andreacarfora.justfit_cliente.Activities;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.andreacarfora.justfit_cliente.Fragments.DiarioFragment;
import com.example.andreacarfora.justfit_cliente.R;

public class DiarioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diario);

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.diario_container);
        if (fragment == null){
            fragment = new DiarioFragment();
            fragmentManager.beginTransaction().add(R.id.diario_container, fragment).commit();
        }
    }
}
