package com.example.vesp.studentdashboard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.vesp.R;
import com.example.vesp.notesAdapter;
import com.example.vesp.notesData;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class view_notes extends AppCompatActivity {
    final String TAG = "VIEW_NOTES";
    private RecyclerView notesRecycler;
    private DatabaseReference reference;
    private List<notesData> list;
    private notesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_notes);

        notesRecycler = findViewById(R.id.notesRecycler);
        reference = FirebaseDatabase.getInstance().getReference().child("UploadPDF");

        getData();
        
    }

    private void getData() {
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list = new ArrayList<>();
//              for(DataSnapshot snapshot : dataSnapshot.getChildren()){
//                  Log.d(TAG, "onDataChange: " + snapshot.getValue().toString());
//                  notesData data = snapshot.getValue(notesData.class);
//                  Log.d(TAG, "onDataChange: " + data);
//                  String name = snapshot.child("name").getValue(String.class);
//
//                  notesData data = new notesData(snapshot.child("name").getValue());
//                  notesData data = new notesData(snapshot.child("name").getValue());
//                  notesData data = new notesData();
//                  snapshot.getValue(notesData.class);
//                  Log.d(TAG, "onDataChange: " + data.getUrl());
//                  list.add(data);
//               }

                for(DataSnapshot snapshot: dataSnapshot.getChildren()){
                    Log.d(TAG, "onDataChange: " + snapshot.getValue().toString());
                    notesData data = new notesData();
                    String str = snapshot.getValue().toString();
                    boolean isReading = false;
                    String value = "";
                    for(int i = 0; i < str.length(); i++){
                        char ch = str.charAt(i);

                        if(isReading){
                            if(ch == ','){
                                isReading = false;
                                data.setName(value);
                                Log.d(TAG, "onDataChange: " + value);
                                value = "";
                            }   else if(ch == '}'){
                                data.setUrl(value);
                                Log.d(TAG, "onDataChange: " + value);
                                value = "";
                                isReading = false;
                            }   else {
                                value += ch;
                            }
                        }
                        if(ch == '=') {
                            isReading = true;
                        }
                    }
                    list.add(data);
                }
                adapter = new notesAdapter(view_notes.this, list);
                notesRecycler.setLayoutManager(new LinearLayoutManager(view_notes.this));
                notesRecycler.setAdapter(adapter);
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(view_notes.this,"Database Error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}