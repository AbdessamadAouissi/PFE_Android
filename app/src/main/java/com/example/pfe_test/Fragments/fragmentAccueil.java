package com.example.pfe_test.Fragments;
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
import androidx.fragment.app.Fragment;

import com.example.pfe_test.LoginActivity;
import com.example.pfe_test.R;
import com.example.pfe_test.modele.annonceItem;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class fragmentAccueil extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ArrayList<annonceItem> listItemView =new ArrayList<annonceItem>();
        View v=inflater.inflate(R.layout.activity_accueil,container,false) ;
        listItemView.add(new annonceItem(R.drawable.tmax,"Tmax 360","modele 2019...","20000 dh" ,"Oujda" ,"10/02/2020"));
        listItemView.add(new annonceItem(R.drawable.maison,"Maison","maison a oujda 7ay lazari 150m²","1300000 dh" ,"marakech" ,"06/12/2019"));
        listItemView.add(new annonceItem(R.drawable.renault_clio,"Renault Clio 4","model 2015 \n mazot ...","70000 dh" ,"Rabat" ,"09/03/2019"));
        listItemView.add(new annonceItem(R.drawable.lenovo,"Pc lenovo yoga","i7 \n8eme gen \n 8Gb Ram","2600 dh" ,"casa" ,"15/11/2020"));
        listItemView.add(new annonceItem(R.drawable.tmax,"Tmax 360","modele 2019...","30000 dh" ,"Berkan" ,"28/07/2020"));



        fragmentAccueil.myAdapter adptr = new fragmentAccueil.myAdapter(listItemView);
        FloatingActionButton btn_ajouter_annonce = v.findViewById(R.id.ajouterAnnonce);
        btn_ajouter_annonce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inscrireIntent = new Intent(fragmentAccueil.this.getContext(), LoginActivity.class);
                startActivity(inscrireIntent);
            }
        });
        ListView lv = v.findViewById(R.id.malist);
        lv.setAdapter(adptr);
        return v;
    }



    class myAdapter extends BaseAdapter {

        ArrayList<annonceItem> listItemView = new ArrayList<annonceItem>();
        public myAdapter(ArrayList<annonceItem> listItemView){
            this.listItemView=listItemView;
        }
        @Override
        public int getCount() {
            return listItemView.size();
        }

        @Override
        public Object getItem(int position) {
            return (Object) listItemView.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater layoutInflater=getLayoutInflater();
            View V = layoutInflater.inflate(R.layout.formatview,null);
            ImageView img=(ImageView)V.findViewById(R.id.imageView);
            TextView titre = (TextView)V.findViewById(R.id.titre);
            TextView desc = (TextView)V.findViewById(R.id.discription);
            TextView date = (TextView)V.findViewById(R.id.date);
            TextView ville = (TextView)V.findViewById(R.id.ville);
            TextView prix = (TextView)V.findViewById(R.id.prix);
            img.setImageResource(listItemView.get(position).getImage());
            titre.setText(listItemView.get(position).getTitre());
            desc.setText(listItemView.get(position).getDescription());
            date.setText(listItemView.get(position).getDate());
            ville.setText(listItemView.get(position).getVille());
            prix.setText(listItemView.get(position).getPrix());
            return V;
        }
    }




}
