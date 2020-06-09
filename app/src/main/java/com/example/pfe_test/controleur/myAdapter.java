package com.example.pfe_test.controleur;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.pfe_test.*;
import com.example.pfe_test.modele.annonceItem;

import java.util.List;

public class myAdapter extends RecyclerView.Adapter<viewHolder>{

    private Context context;
    private List<annonceItem> myList;

    public myAdapter(Context context, List<annonceItem> myList) {
        this.context = context;
        this.myList = myList;
    }
    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(context).inflate(R.layout.formatview,parent,false);
        viewHolder VH = new viewHolder(mView);
        return VH;
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        annonceItem annonce = myList.get(position);

        Glide.with(context)
                .load(annonce.getImage())
                .into(holder.image);


        holder.titre.setText(annonce.getTitre());
        holder.description.setText(annonce.getDescription());
        holder.prix.setText(annonce.getPrix());
        holder.date.setText(annonce.getDate());
        holder.ville.setText(annonce.getVille());
    }

    @Override
    public int getItemCount() {
        return myList.size();
    }
}


class viewHolder extends RecyclerView.ViewHolder{
    ImageView image;
    TextView titre,description,prix,date,ville;

    public viewHolder(@NonNull View itemView) {
        super(itemView);
        image=itemView.findViewById(R.id.imageView);
        titre=itemView.findViewById(R.id.titre);
        description=itemView.findViewById(R.id.discription);
        prix=itemView.findViewById(R.id.prix);
        date=itemView.findViewById(R.id.date);
        ville=itemView.findViewById(R.id.ville);


    }

}