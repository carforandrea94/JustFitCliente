package com.example.andreacarfora.justfit_cliente.Activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.example.andreacarfora.justfit_cliente.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private TextView goToRegistrationText;
    private Button loginButton;
    private Button resetButton;
    private EditText emailText;
    private EditText passwordText;
    private ProgressBar loginProgressDialog;
    private boolean isLoginCompleted;
    private Handler handler;
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    private TextView forgetPasswordText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        bindUiViewActivity();


        goToRegistrationText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RegistrazioneActivity.class);
                startActivity(intent);
            }
        });


        forgetPasswordText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(emailText.getText().toString().equals("")){
                    emailText.setError("Inserire email per cambiare password");
                }else{
                    mAuth.sendPasswordResetEmail(emailText.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(LoginActivity.this, "Emal inviata a " + emailText.getText().toString(), Toast.LENGTH_SHORT).show();
                                emailText.setText("");
                            }
                        }
                    });
                }
            }
        });


        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                emailText.setText("");
                passwordText.setText("");
            }
        });


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validateForm()){

                    InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

                    showProgressDialog();

                    mAuth.signInWithEmailAndPassword(emailText.getText().toString(), passwordText.getText().toString()).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                currentUser = mAuth.getCurrentUser();
                                isLoginCompleted = true;
                            }else{
                                Toast.makeText(LoginActivity.this, "Credenziali errate, riprovare", Toast.LENGTH_SHORT).show();
                                loginProgressDialog.setVisibility(ProgressBar.INVISIBLE);
                                loginProgressDialog.setProgress(0);
                                emailText.setText("");
                                passwordText.setText("");
                            }
                        }
                    });
                }
            }
        });
    }



    private void bindUiViewActivity() {
        isLoginCompleted = false;
        handler = new Handler();
        mAuth = FirebaseAuth.getInstance();
        goToRegistrationText = findViewById(R.id.creaNuovoAccount);
        loginButton = findViewById(R.id.buttonLogin);
        resetButton = findViewById(R.id.buttonReset);
        emailText= findViewById(R.id.email_login);
        passwordText = findViewById(R.id.password_login);
        forgetPasswordText = findViewById(R.id.forgotPassword);
        loginProgressDialog = findViewById(R.id.progressBar);
        loginProgressDialog.setVisibility(ProgressBar.INVISIBLE);

        TextView textView = findViewById(R.id.textLogoLogin);
        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/font.ttf");
        textView.setTypeface(font);

    }

    @Override
    public void onStart() {
        super.onStart();
        currentUser = mAuth.getCurrentUser();
        goToHome(currentUser);
    }

    private void goToHome(FirebaseUser user){
        if(user != null){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    private boolean validateForm(){
        boolean isValid = false;

        if(!emailText.getText().toString().equals("") && !passwordText.getText().toString().equals("")){
            isValid = true;
        }else if(emailText.getText().toString().equals("")){
            emailText.setError("Il campo " + emailText.getHint() + " è vuoto");
        }else if(passwordText.getText().toString().equals("")){
            passwordText.setError("Il campo " + passwordText.getHint() + " è vuoto");
        }

        return  isValid;
    }

    private void showProgressDialog(){

        loginProgressDialog.setMax(100);
        loginProgressDialog.setVisibility(ProgressBar.VISIBLE);

        new Thread(new Runnable() {
            @Override
            public void run() {

                for(int i = 0; i<100;i=i+10){

                    try{
                        Thread.sleep(300);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }

                        loginProgressDialog.setProgress(i);
                    }

                goToHome(currentUser);
            }
        }).start();
    }


}
