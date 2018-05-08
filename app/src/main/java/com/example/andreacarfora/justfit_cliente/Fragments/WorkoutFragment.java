package com.example.andreacarfora.justfit_cliente.Fragments;


import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.andreacarfora.justfit_cliente.Adapters.AdapterGiornoAllenamento;
import com.example.andreacarfora.justfit_cliente.Classes.Esercizio;
import com.example.andreacarfora.justfit_cliente.Classes.GiornoAllenameto;
import com.example.andreacarfora.justfit_cliente.Classes.Scheda;
import com.example.andreacarfora.justfit_cliente.CustomViews.WrappingGridView;
import com.example.andreacarfora.justfit_cliente.R;
import com.githang.stepview.StepView;
import java.util.ArrayList;
import java.util.List;


public class WorkoutFragment extends Fragment {


    private TextView dataInzio,dataFine,settimana,consiglio,textView;
    private Scheda myScheda;
    private int countSettimana;
    private StepView mStepView;
    private WrappingGridView gridView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = bindUiFragment(inflater,container);

        recuperoDati();

        AdapterGiornoAllenamento adapterGiornoAllenamento = new AdapterGiornoAllenamento(getContext(),R.layout.list_element_allenameneto, myScheda.getGiorniAllenamento());
        gridView.setAdapter(adapterGiornoAllenamento);

        return v;
    }


   private void recuperoDati(){

        countSettimana = 1;
        ArrayList<GiornoAllenameto> allenamenti = new ArrayList<>();
        allenamenti.add(new GiornoAllenameto("", 1, "Dorsali-Deltoidi", new ArrayList<Esercizio>()));
        allenamenti.add(new GiornoAllenameto("", 2, "Petto-Bicipiti", new ArrayList<Esercizio>()));
        allenamenti.add(new GiornoAllenameto("", 3, "Femorali-Quadricipiti-Tricipiti", new ArrayList<Esercizio>()));
        allenamenti.add(new GiornoAllenameto("", 4, "Richiamo Dorsali-Deltoidi", new ArrayList<Esercizio>()));


        myScheda = new Scheda();
        myScheda.setDataFine("10/03/2018");
        myScheda.setDataInizio("10/02/2018");
        myScheda.setGiorniAllenamento(allenamenti);
        myScheda.setNumSettimane(4);

        dataInzio.setText(myScheda.getDataInizio());
        dataFine.setText(myScheda.getDataFine());
        settimana.setText(countSettimana+"/"+myScheda.getNumSettimane());


        List<String> steps = new ArrayList<>();
        for(int i=1; i<=myScheda.getGiorniAllenamento().size();i++)
            steps.add("");
        mStepView.setSteps(steps);

    }



    private View bindUiFragment(LayoutInflater inflater, ViewGroup container) {
        View v =  inflater.inflate(R.layout.fragment_workout, container, false);

        mStepView = v.findViewById(R.id.step_view);
        gridView = v.findViewById(R.id.grid_days);
        dataInzio = v.findViewById(R.id.dataInizio);
        dataFine = v.findViewById(R.id.dataFine);
        settimana=v.findViewById(R.id.settimana);
        consiglio = v.findViewById(R.id.consiglio);


        return v;
    }
}
