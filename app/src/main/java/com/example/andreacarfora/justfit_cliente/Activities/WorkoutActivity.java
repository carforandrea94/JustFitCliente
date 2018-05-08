package com.example.andreacarfora.justfit_cliente.Activities;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.andreacarfora.justfit_cliente.Fragments.WorkoutFragment;
import com.example.andreacarfora.justfit_cliente.R;

public class WorkoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.workout_container);
        if (fragment == null){
            fragment = new WorkoutFragment();
            fragmentManager.beginTransaction().add(R.id.workout_container, fragment).commit();
        }
    }
}
