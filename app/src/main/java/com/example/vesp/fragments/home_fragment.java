package com.example.vesp.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.vesp.R;

import java.util.ArrayList;


public class home_fragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_home_fragment, container, false);

        ImageSlider imgslidere = view.findViewById(R.id.image_slider);

        ArrayList<SlideModel> slideModels = new ArrayList<>();

        slideModels.add(new SlideModel("https://main.ves.ac.in/polytechnic/wp-content/uploads/sites/8/2015/11/DSC_3495.jpg", ScaleTypes.FIT));
        slideModels.add(new SlideModel("https://main.ves.ac.in/polytechnic/wp-content/uploads/sites/8/2015/11/IMG-20191129-WA0018.jpg", ScaleTypes.FIT));
        slideModels.add(new SlideModel("https://main.ves.ac.in/polytechnic/wp-content/uploads/sites/8/2015/11/DSC0220.jpg", ScaleTypes.FIT));
        slideModels.add(new SlideModel("https://main.ves.ac.in/polytechnic/wp-content/uploads/sites/8/2015/11/DSC0321.jpg", ScaleTypes.FIT));


        imgslidere.setImageList(slideModels, ScaleTypes.FIT);

        return view;

    }

    private void gotoUrl(String s) {
        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW , uri));
    }
}