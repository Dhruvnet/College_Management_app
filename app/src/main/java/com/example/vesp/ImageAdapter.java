package com.example.vesp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {

    private Context thisContext;
    public Integer[] mThumbIds;
//    private LayoutInflater layoutInflater;


    public ImageAdapter(Context thisContext, Integer[] items){
        this.thisContext = thisContext;
        this.mThumbIds = items;
//        this.layoutInflater = (LayoutInflater) thisContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return mThumbIds.length;
    }

    @Override
    public Object getItem(int i) {
        return mThumbIds[i];
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ImageView imgView;
        if(view == null) {
            imgView = new ImageView(thisContext);
            imgView.setImageResource(mThumbIds[i]);
            imgView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imgView.setLayoutParams(new GridView.LayoutParams(350, 350));
        }
        else{
            imgView = (ImageView) view;
        }
        imgView.setImageResource(mThumbIds[i]);
        return imgView;
    }
}
