package com.example.pfe_test.modele;

public class annonceItem {
    private String titre, description, prix, ville, date;
    private String image;

    public annonceItem() {
    }

    public annonceItem(String image, String titre, String description, String prix, String ville, String date ) {
        this.titre = titre;
        this.description = description;
        this.image=image;
        this.prix = prix;
        this.ville = ville;
        this.date = date;
    }


    // getter
    public String getTitre() {
        return titre;
    }

    public String getDescription() {
        return description;
    }

    public String getPrix() {
        return prix;
    }

    public String getVille() {
        return ville;
    }

    public String getDate() {
        return date;
    }

    public String getImage() {
        return image;
    }

    // setter


    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrix(String prix) {
        this.prix = prix;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
