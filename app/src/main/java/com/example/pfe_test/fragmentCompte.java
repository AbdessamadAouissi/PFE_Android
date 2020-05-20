package com.example.pfe_test;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class fragmentCompte extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_compte,container,false);
        Button btnVersConnecter = v.findViewById(R.id.btn_vers_connecter);
        btnVersConnecter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inscrireIntent = new Intent(fragmentCompte.this.getContext(), LoginActivity.class);
                startActivity(inscrireIntent);
            }
        });
        return v;
    }
}
