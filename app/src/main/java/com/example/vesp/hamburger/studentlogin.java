package com.example.vesp.hamburger;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.vesp.MainActivity;
import com.example.vesp.R;
import com.example.vesp.studentdashboard.s1;
import com.example.vesp.teacherdashboard.t1;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class studentlogin extends AppCompatActivity {
    TextInputLayout username_var,pass_var;
    Button LoginBtn;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    public static String PREFS_NAME = "myPrefFiles";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studentlogin);
        username_var = findViewById(R.id.stdname);
        pass_var = findViewById(R.id.stdpwd);
        LoginBtn = findViewById(R.id.loginstd);

        LoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username_ = username_var.getEditText().getText().toString();
                String password_ = pass_var.getEditText().getText().toString();

                if (!username_.isEmpty()) {
                    username_var.setError(null);
                    username_var.setErrorEnabled(false);

                    if (!password_.isEmpty()) {
                        pass_var.setError(null);
                        pass_var.setErrorEnabled(false);

                        final String username_data = username_var.getEditText().getText().toString();
                        final String pass_data = pass_var.getEditText().getText().toString();

                        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                        DatabaseReference databaseReference = firebaseDatabase.getReference("student");

                        Query check_username = databaseReference.orderByChild("uname").equalTo(username_data);

                        check_username.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {

                                if (snapshot.exists()) {

                                    username_var.setError(null);
                                    username_var.setErrorEnabled(false);
                                    String pass_check = snapshot.child(username_data).child("ped").getValue(String.class);

                                    if (pass_check.equals(pass_data)) {
                                        pass_var.setError(null);
                                        pass_var.setErrorEnabled(false);
                                        Toast.makeText(getApplicationContext(), "Login Successfully !", Toast.LENGTH_SHORT).show();

                                        SharedPreferences sharedPreferences = getSharedPreferences(studentlogin.PREFS_NAME , MODE_PRIVATE);
                                        SharedPreferences.Editor editor = sharedPreferences.edit();
                                        editor.putBoolean("hasLoggedInStudent" ,true);
                                        editor.commit();

                                        startActivity(new Intent(studentlogin.this , s1.class));
                                        finish();

                                    } else {
                                        pass_var.setError("Password could not match !");
                                    }

                                } else {
                                    username_var.setError("Username does not found !");
                                }

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });


                    } else {
                        pass_var.setError("Please enter password");
                    }

                } else {
                    username_var.setError("Please enter username");
                }


            }
        });


    }
    @Override
    public void onBackPressed() {
        Intent i = new Intent(studentlogin.this, MainActivity.class);
        startActivity(i);
        super.onBackPressed();

    }
}