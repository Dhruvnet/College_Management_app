package com.example.vesp.teacherdashboard;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.vesp.R;
import com.example.vesp.putpdf;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.Objects;

public class add_notes extends AppCompatActivity {

    CardView imageView ;
    Button button ;
    EditText editText ;

    StorageReference storageReference ;
    DatabaseReference databaseReference ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_notes);

        imageView = findViewById(R.id.img);
        editText = findViewById(R.id.editText);
        button = findViewById(R.id.btn);

        storageReference = FirebaseStorage.getInstance().getReference();
        databaseReference = FirebaseDatabase.getInstance().getReference("UploadPDF");

        button.setEnabled(false);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                selectPDF();

            }
        });


    }

    private void selectPDF() {

        Intent intent = new Intent();
        intent.setType("application/pdf");
        intent.setAction(intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent , "PDF FILE SELECT NOW" ), 12);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==12 && resultCode==RESULT_OK && data!= null && data.getData()!=null)
        {
            button.setEnabled(true);
            editText.setText(data.getDataString().substring(data.getDataString().lastIndexOf("/")+1));

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    uploadtofirebase(data.getData());
                }
            });
        }
    }

    private void uploadtofirebase(Uri data) {

        final ProgressDialog  progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("File is loading..");
        progressDialog.show();

        StorageReference reference = storageReference.child("UploadPDF"+System.currentTimeMillis()+".pdf");

        reference.putFile(data).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                while (!uriTask.isComplete()) ;
                Uri uri = uriTask.getResult();


                putpdf putPDF = new putpdf(editText.getText().toString() , uri.toString());
                databaseReference.child(databaseReference.push().getKey()).setValue(putPDF)   ;
                Toast.makeText(add_notes.this, "File Upload", Toast.LENGTH_SHORT).show();



            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {

                double progress = (100.0* snapshot.getBytesTransferred())/snapshot.getTotalByteCount();
                progressDialog.setMessage("File Uploading...." + (int) progress +"%");
                if (progress == 100)
                {
                    Toast.makeText(add_notes.this, "DONE", Toast.LENGTH_SHORT).show();
                    progressDialog.hide();
                }

            }
        });
    }
}