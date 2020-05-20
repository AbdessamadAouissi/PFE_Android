package com.example.pfe_test;

public class annonceItem {
    String titre, description, prix, ville, date;
    int image;

    public annonceItem(int image, String titre, String description,String prix, String ville, String date ) {
        this.titre = titre;
        this.description = description;
        this.image=image;
        this.prix = prix;
        this.ville = ville;
        this.date = date;
    }


}
