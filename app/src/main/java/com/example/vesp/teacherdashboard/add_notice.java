package com.example.vesp.teacherdashboard;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

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

import com.example.vesp.R;
import com.example.vesp.noticedata;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class add_notice extends AppCompatActivity {

    CardView addnotice;
    ImageView noticeimageview;
    EditText noticetitle;
    Button uploadnoticebtn;

    private final int REQ = 1;
    private Bitmap bitmap;

    StorageReference storageReference;
    DatabaseReference reference;
    String downloadurl = "";
    ProgressDialog pd;
    public String title;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_notice);

        storageReference = FirebaseStorage.getInstance().getReference();
        reference = FirebaseDatabase.getInstance().getReference();

        pd = new ProgressDialog(this);

        addnotice = findViewById(R.id.traddnotice);
        noticeimageview = findViewById(R.id.noticeimageview);
        noticetitle = findViewById(R.id.noticetitlename);
        uploadnoticebtn = findViewById(R.id.uploadnoticebtn);

        uploadnoticebtn.setEnabled(false);

        addnotice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opengallery();
            }
        });


        uploadnoticebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (noticetitle.getText().toString().isEmpty()) {
                    noticetitle.setError("Please Enter Title");
                } else if (bitmap == null) {
                    uploaddata();
                } else {
                    uploadImage();
                }
            }
        });
    }

    private void uploadImage() {
        pd.setMessage("Uploading...");
        pd.show();
        ByteArrayOutputStream boas = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, boas);
        byte[] finalimg = boas.toByteArray();
        final StorageReference filePath;
        filePath = storageReference.child("Notice").child(finalimg + "jpg");
        final UploadTask uploadTask = filePath.putBytes(finalimg);
        uploadTask.addOnCompleteListener(add_notice.this, new OnCompleteListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                if (task.isSuccessful()) {
                    uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            filePath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    downloadurl = String.valueOf(uri);
                                    uploaddata();
                                }
                            });
                        }
                    });
                } else {
                    pd.dismiss();
                    Toast.makeText(add_notice.this, "Something went Wrong", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void uploaddata() {
        reference = reference.child("Notice");
        final String uniquekey = reference.push().getKey();

        title = noticetitle.getText().toString();

        Calendar calforDate = Calendar.getInstance();
        SimpleDateFormat currentDate = new SimpleDateFormat("dd-MM-yy");
        String date = currentDate.format(calforDate.getTime());

        Calendar calforTime = Calendar.getInstance();
        SimpleDateFormat currentTime = new SimpleDateFormat("hh:mm a");
        String time = currentTime.format(calforTime.getTime());

        noticedata noticedata = new noticedata(title, downloadurl, date, time);

        reference.child(uniquekey).setValue(noticedata).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                pd.dismiss();
                Toast.makeText(add_notice.this, "Notice Uploaded", Toast.LENGTH_SHORT).show();
                noticetitle.setText("");
                noticeimageview.setVisibility(View.GONE);
                uploadnoticebtn.setEnabled(false);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                pd.dismiss();
                Toast.makeText(add_notice.this, "Something went Wrong !", Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void opengallery() {
        Intent pickimage = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
       startActivityForResult(pickimage, REQ);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ && resultCode == RESULT_OK){
            uploadnoticebtn.setEnabled(true);
            noticeimageview.setVisibility(View.VISIBLE);
            Uri uri = data.getData();
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                noticeimageview.setImageBitmap(bitmap);
            }

        }
    }
