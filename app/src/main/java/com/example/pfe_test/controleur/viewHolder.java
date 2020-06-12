package com.example.pfe_test.controleur;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pfe_test.R;

public class viewHolder extends RecyclerView.ViewHolder {
    private ImageView image;
    private TextView titre, description, prix, date, ville;

    public viewHolder(@NonNull View itemView) {
        super(itemView);
        image = itemView.findViewById(R.id.imageView);
        titre = itemView.findViewById(R.id.titre);
        description = itemView.findViewById(R.id.discription);
        prix = itemView.findViewById(R.id.prix);
        date = itemView.findViewById(R.id.date);
        ville = itemView.findViewById(R.id.ville);


    }


    // getter
    public ImageView getImage() {
        return image;
    }

    public TextView getTitre() {
        return titre;
    }

    public TextView getDescription() {
        return description;
    }

    public TextView getPrix() {
        return prix;
    }

    public TextView getDate() {
        return date;
    }

    public TextView getVille() {
        return ville;
    }
}
