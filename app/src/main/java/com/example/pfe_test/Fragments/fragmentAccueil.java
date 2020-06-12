package com.example.pfe_test.Fragments;
import android.app.ProgressDialog;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.fragment.app.Fragment;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pfe_test.AjouterAnnonceActivity;

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



public class fragmentAccueil extends Fragment{

    public FloatingActionButton btn_ajouter_annonce;
    public RecyclerView recyclerView;
    public RecyclerView.Adapter adapter;


    public ArrayList<annonceItem> myList;


    private DatabaseReference databaseReference;
    private ValueEventListener eventListener;

    ProgressDialog progressDialog;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v=inflater.inflate(R.layout.activity_accueil,container,false) ;

        recyclerView = v.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        btn_ajouter_annonce = v.findViewById(R.id.ajouterAnnonce);


        progressDialog = new ProgressDialog(fragmentAccueil.this.getContext());
        progressDialog.setMessage("Loading ...");




        recyclerView.setHasFixedSize(true);

        myList = new ArrayList<annonceItem>();


        adapter = new myAdapter(myList);
        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));




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
