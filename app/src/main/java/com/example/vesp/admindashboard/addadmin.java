package com.example.vesp.admindashboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.vesp.R;
import com.example.vesp.Storingdata;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class addadmin extends AppCompatActivity {
    TextInputLayout username, password, email;
    Button add;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addadmin);
        username = findViewById(R.id.adminname);
        email = findViewById(R.id.adminemail);
        password = findViewById(R.id.adminpwd);
        add = findViewById(R.id.addbtn);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name_ = email.getEditText().getText().toString();
                String userSU_ = username.getEditText().getText().toString();
                String pass_ = password.getEditText().getText().toString();

                if (!name_.isEmpty()) {
                    email.setError(null);
                    email.setErrorEnabled(false);
                    if (!userSU_.isEmpty()) {
                        username.setError(null);
                        username.setErrorEnabled(false);
                            if (!pass_.isEmpty()) {
                                password.setError(null);
                                password.setErrorEnabled(false);

                                firebaseDatabase = FirebaseDatabase.getInstance();
                                reference = firebaseDatabase.getReference("admin");

                                String name_s = email.getEditText().getText().toString();
                                String userSU_s = username.getEditText().getText().toString();
                                String pass_s = password.getEditText().getText().toString();

                                Storingdata storingData = new Storingdata(name_s , userSU_s  ,pass_s);
                                reference.child(userSU_s).setValue(storingData);

                                Toast.makeText(getApplicationContext(), "Admin Added Succssfully !", Toast.LENGTH_LONG).show();

                                startActivity(new Intent(addadmin.this , d1.class));
                                finish();

                            } else {
                                password.setError("Please enter password");
                            }

                    } else {
                        username.setError("Please enter username");
                    }
                } else {
                    email.setError("Please enter full name");
                }

            }
        });

    }
}