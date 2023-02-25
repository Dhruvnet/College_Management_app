package com.example.vesp.admindashboard;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.vesp.R;
import com.example.vesp.Storingdata;
import com.example.vesp.noticedata;
import com.example.vesp.teacher_storing_data;
import com.example.vesp.teacherdashboard.add_notice;
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

public class addteacher extends AppCompatActivity {
    TextInputLayout emailid,uname,postn,pwd;
    Spinner sp;
    Button btn;
    CardView img;
    ImageView displayimg;
    String[] courses = {
            "Select Courses"," CO " , " ME " , " EJ " , " CE "
            , " EE " , " IE " , " AO "
    };
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    StorageReference storageReference;
    private final int REQ = 1;
    private Bitmap bitmap;
    String downloadurl = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addteacher);

        emailid = findViewById(R.id.tremail);
        uname = findViewById(R.id.trname);
        postn = findViewById(R.id.trpost);
        pwd = findViewById(R.id.trpwd);
        btn = findViewById(R.id.addbtntr);
        sp = findViewById(R.id.coursesspinner);
//        img = findViewById(R.id.trphoto);
//        displayimg = findViewById(R.id.trphotoview);


        ArrayAdapter ad = new ArrayAdapter(this,R.layout.spinneritem,courses);
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(ad);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email_ = emailid.getEditText().getText().toString();
                String username_ = uname.getEditText().getText().toString();
                String post_ = postn.getEditText().getText().toString();
                String spn = sp.getSelectedItem().toString();
                String password_ = pwd.getEditText().getText().toString();

                if (!email_.isEmpty()){
                    emailid.setError(null);
                    emailid.setErrorEnabled(false);
                    if (!username_.isEmpty()){
                        uname.setError(null);
                        uname.setErrorEnabled(false);
                        if (!post_.isEmpty()){
                            postn.setError(null);
                            postn.setErrorEnabled(false);
                            if (spn=="Select Courses"){
                                Toast.makeText(addteacher.this,"Please Select Course",Toast.LENGTH_SHORT).show();
                            }
                            if (!password_.isEmpty()){
                                pwd.setError(null);
                                pwd.setErrorEnabled(false);

                                firebaseDatabase = FirebaseDatabase.getInstance();
                                databaseReference = firebaseDatabase.getReference("teacher");
                                storageReference = FirebaseStorage.getInstance().getReference();

                                String email = emailid.getEditText().getText().toString();
                                String username = uname.getEditText().getText().toString();
                                String post = postn.getEditText().getText().toString();
                                String _spn = sp.getSelectedItem().toString();
                                String password = pwd.getEditText().getText().toString();


                                teacher_storing_data storingData = new teacher_storing_data(email , username  ,post,_spn,password);
                                databaseReference.child(spn).child(username).setValue(storingData);

                                Toast.makeText(getApplicationContext(), "Teacher Added Succssfully !", Toast.LENGTH_LONG).show();



                                startActivity(new Intent(addteacher.this , d1.class));
                                finish();
                            }
                            else{
                                pwd.setError("Enter proper password");
                            }

                        }
                        else{
                            postn.setError("Enter proper post");
                        }
                    }
                    else{
                        uname.setError("Enter proper name");
                    }

                }
                else{
                    emailid.setError("Enter proper email");
                }

            }
        });



    }
}