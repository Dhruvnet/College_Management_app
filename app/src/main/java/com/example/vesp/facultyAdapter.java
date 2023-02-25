package com.example.vesp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class facultyAdapter extends RecyclerView.Adapter<facultyAdapter.FacultyViewAdapter> {

    private Context context;
    private ArrayList<teacher_storing_data> list;

    public facultyAdapter(Context context, ArrayList<teacher_storing_data> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public FacultyViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.teacher_display,parent,false);
        return new facultyAdapter.FacultyViewAdapter(view);


    }

    @Override
    public void onBindViewHolder(@NonNull FacultyViewAdapter holder, int position) {
        teacher_storing_data currentitem = list.get(position);

        holder.name.setText(currentitem.getUsername());
        holder.post.setText(currentitem.getPost());
        holder.course.setText(currentitem.getCourse());
        holder.email.setText(currentitem.getEmail());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class FacultyViewAdapter extends RecyclerView.ViewHolder {
        TextView name,email,post,course;
        public FacultyViewAdapter(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.trnamedisplay);
            email = itemView.findViewById(R.id.tremaildisplay);
            post = itemView.findViewById(R.id.trpostdisplay);
            course = itemView.findViewById(R.id.trcoursedisplay);


        }
    }
}
