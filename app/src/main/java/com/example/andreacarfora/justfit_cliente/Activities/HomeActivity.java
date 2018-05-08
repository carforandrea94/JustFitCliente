package com.example.andreacarfora.justfit_cliente.Activities;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.andreacarfora.justfit_cliente.Fragments.HomeFragment;
import com.example.andreacarfora.justfit_cliente.Fragments.ProfiloFragment;
import com.example.andreacarfora.justfit_cliente.R;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.home_container);
        if (fragment == null){
            fragment = new HomeFragment();
            fragmentManager.beginTransaction().add(R.id.home_container, fragment).commit();
        }
    }
}
