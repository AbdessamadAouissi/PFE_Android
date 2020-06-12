package com.example.pfe_test.controleur;

import android.content.Context;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.pfe_test.*;
import com.example.pfe_test.modele.annonceItem;

import java.util.ArrayList;


public class myAdapter extends RecyclerView.Adapter<viewHolder>{


    private ArrayList<annonceItem> myList;
    private Context context;
    public myAdapter(ArrayList<annonceItem> myList) {

        this.myList = myList;
    }



    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.formatview,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final viewHolder holder, int position) {
        final annonceItem annonce = myList.get(position);

        Glide.with(context)
                .load(annonce.getImage())
                .into(holder.getImage());


        holder.getTitre().setText(annonce.getTitre());
        holder.getDescription().setText(annonce.getDescription());
        holder.getPrix().setText(annonce.getPrix());
        holder.getDate().setText(annonce.getDate());
        holder.getVille().setText(annonce.getVille());

        holder.getCardView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,DetailAnnonce.class);

                intent.putExtra("image",myList.get(holder.getAdapterPosition()).getImage());
                intent.putExtra("titre",myList.get(holder.getAdapterPosition()).getTitre());
                intent.putExtra("description",myList.get(holder.getAdapterPosition()).getDescription());
                intent.putExtra("prix",myList.get(holder.getAdapterPosition()).getPrix());
                intent.putExtra("ville",myList.get(holder.getAdapterPosition()).getVille());
                intent.putExtra("date",myList.get(holder.getAdapterPosition()).getDate());

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.myList.size();
    }



    public void filtrerList(ArrayList<annonceItem> filterListe) {

        myList = filterListe;
        notifyDataSetChanged();

    }

}







