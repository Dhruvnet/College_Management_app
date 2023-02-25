package com.example.vesp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.vesp.fragments.announcement_fragment;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class NoticeAdapter extends RecyclerView.Adapter<NoticeAdapter.NoticeViewAdapter> {

    private Context context;
    private ArrayList<noticedata> list;

    public NoticeAdapter(Context context, ArrayList<noticedata> list) {
        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override
    public NoticeViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.notice_display,parent,false);
        return new NoticeViewAdapter(view);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onBindViewHolder(@NonNull NoticeViewAdapter holder, int position) {
        noticedata currentitem = list.get(position);


        holder.title.setText(currentitem.getTitle());
        Picasso.get().load(currentitem.getImage()).into(holder.notice);
        holder.date.setText(currentitem.getDate());
        holder.time.setText(currentitem.getTime());


    }

    public class NoticeViewAdapter extends RecyclerView.ViewHolder {
        TextView title,date,time;
        ImageView notice;
        public NoticeViewAdapter(@NonNull View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.noticedisplaydate);
            time = itemView.findViewById(R.id.noticedisplaytime);
            title = itemView.findViewById(R.id.noticedisplaytitle);
            notice = itemView.findViewById(R.id.noticedisplayimg);
        }
    }
}
