package com.example.pfe_test.Fragments;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pfe_test.AjouterAnnonceActivity;
import com.example.pfe_test.LoginActivity;
import com.example.pfe_test.R;
import com.example.pfe_test.controleur.myAdapter;
import com.example.pfe_test.modele.annonceItem;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class fragmentAccueil extends Fragment{
    FloatingActionButton btn_ajouter_annonce;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;

    List<annonceItem> myList;
    annonceItem annonce;

    private DatabaseReference databaseReference;
    private ValueEventListener eventListener;

    ProgressDialog progressDialog;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v=inflater.inflate(R.layout.activity_accueil,container,false) ;


        recyclerView =(RecyclerView) v.findViewById(R.id.recyclerView);
        btn_ajouter_annonce =(FloatingActionButton) v.findViewById(R.id.ajouterAnnonce);

        progressDialog = new ProgressDialog(fragmentAccueil.this.getContext());
        progressDialog.setMessage("Louading ...");

        recyclerView.setHasFixedSize(true);

        myList = new ArrayList<annonceItem>();

/*
        annonce = new annonceItem(R.drawable.tmax,"Tmax 364","modele 2019...","20000 dh" ,"Oujda" ,"10/02/2020");
        myList.add(annonce);
        annonce = new annonceItem(R.drawable.maison,"Maison","maison a oujda 7ay lazari 150m²","1300000 dh" ,"marakech" ,"06/12/2019");
        myList.add(annonce);
        annonce = new annonceItem(R.drawable.renault_clio,"Renault Clio 4","model 2015 \n mazot ...","70000 dh" ,"Rabat" ,"09/03/2019");
        myList.add(annonce);

        annonce = new annonceItem(R.drawable.tmax,"Tmax 365","modele 2019...","30000 dh" ,"Berkan" ,"28/07/2020");
        myList.add(annonce);
        annonce = new annonceItem(R.drawable.lenovo,"Pc lenovo yoga","i7 \n8eme gen \n 8Gb Ram","2600 dh" ,"casa" ,"15/11/2020");
        myList.add(annonce);
*/


        //GridLayoutManager gridLayoutManager = new GridLayoutManager(fragmentAccueil.this.getContext(),1);
        //layoutManager = new LinearLayoutManager(fragmentAccueil.this.getContext());
        // linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(new LinearLayoutManager(fragmentAccueil.this.getContext()));


        adapter = new myAdapter(fragmentAccueil.this.getContext(),myList);
        recyclerView.setAdapter(adapter);

        databaseReference = FirebaseDatabase.getInstance().getReference("annance");

        progressDialog.show();

        eventListener = databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                myList.clear();

                for (DataSnapshot itemSnapshot : dataSnapshot.getChildren()){

                    annonceItem annonce = itemSnapshot.getValue(annonceItem.class);

                    myList.add(annonce);

                }

                adapter.notifyDataSetChanged();
                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                progressDialog.dismiss();
            }
        });


        btn_ajouter_annonce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inscrireIntent = new Intent(fragmentAccueil.this.getContext(), AjouterAnnonceActivity.class);
                startActivity(inscrireIntent);
            }
        });


        return v;
    }








}
