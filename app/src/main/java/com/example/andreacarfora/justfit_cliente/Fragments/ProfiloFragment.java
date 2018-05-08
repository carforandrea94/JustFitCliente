package com.example.andreacarfora.justfit_cliente.Fragments;



import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.andreacarfora.justfit_cliente.Activities.LoginActivity;
import com.example.andreacarfora.justfit_cliente.Activities.MainActivity;
import com.example.andreacarfora.justfit_cliente.Activities.ModificaProfiloActivity;
import com.example.andreacarfora.justfit_cliente.R;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.util.ArrayList;
import java.util.List;

import com.example.andreacarfora.justfit_cliente.Adapters.SpinnerData;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.example.andreacarfora.justfit_cliente.R.color.rosso;


public class ProfiloFragment extends Fragment {

    private FirebaseFirestore db;
    private MaterialSpinner  spinner;
    private ImageView imageViewSpinner;
    private TextView numberTextView;
    private TextView emailTextView;
    private TextView dateTextView;
    private TextView genderText;
    private TextView textView;
    private CircleImageView mCropImageViewProfile;
    private CropImageView mCropImageViewCrop;
    private ImageView editProf;
    private List<SpinnerData> itemSpinner;
    private String NOME;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        View v = bindUiFragment(inflater,container);


        editProf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ModificaProfiloActivity.class);
                startActivity(intent);
            }
        });



        spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                if(position == 1){
                    AlertDialog alertDialog = new AlertDialog.Builder(getContext()).create();
                    alertDialog.setTitle("Logout");
                    alertDialog.setMessage("Sicuro di voler uscire?");
                    alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Logout",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    spinner.setSelectedIndex(0);
                                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                                    FirebaseAuth.getInstance().signOut();
                                    dialog.dismiss();
                                    startActivity(intent);
                                    getActivity().finish();
                                }
                            });
                    alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Annulla",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    spinner.setSelectedIndex(0);
                                    dialog.dismiss();

                                }
                            });
                    alertDialog.show();
                }
            }
        });




        return v;
    }


    public void onStart() {
        super.onStart();
        updateProfileInfo(true);
    }

    @Override
    public void onResume() {
        super.onResume();
        updateProfileInfo(true);
    }

    @Override
    public void onPause() {
        super.onPause();
        updateProfileInfo(false);
    }

    private void updateProfileInfo(boolean isTrue) {

        if (isTrue) {
            recuperoDati();
        }

    }


    private View bindUiFragment(LayoutInflater inflater, ViewGroup container) {
        View v = inflater.inflate(R.layout.fragment_profilo, container, false);

        numberTextView = v.findViewById(R.id.numberTextView);
        emailTextView = v.findViewById(R.id.emailTextView);
        genderText = v.findViewById(R.id.gender_profiloText);
        mCropImageViewProfile = v.findViewById(R.id.profilePhoto);
        dateTextView = v.findViewById(R.id.dateTextView);
        editProf = v.findViewById(R.id.editProfileButton);
        spinner = v.findViewById(R.id.spinner);


        return v;
    }


    private void recuperoDati() {
        Log.i("Check : ", "Sono nel metodo updateProfileInfo, caso true");
        FirebaseStorage storage = FirebaseStorage.getInstance("gs://justifit-c99ed.appspot.com");
        StorageReference storageReference = storage.getReference();
        StorageReference profilePhotoRef = storageReference.child("Clienti/" + FirebaseAuth.getInstance().getCurrentUser().getEmail()+ "/profilePhoto/fotoProfilo");

        Glide.with(getContext())
                .using(new FirebaseImageLoader())
                .load(profilePhotoRef)
                .into(mCropImageViewProfile);

        db = FirebaseFirestore.getInstance();
        DocumentReference user = db.collection("Clienti").document(FirebaseAuth.getInstance().getCurrentUser().getUid());

        user.addSnapshotListener(new EventListener < DocumentSnapshot > () {

            @Override

            public void onEvent(DocumentSnapshot documentSnapshot, FirebaseFirestoreException e) {

                if (e != null) {
                    Log.d("ERROR", e.getMessage());
                    return;
                }

                if (documentSnapshot != null && documentSnapshot.exists()) {
                    NOME = documentSnapshot.get("nome")+" "+documentSnapshot.get("cognome");
                    spinner.setItems(NOME.toUpperCase(),"Logout");
                    emailTextView.setText((String) documentSnapshot.get("email"));
                    numberTextView.setText((String) documentSnapshot.get("numeroTelefonico"));
                    genderText.setText((String) documentSnapshot.get("sesso"));
                    dateTextView.setText((String) documentSnapshot.get("dataDiNascita"));
                }
            }
        });
    }
}