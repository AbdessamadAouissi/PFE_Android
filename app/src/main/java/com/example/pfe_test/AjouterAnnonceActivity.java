package com.example.pfe_test;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.pfe_test.modele.annonceItem;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.UUID;

public class AjouterAnnonceActivity extends AppCompatActivity {

    private Button btn_upload, btn_choose;
    private ImageView imageView;
    private EditText tirte,description,prix,ville;
    private Uri uri;
    private String UrlImage;
    private FirebaseStorage storage;
    private StorageReference storageReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter_annonce);

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        imageView=(ImageView) findViewById(R.id.addImg);
        tirte=(EditText) findViewById(R.id.addTitre);
        description=(EditText) findViewById(R.id.addDesc);
        prix=(EditText) findViewById(R.id.addPrix);
        ville=(EditText) findViewById(R.id.addVille);


        btn_choose=(Button) findViewById(R.id.choose_btn);
        btn_upload=(Button) findViewById(R.id.upload_btn);

        btn_choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseImage();
            }
        });

        btn_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadImage();
            }
        });


    }

    private void chooseImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Select Image"),1);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null){

            uri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
                imageView.setImageURI(uri);

            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    private void uploadImage() {

        if(uri != null){
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading ...");
            progressDialog.show();

            StorageReference reference = storageReference.child("image/"+ UUID.randomUUID().toString());

            reference.putFile(uri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                            while (!uriTask.isComplete());
                            UrlImage = uriTask.getResult().toString();
                            uploadDataOfAnnonce();

                            progressDialog.dismiss();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                                double progres = (100.0*taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount());
                                progressDialog.setMessage("Uploaded"+(int)progres + "%");
                        }
                    });

        }
    }

    private void uploadDataOfAnnonce(){

        Calendar calendar = Calendar.getInstance();
        String DateAnnonce = calendar.get(Calendar.DAY_OF_MONTH)+"/"+calendar.get(Calendar.MONTH)+"/"+calendar.get(Calendar.YEAR);



        annonceItem annonce = new annonceItem(
                UrlImage,
                tirte.getText().toString(),
                description.getText().toString(),
                prix.getText().toString(),
                ville.getText().toString(),
                DateAnnonce
        );

        String CourrentDate = DateFormat.getDateTimeInstance()
                .format(Calendar.getInstance().getTime());

        FirebaseDatabase.getInstance().getReference("annance")
                .child(CourrentDate).setValue(annonce).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if (task.isSuccessful()){
                    Toast.makeText(AjouterAnnonceActivity.this, "donner Uploaded", Toast.LENGTH_SHORT).show();
                    finish();
                }


            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(AjouterAnnonceActivity.this, "Uploaded Failed\n" + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }


}