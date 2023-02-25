package com.example.vesp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.core.Context;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class recycler_adapter_notice extends RecyclerView.Adapter<recycler_adapter_notice.ViewHolder> {

    Context context;
    ArrayList<noticedata> array2;
    ViewHolder holder;


    public recycler_adapter_notice(Context context, ArrayList<noticedata> array2) {
        this.context = context;
        this.array2 = array2;
    }

    public recycler_adapter_notice(android.content.Context context, ArrayList<noticedata> array) {
    }


    @NonNull
    @Override
    public recycler_adapter_notice.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notice_display, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(array2.get(position).getTitle());
        Picasso.get().load(array2.get(position).getImage()).into(holder.notice);

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView notice;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.noticedisplaytitle);
            notice = itemView.findViewById(R.id.noticeimageview);

        }
    }
}
