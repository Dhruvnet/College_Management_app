package com.example.vesp.teacherdashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.vesp.R;
import com.example.vesp.student_storing_data;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class add_student extends AppCompatActivity {
    TextInputLayout username, password, email;
    Button add;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);
        username = findViewById(R.id.studentname);
        email = findViewById(R.id.studentemail);
        password = findViewById(R.id.studentpwd);
        add = findViewById(R.id.addbtnstd);

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
                            reference = firebaseDatabase.getReference("student");

                            String name_s = email.getEditText().getText().toString();
                            String userSU_s = username.getEditText().getText().toString();
                            String pass_s = password.getEditText().getText().toString();

                            student_storing_data storingData = new student_storing_data(name_s , userSU_s  ,pass_s);
                            reference.child(userSU_s).setValue(storingData);

                            Toast.makeText(getApplicationContext(), "Student Added Succssfully !", Toast.LENGTH_LONG).show();

                            startActivity(new Intent(add_student.this , t1.class));
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