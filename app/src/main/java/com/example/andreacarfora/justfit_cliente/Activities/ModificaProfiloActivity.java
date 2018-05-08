package com.example.andreacarfora.justfit_cliente.Activities;

import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.system.ErrnoException;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.andreacarfora.justfit_cliente.R;
import com.firebase.ui.storage.images.FirebaseImageLoader;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;


public class ModificaProfiloActivity extends AppCompatActivity {

    private static final int REQUEST_LOGIN = 0;
    private ImageView close, check;
    private EditText data,nome,cognome,telefono;
    private StorageReference storageReference;
    private FirebaseStorage storage;
    private Uri mCropImageUri;
    private Uri downloadUrl;
    private CircleImageView mCropImageViewProfile;
    private CropImageView mCropImageViewCrop;
    private FirebaseFirestore db;
    private String nomeS,cognomeS,dataS,telefonoS;



    @Override
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



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifica_profilo);
        db = FirebaseFirestore.getInstance();

        bindUiViewActivity();
        checkCloseListener();
        dataManager();
        photoManager();
    }

    private void photoManager(){

        mCropImageViewProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onLoadImageClick(view);
            }
        });
    }

    private void dataManager(){
        final GregorianCalendar myCalendar = new GregorianCalendar();
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String myFormat = "dd/MM/yy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

                data.setText(sdf.format(myCalendar.getTime()));
            }

        };
        data.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    new DatePickerDialog(ModificaProfiloActivity.this, date, myCalendar
                            .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                            myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                }
            }
        });
        data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(ModificaProfiloActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

    }



    private void bindUiViewActivity() {

        close = findViewById(R.id.closeModify);
        check = findViewById(R.id.checkModify);
        data = findViewById(R.id.datanascitaEditModify);
        data.setInputType(InputType.TYPE_NULL);
        nome = findViewById(R.id.nameEditModify);
        cognome=findViewById(R.id.cognomeEditModify);
        telefono = findViewById(R.id.telefonoEditModify);
        mCropImageViewProfile = findViewById(R.id.modifyprofilePhoto);
    }

    private void checkCloseListener(){
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                finish();
            }
        });

        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Map<String, Object> map = new HashMap<>();
                map.put("nome", controllo(nome.getText().toString()));
                map.put("cognome", controllo(cognome.getText().toString()));
                map.put("numeroTelefonico", controllo(telefono.getText().toString()));
                map.put("dataDiNascita", controllo(data.getText().toString()));

                db.collection("Clienti").document(FirebaseAuth.getInstance().getCurrentUser().getUid()).update(map);
                onBackPressed();
                finish();
            }
        });
    }



    private String controllo(String s){

        String myS="";
        for(int i=0; i<s.length();i++){
            if(s.charAt(i)!=' '){
                myS +=s.charAt(i);
            }
        }
        return myS;
    }


    private void updateProfileInfo(boolean isTrue) {

        if (isTrue) {
            Log.i("Check : ", "Sono nel metodo updateProfileInfo, caso true");
            FirebaseStorage storage = FirebaseStorage.getInstance("gs://justifit-c99ed.appspot.com");
            StorageReference storageReference = storage.getReference();
            StorageReference profilePhotoRef = storageReference.child("Clienti/" + FirebaseAuth.getInstance().getCurrentUser().getEmail() + "/profilePhoto/fotoProfilo");

            Glide.with(this)
                    .using(new FirebaseImageLoader())
                    .load(profilePhotoRef)
                    .into(mCropImageViewProfile);


            DocumentReference user = db.collection("Clienti").document(FirebaseAuth.getInstance().getCurrentUser().getUid());

            user.addSnapshotListener(new EventListener< DocumentSnapshot >() {

                @Override

                public void onEvent(DocumentSnapshot documentSnapshot, FirebaseFirestoreException e) {

                    if (e != null) {
                        Log.d("ERROR", e.getMessage());
                        return;
                    }

                    if (documentSnapshot != null && documentSnapshot.exists()) {
                        nome.setText((String) documentSnapshot.get("nome")+" ");
                        cognome.setText((String)documentSnapshot.get("cognome"));
                        telefono.setText((String) documentSnapshot.get("numeroTelefonico"));
                        data.setText((String) documentSnapshot.get("dataDiNascita"));

                        nomeS = nome.getText().toString();
                        cognomeS = cognome.getText().toString();
                        dataS=data.getText().toString();
                        telefonoS = telefono.getText().toString();
                    }
                }
            });

        }

    }


    private void setProfilePhoto(boolean isTrue){
        if(isTrue){

            FirebaseStorage storage = FirebaseStorage.getInstance("gs://justifit-c99ed.appspot.com");
            StorageReference storageReference = storage.getReference();

            StorageReference profilePhotoRef = storageReference.child("Clienti/" + FirebaseAuth.getInstance().getCurrentUser().getEmail() + "/profilePhoto/fotoProfilo");
            UploadTask uploadTask;

            uploadTask = profilePhotoRef.putFile(mCropImageViewCrop.getImageUri());

            uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Toast.makeText(getApplicationContext(), "Foto del profilo aggiornata!", Toast.LENGTH_LONG).show();
                    downloadUrl = taskSnapshot.getDownloadUrl();
                    System.out.println("Download url : " + downloadUrl);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getApplicationContext(), "Errore nel caricamento della foto del profilo", Toast.LENGTH_LONG).show();
                }
            });

            System.out.println("Uri immagine : " + mCropImageViewCrop.getImageUri());
        }
    }

    @Override
    public void onActivityResult(int  requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {

            if (requestCode == REQUEST_LOGIN){
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(this, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }

            Uri imageUri =  getPickImageResultUri(data);

            // For API >= 23 we need to check specifically that we have permissions to read external storage,
            // but we don't know if we need to for the URI so the simplest is to try open the stream and see if we get error.
            boolean requirePermissions = false;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
                    checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED &&
                    isUriRequiresPermissions(imageUri)) {

                // request permissions and handle the result in onRequestPermissionsResult()
                requirePermissions = true;
                mCropImageUri = imageUri;
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 0);
            }

            if (!requirePermissions) {
                final View v = LayoutInflater.from(this).inflate(R.layout.crop_image_layout, null);

                mCropImageViewCrop = v.findViewById(R.id.cropImageView);
                mCropImageViewCrop.setImageUriAsync(imageUri);

                new AlertDialog.Builder(this, R.style.Theme_AlertDialog)
                        .setIcon(R.drawable.crop)
                        .setTitle("Ritaglia foto")
                        .setView(v)
                        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                dialog.cancel();
                                onCropImageClick(v);
                            }
                        })
                        .setNeutralButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        })
                        .show();
            }
        }
    }

    public Uri getPickImageResultUri(Intent  data) {
        boolean isCamera = true;
        if (data != null && data.getData() != null) {
            String action = data.getAction();
            isCamera = action != null  && action.equals(MediaStore.ACTION_IMAGE_CAPTURE);
        }
        return isCamera ?  getCaptureImageOutputUri() : data.getData();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public boolean isUriRequiresPermissions(Uri uri) {
        try {
            ContentResolver resolver = getContentResolver();
            InputStream stream = resolver.openInputStream(uri);
            stream.close();
            return false;
        } catch (FileNotFoundException e) {
            if (e.getCause() instanceof ErrnoException) {
                return true;
            }
        } catch (Exception e) {
        }
        return false;
    }

    public void onLoadImageClick(View view) {
        startActivityForResult(getPickImageChooserIntent(), 200);
    }

    /**
     * Crop the image and set it back to the  cropping view.
     */
    public void onCropImageClick(View view) {
        Bitmap cropped =  mCropImageViewCrop.getCroppedImage(500, 500);
        if (cropped != null)
            mCropImageViewProfile.setImageBitmap(cropped);
        setProfilePhoto(true);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        if (mCropImageUri != null && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            mCropImageViewCrop.setImageUriAsync(mCropImageUri);
        } else {
            Toast.makeText(getApplicationContext(), "Required permissions are not granted", Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Create a chooser intent to select the  source to get image from.<br/>
     * The source can be camera's  (ACTION_IMAGE_CAPTURE) or gallery's (ACTION_GET_CONTENT).<br/>
     * All possible sources are added to the  intent chooser.
     */
    public Intent getPickImageChooserIntent() {

// Determine Uri of camera image to  save.
        Uri outputFileUri =  getCaptureImageOutputUri();

        List<Intent> allIntents = new  ArrayList<>();
        PackageManager packageManager =getPackageManager();

// collect all camera intents
        Intent captureIntent = new  Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        List<ResolveInfo> listCam =  packageManager.queryIntentActivities(captureIntent, 0);
        for (ResolveInfo res : listCam) {
            Intent intent = new  Intent(captureIntent);
            intent.setComponent(new  ComponentName(res.activityInfo.packageName, res.activityInfo.name));
            intent.setPackage(res.activityInfo.packageName);
            if (outputFileUri != null) {
                intent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
            }
            allIntents.add(intent);
        }

// collect all gallery intents
        Intent galleryIntent = new  Intent(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        List<ResolveInfo> listGallery =  packageManager.queryIntentActivities(galleryIntent, 0);
        for (ResolveInfo res : listGallery) {
            Intent intent = new  Intent(galleryIntent);
            intent.setComponent(new  ComponentName(res.activityInfo.packageName, res.activityInfo.name));
            intent.setPackage(res.activityInfo.packageName);
            allIntents.add(intent);
        }

// the main intent is the last in the  list (fucking android) so pickup the useless one
        Intent mainIntent =  allIntents.get(allIntents.size() - 1);
        for (Intent intent : allIntents) {
            if  (intent.getComponent().getClassName().equals("com.android.documentsui.DocumentsActivity"))  {
                mainIntent = intent;
                break;
            }
        }
        allIntents.remove(mainIntent);

// Create a chooser from the main  intent
        Intent chooserIntent =  Intent.createChooser(mainIntent, "Select source");

// Add all other intents
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS,  allIntents.toArray(new Parcelable[allIntents.size()]));

        return chooserIntent;
    }

    /**
     * Get URI to image received from capture  by camera.
     */
    private Uri getCaptureImageOutputUri() {
        Uri outputFileUri = null;
        File getImage = getExternalCacheDir();
        if (getImage != null) {
            outputFileUri = Uri.fromFile(new  File(getImage.getPath(), "pickImageResult.jpeg"));
        }
        return outputFileUri;
    }


}
