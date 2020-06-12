package com.example.pfe_test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailAnnonce extends AppCompatActivity {

    private ImageView image;
    private TextView titre, description, prix, date, ville;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_annonce);

        image =(ImageView) findViewById(R.id.imgview);
        titre =(TextView) findViewById(R.id.tv_titre);
        description =(TextView) findViewById(R.id.tv_descr);
        prix =(TextView) findViewById(R.id.tv_prix);
        date =(TextView) findViewById(R.id.tv_date);
        ville =(TextView) findViewById(R.id.tv_ville);

        Bundle bundle = getIntent().getExtras();
        if (bundle!=null){

            titre.setText(bundle.getString("titre"));
            description.setText(bundle.getString("description"));
            prix.setText(bundle.getString("prix"));
            date.setText(bundle.getString("date"));
            ville.setText(bundle.getString("ville"));

            Glide.with(this)
                    .load(bundle.getString("image"))
                    .into(image);

        }

    }
}