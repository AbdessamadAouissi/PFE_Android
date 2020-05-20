package com.example.pfe_test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.santalu.maskedittext.MaskEditText;


public class inscrireActivity extends AppCompatActivity {
    private TextInputLayout NOM;
    private TextInputLayout PRENOM;
    private MaskEditText textofTELEPHONE;
    private TextInputLayout TELEPHONE;
    private TextInputLayout EMAIL;
    private TextInputLayout PWD;
    private TextInputLayout PWD_CONFIRMER;
    private Button inscrire;

    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscrire);
        db = new DatabaseHelper(this);

        NOM = (TextInputLayout) findViewById(R.id.text_input_nom);
        PRENOM = (TextInputLayout) findViewById(R.id.text_input_prenom);
        textofTELEPHONE = (MaskEditText) findViewById(R.id.edit_text_phone1);
        TELEPHONE = (TextInputLayout) findViewById(R.id.edit_text_phone);
        EMAIL = (TextInputLayout) findViewById(R.id.text_input_mail);
        PWD = (TextInputLayout) findViewById(R.id.text_input_pdw);
        PWD_CONFIRMER = (TextInputLayout) findViewById(R.id.text_input_pwdConf);
        inscrire = (Button) findViewById(R.id.btn_inscrire);


    }

    private boolean validate_nom() {
        String nom = NOM.getEditText().getText().toString().trim();
        if (nom.isEmpty()) {
            NOM.setError("Nom est vide !");
            return false;
        } else if (nom.length() > 15) {
            NOM.setError("Nom est long");
            return false;
        } else {
            NOM.setError(null);
            return true;
        }
    }
    private boolean validate_prenom() {
        String prenom = PRENOM.getEditText().getText().toString().trim();
        if (prenom.isEmpty()) {
            PRENOM.setError("Prenom est vide !");
            return false;
        } else if (prenom.length() > 15) {
            PRENOM.setError("Prenom est long");
            return false;
        } else {
            PRENOM.setError(null);
            return true;
        }
    }

    private boolean validatePhone() {
        String phone = textofTELEPHONE.getRawText();
        if (phone.isEmpty()){
            TELEPHONE.setError("Numero de telephone est vide !");
            return false;
        }else if (phone.length() != 10){
            TELEPHONE.setError("Numero de telephone no valide");
            return false;
        }else {
            TELEPHONE.setError(null);
            return true;
        }

    }

    private boolean validateEmail() {
        String emailInput = EMAIL.getEditText().getText().toString().trim();
        if (emailInput.isEmpty()) {
            EMAIL.setError("Email est vide ");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()){
            EMAIL.setError("Email no valide !");
            return false;
        }else {
            EMAIL.setError(null);
            return true;
        }

    }

    private boolean validatePassword() {
        String passwordInput = PWD.getEditText().getText().toString();
        if (passwordInput.isEmpty()) {
            PWD.setError("Mot de passe est vide !");
            return false;
        } else {
            PWD.setError(null);
            return true;
        }
    }

    private boolean validatePasswordConfirmer() {
        String passwordInput = PWD_CONFIRMER.getEditText().getText().toString();
        if (passwordInput.isEmpty()) {
            PWD_CONFIRMER.setError("Mot de passe confirmer est vide !");
            return false;
        } else if (!passwordInput.equals(PWD.getEditText().getText().toString())) {
            PWD_CONFIRMER.setError("Mot de passe confirmer est erroné");
            return false;
        } else {
            PWD_CONFIRMER.setError(null);
            return true;
        }
    }


    public void retour(View view) {
        inscrireActivity.this.finish();
    }

    public void inscrire(View view) {
        if (!validate_nom() | !validatePhone() | !validate_prenom() | !validateEmail() | !validatePassword() | !validatePasswordConfirmer() ) {
            return;
        }
        String nom = NOM.getEditText().getText().toString();
        String prenom = PRENOM.getEditText().getText().toString();
        String tele = textofTELEPHONE.getRawText();
        String email = EMAIL.getEditText().getText().toString();
        String pwd = PWD.getEditText().getText().toString();



            long val = db.addUser(nom,prenom,tele,email,pwd);
            if(val > 0){
                Toast.makeText(inscrireActivity.this,"Inscription avec succès",Toast.LENGTH_SHORT).show();
                inscrireActivity.this.finish();
            }
            else{
                Toast.makeText(inscrireActivity.this,"Erreur d'inscription",Toast.LENGTH_SHORT).show();
            }

    }


}
