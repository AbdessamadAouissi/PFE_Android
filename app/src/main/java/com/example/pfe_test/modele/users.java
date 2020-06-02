package com.example.pfe_test.modele;

public class users {

    private String Nom,Prenom,Telephone,Email,Pwd;


    // Constructeur
    public users(String nom, String prenom, String telephone, String email,String pwd) {
        Nom = nom;
        Prenom = prenom;
        Telephone = telephone;
        Email = email;
        Pwd=pwd;
    }



    // Getters
    public String getNom() {
        return Nom;
    }

    public String getPrenom() {
        return Prenom;
    }

    public String getTelephone() {
        return Telephone;
    }

    public String getEmail() {
        return Email;
    }

    public String getPwd() {
        return Pwd;
    }

    // Setters
    public void setNom(String nom) {
        Nom = nom;
    }

    public void setPrenom(String prenom) {
        Prenom = prenom;
    }

    public void setTelephone(String telephone) {
        Telephone = telephone;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public void setPwd(String pwd) {
        Pwd = pwd;
    }
}
