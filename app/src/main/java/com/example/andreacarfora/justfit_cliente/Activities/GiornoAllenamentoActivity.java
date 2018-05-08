package com.example.andreacarfora.justfit_cliente.Activities;

import android.animation.ObjectAnimator;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.support.design.widget.Snackbar;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.example.andreacarfora.justfit_cliente.Adapters.AdapterEsercizi;
import com.example.andreacarfora.justfit_cliente.Classes.Esercizio;
import com.example.andreacarfora.justfit_cliente.Classes.GiornoAllenameto;
import com.example.andreacarfora.justfit_cliente.CustomViews.CustomDialogSetRecupero;
import com.example.andreacarfora.justfit_cliente.Fragments.DatePickerFragment;
import com.example.andreacarfora.justfit_cliente.Fragments.TimerFragment;
import com.github.aakira.expandablelayout.ExpandableRelativeLayout;
import com.github.aakira.expandablelayout.Utils;
import java.util.ArrayList;
import java.util.Calendar;

import com.example.andreacarfora.justfit_cliente.R;

public class GiornoAllenamentoActivity extends AppCompatActivity {

    private GiornoAllenameto training;
    private int GET_DAY;
    private Esercizio selectedEx;
    private ListView listView;
    private TextView textToolbar;
    private TextView tempNomeEx, tempAttrezzoEx,tempSerie,tempRip,txtDetails,textCounterSerie;
    private ImageView backButton,dotsDetails,fotoEx_temp,addSerie,subSerie,chevrArrow;
    private LinearLayout layoutStartTimer;
    private AudioManager am;
    private MediaPlayer mp;
    private LinearLayout linearLayout;
    private CountDownTimer timer;
    private int MIN=0, SEC=0;
    private CustomDialogSetRecupero customDialog;
    private ExpandableRelativeLayout expandableRelativeLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giorno_allenamento);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {

            } else {
                GET_DAY= extras.getInt("NUM_GIORNO");
            }
        }

        bindUiViewActivity();
        recuperoDati();

        AdapterEsercizi adapterEsercizi = new AdapterEsercizi(this,R.layout.item_esercizio,training.getEsercizi());
        listView.setAdapter(adapterEsercizi);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedEx = training.getEsercizi().get(position);
            }
        });


        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getThis(), DetailsExActivity.class);
                // intent.putExtra("NOME_EX",training.getEsercizi().get(position).getNome());
                startActivity(intent);
                return true;
            }
        });




        layoutStartTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(MIN==0 && SEC==0){
                    Snackbar snackbar = Snackbar
                            .make(linearLayout, "Imposta recupero", Snackbar.LENGTH_LONG)
                            .setAction("IMPOSTA", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    createDialog();
                                }
                            });

                    snackbar.show();
                }
                else {

                    TimerFragment dialog= new TimerFragment();
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    dialog.show(ft,"Timer");
                }
            }
        });


        chevrArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createRotateAnimator(chevrArrow, 180f, 0f).start();
                expandableRelativeLayout.toggle();
            }
        });

        /*
        resetTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timer.cancel();
                progressBar.setProgress(0);
                int seconds = (int) (finalMil / 1000) % 60;
                int minutes = (int) ((finalMil / (1000 * 60)) % 60);
                textTimer.setText(String.format("%02d", minutes)
                        + ":" + String.format("%02d", seconds));
                setTimer();
            }
        });
*/

/*
       set_timer.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               createDialog();
           }
       });

*/


    }


    private Context getThis(){
        return this;
    }



    public ObjectAnimator createRotateAnimator(final View target, final float from, final float to) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(target, "rotation", from, to);
        animator.setDuration(200);
        animator.setInterpolator(Utils.createInterpolator(Utils.LINEAR_INTERPOLATOR));
        return animator;
    }


    private void createDialog(){
        customDialog = new CustomDialogSetRecupero(GiornoAllenamentoActivity.this,R.layout.layout_popup_set_timer,R.id.okTimer,R.id.noTimer,MIN,SEC);
        customDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        customDialog.show();

        customDialog.setOnOKClickListner(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MIN = customDialog.getMinuti();
                SEC = customDialog.getSecondi();

                //setTimer();
                customDialog.dismiss();
            }
        });

        customDialog.setOnCancClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customDialog.dismiss();
            }
        });
    }

/*
    private void setTimer() {

        setTextTimer();

        timer = new CountDownTimer(finalMil, 1000) {


            PopupWindow mPopupWindow;


            public void onTick(long millisUntilFinished) {

                int seconds = (int) (millisUntilFinished / 1000) % 60;
                int minutes = (int) ((millisUntilFinished / (1000 * 60)) % 60);
               // textTimer.setText(String.format("%02d", minutes)
                      //  + ":" + String.format("%02d", seconds));

                int under = (int) TimeUnit.MINUTES.toMillis(minutes);
                under = under + (int) TimeUnit.SECONDS.toMillis(seconds);

                progressBar.setMax(finalMil);
                progressBar.setProgress(finalMil - under);


            }

            public void onFinish() {
                LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                View customView = inflater.inflate(R.layout.popup_layout, null);

                mPopupWindow = new PopupWindow(
                        customView,
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                );

                mPopupWindow.showAtLocation(linearLayout, Gravity.CENTER, 0, 0);

                mp.start();
                shadowBackground(mPopupWindow);
                new DismissAsyncTask(mPopupWindow).execute();

                progressBar.setProgress(0);
                setTextTimer();

            }


            public void shadowBackground(PopupWindow popupWindow) {
                View container = popupWindow.getContentView().getRootView();
                Context context = popupWindow.getContentView().getContext();
                WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
                WindowManager.LayoutParams p = (WindowManager.LayoutParams) container.getLayoutParams();
                p.flags |= WindowManager.LayoutParams.FLAG_DIM_BEHIND;
                p.dimAmount = 0.3f;
                wm.updateViewLayout(container, p);
            }

        };
    }



    private void setTextTimer(){
        int sec = (int) (finalMil / 1000) % 60;
        int min = (int) ((finalMil / (1000 * 60)) % 60);
       // textTimer.setText(String.format("%02d", min)
            ///    + ":" + String.format("%02d", sec));
    }
*/
    private void recuperoDati(){
        ArrayList<Esercizio> esercizi = new ArrayList<Esercizio>();
        esercizi.add(new Esercizio("","Alzate laterali", "8",4, GET_DAY,"Manubri", "Eseguire le rip. max 1 minuto di recupero", "Deltoidi", ""));
        esercizi.add(new Esercizio("","Alzate laterali", "8",4, GET_DAY,"Manubri", "Eseguire le rip. max 1 minuto di recupero", "Deltoidi", ""));
        esercizi.add(new Esercizio("","Alzate laterali", "8",4, GET_DAY,"Manubri", "Eseguire le rip. max 1 minuto di recupero", "Deltoidi", ""));
        esercizi.add(new Esercizio("","Alzate laterali", "8",4, GET_DAY,"Manubri", "Eseguire le rip. max 1 minuto di recupero", "Deltoidi", ""));
        esercizi.add(new Esercizio("","Alzate laterali", "8",4, GET_DAY,"Manubri", "Eseguire le rip. max 1 minuto di recupero", "Deltoidi", ""));
        esercizi.add(new Esercizio("","Alzate laterali", "8",4, GET_DAY,"Manubri", "Eseguire le rip. max 1 minuto di recupero", "Deltoidi", ""));
        esercizi.add(new Esercizio("","Alzate laterali", "8",4, GET_DAY,"Manubri", "Eseguire le rip. max 1 minuto di recupero", "Deltoidi", ""));

        training = new GiornoAllenameto("",GET_DAY, "",esercizi);

    }


    private void bindUiViewActivity() {
        textToolbar = findViewById(R.id.toolbarTrain);
        backButton = findViewById(R.id.backToSheda);
        textToolbar.setText("Giorno "+GET_DAY);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                finish();
            }
        });

        listView= findViewById(R.id.recyclerview_ex);
        layoutStartTimer = findViewById(R.id.layoutStartTimer);
        tempNomeEx = findViewById(R.id.tmpNomeEx);
        tempAttrezzoEx= findViewById(R.id.tmpAttrezzoEx);
        tempSerie= findViewById(R.id.tmpSerie);
        tempRip = findViewById(R.id.tmpRip);
        dotsDetails = findViewById(R.id.dots_Details);
        fotoEx_temp = findViewById(R.id.imgEx_tmp);
        txtDetails = findViewById(R.id.tmpDettaglio);
        linearLayout = findViewById(R.id.linearLayoutDay);
        chevrArrow = findViewById(R.id.chevArrow);
        addSerie = findViewById(R.id.seriePlus);
        subSerie = findViewById(R.id.serieMinus);
        textCounterSerie = findViewById(R.id.counterSerie);
        expandableRelativeLayout = findViewById(R.id.expandLayout);
    }
}


class DismissAsyncTask extends AsyncTask<Void, Integer, Void> {


    PopupWindow taskPopupWindow;

    public DismissAsyncTask(PopupWindow targetPopupWindow) {
        taskPopupWindow = targetPopupWindow;
    }

    @Override
    protected Void doInBackground(Void... params) {
        SystemClock.sleep(3000);
        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {

    }

    @Override
    protected void onPostExecute(Void result) {
        taskPopupWindow.dismiss();
    }


}