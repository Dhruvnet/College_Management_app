package com.example.vesp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class notesAdapter extends RecyclerView.Adapter<notesAdapter.notesViewHolder> {

    Context context;
    private List<notesData> list;

    public notesAdapter(Context context, List<notesData> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public notesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

       View view = LayoutInflater.from(context).inflate(R.layout.notes_item_layout,parent,false);
        return new notesViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull notesViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.notesName.setText(list.get(position).getName());

        holder.itemView.setOnClickListener( (view) -> {
            Toast.makeText(context,list.get(position).getName(),Toast.LENGTH_SHORT).show();
        });
        holder.notesDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(list.get(position).getUrl()));
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class notesViewHolder extends RecyclerView.ViewHolder {

        private TextView notesName;
        private ImageView notesDownload;

        public notesViewHolder(@NonNull View itemView) {
            super(itemView);
            notesName = itemView.findViewById(R.id.notesName);
            notesDownload = itemView.findViewById(R.id.notesDownload);
        }
    }
}
