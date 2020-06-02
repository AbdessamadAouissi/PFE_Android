package com.example.pfe_test;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    private TextInputLayout textInputEmail;
    private TextInputLayout textInputPassword;
    private Button mSeConnecter;
    private TextView mInscrire;
    private ProgressBar progressBar;
    private FirebaseAuth Auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Auth = FirebaseAuth.getInstance();

        textInputEmail = findViewById(R.id.email_login);
        textInputPassword = findViewById(R.id.password_login);
        mSeConnecter = (Button) findViewById(R.id.btn_connecter);
        progressBar = (ProgressBar)findViewById(R.id.progress_bar_signin);
        mSeConnecter.setEnabled(false);
        textInputEmail.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mSeConnecter.setEnabled(s.toString().length()!=0 && textInputPassword.getEditText().getText().toString().length()!=0);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        textInputPassword.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mSeConnecter.setEnabled(s.toString().length()!=0 && textInputEmail.getEditText().getText().toString().length()!=0);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        mInscrire = (TextView) findViewById(R.id.inscrire);
        mInscrire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inscrireIntent = new Intent(LoginActivity.this,inscrireActivity.class);
                startActivity(inscrireIntent);
            }
        });

        mSeConnecter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validateEmail() | !validatePassword()) {
                    return;
                }
                String email = textInputEmail.getEditText().getText().toString().trim();
                String pwd = textInputPassword.getEditText().getText().toString().trim();

                progressBar.setVisibility(View.VISIBLE);


                Auth.signInWithEmailAndPassword(email, pwd)
                        .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Toast.makeText(LoginActivity.this,"connection validé",Toast.LENGTH_SHORT).show();
                                    progressBar.setVisibility(View.GONE);
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Toast.makeText(LoginActivity.this,"Erreur de connexion",Toast.LENGTH_SHORT).show();
                                    progressBar.setVisibility(View.GONE);
                                }


                            }
                        });


            }
        });

    }

    private boolean validateEmail() {
        String emailInput = textInputEmail.getEditText().getText().toString().trim();
        if (emailInput.isEmpty()) {
            textInputEmail.setError("Email est vide ");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()){
            textInputEmail.setError("Email no valide !");
            return false;
        }else {
            textInputEmail.setError(null);
            return true;
        }

    }

    private boolean validatePassword() {
        String passwordInput = textInputPassword.getEditText().getText().toString().trim();
        if (passwordInput.isEmpty()) {
            textInputPassword.setError("Mot de passe est vide");
            return false;
        } else {
            textInputPassword.setError(null);
            return true;
        }
    }


    public void retour(View view) {
        LoginActivity.this.finish();
    }

}
