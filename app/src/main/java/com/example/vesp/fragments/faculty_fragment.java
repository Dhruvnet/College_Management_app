package com.example.vesp.fragments;

import static android.service.controls.ControlsProviderService.TAG;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vesp.NoticeAdapter;
import com.example.vesp.R;
import com.example.vesp.facultyAdapter;
import com.example.vesp.noticedata;
import com.example.vesp.teacher_storing_data;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class faculty_fragment extends Fragment {
    RecyclerView recyclerView;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    ArrayList<teacher_storing_data> list;
    facultyAdapter recycler_adapter;

        @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_faculty_fragment, container, false);

            recyclerView = view.findViewById(R.id.facultyRecycler);

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());

            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setHasFixedSize(true);
            databaseReference = FirebaseDatabase.getInstance().getReference().child("teacher");


            getFaculty();

        return view;
    }

    private void getFaculty() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list = new ArrayList<>();
                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                    Log.d(TAG, "onDataChange: " + snapshot.getValue().toString());
                    teacher_storing_data data = new teacher_storing_data();
                    String str = snapshot1.getValue().toString();
                    boolean isReading = false;
                    int counter = 0;
                    String value = "";
                    for(int i = 0; i < str.length(); i++) {
                        char ch = str.charAt(i);
                        if(isReading){
                            if(ch == ',' && counter == 3){
                                isReading = false;
                                data.setEmail(value);
                                // Log.d(TAG, "onDataChange: " + value);
                                Log.d(TAG, "onDataChange: "+ counter);
                                value = "";
                                counter++;
                            }
                            else if(ch == ',' && counter == 1){
                                data.setPost(value);
                                //Log.d(TAG, "onDataChange: " + value);
                                Log.d(TAG, "onDataChange: "+ counter);
                                value = "";
                                isReading = false;
                                counter++;
                            }
                            else if(ch == ','){
                                data.setCourse(value);
                                // Log.d(TAG, "onDataChange: " + value);
                                Log.d(TAG, "onDataChange: "+ counter);
                                value = "";
                                isReading = false;
                                counter++;
                            }
                            else if(ch == '}'){
                                data.setUsername(value);
                                Log.d(TAG, "onDataChange: " + value);
                                value = "";
                                isReading = false;
                            }  else {
                                value += ch;
                            }
                        }
                        if(ch == '=' ) {
                            isReading = true;
                        }
                    }
                    list.add(data);
                }
                recycler_adapter = new facultyAdapter(getContext(),list);
                recycler_adapter.notifyDataSetChanged();
                recyclerView.setAdapter(recycler_adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
            }
        });

    }
}