package com.example.vesp.fragments;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.vesp.ClickedItemActivity;
import com.example.vesp.ImageAdapter;
import com.example.vesp.MainActivity;
import com.example.vesp.R;

import java.util.ArrayList;
import java.util.List;


public class gallery_fragment extends Fragment {

    GridView gridView, gridViewAnvesh, gridViewSpurhti;
    public Integer[] ImgArray = {
            R.drawable.csi_img1,R.drawable.csi_img2,R.drawable.csi_img4,R.drawable.csi_img5
    };

    public Integer[] AnveshArray = {
            R.drawable.anvesh_img1,R.drawable.anvesh_img2,R.drawable.anvesh_img3,R.drawable.anvesh_img4,
            R.drawable.anvesh_img5, R.drawable.anvesh_img6
    };

    public Integer[] SpurhtiArray = {
            R.drawable.csi_img1,R.drawable.csi_img2,R.drawable.csi_img4,R.drawable.csi_img5,
             R.drawable.faculty
    };
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_gallery_fragment, container, false);
        List<SlideModel> slideModels = new ArrayList<>();
        ImageSlider is = view.findViewById(R.id.slider);
        slideModels.add(new SlideModel("https://main.ves.ac.in/polytechnic/wp-content/uploads/sites/8/2015/11/DSC_3495.jpg",ScaleTypes.FIT));
        slideModels.add(new SlideModel("https://main.ves.ac.in/polytechnic/wp-content/uploads/sites/8/2019/01/IMG-20191129-WA0018--960x800_c.jpg", ScaleTypes.FIT));
        slideModels.add(new SlideModel("https://main.ves.ac.in/polytechnic/wp-content/uploads/sites/8/2015/11/DSC0321.jpg",ScaleTypes.FIT));
        slideModels.add(new SlideModel("https://main.ves.ac.in/polytechnic/wp-content/uploads/sites/8/2015/11/DSC0388.jpg",ScaleTypes.FIT));
        slideModels.add(new SlideModel("https://main.ves.ac.in/polytechnic/wp-content/uploads/sites/8/2015/11/DSC0375.jpg",ScaleTypes.FIT));

        is.setImageList(slideModels, ScaleTypes.FIT);

        //Anvesh Grid
        gridViewAnvesh = view.findViewById(R.id.AnveshGrid);
        gridViewAnvesh.setAdapter(new ImageAdapter(requireActivity(),AnveshArray));
        gridViewAnvesh.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int selectedImage = AnveshArray[i];
                startActivity(new Intent(gallery_fragment.this.getActivity(),ClickedItemActivity.class).putExtra("image",selectedImage));
            }
        });


        //Spurhti Grid
        gridViewSpurhti = view.findViewById(R.id.SpurhtiGrid);
        gridViewSpurhti.setAdapter(new ImageAdapter(requireActivity(),SpurhtiArray));
        gridViewSpurhti.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int selectedImage = SpurhtiArray[i];
                startActivity(new Intent(gallery_fragment.this.getActivity(),ClickedItemActivity.class).putExtra("image",selectedImage));
            }
        });


        //CSI Grid
        gridView = view.findViewById(R.id.CSIGrid);
        gridView.setAdapter(new ImageAdapter(requireActivity(),ImgArray));
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int selectedImage = ImgArray[i];
                startActivity(new Intent(gallery_fragment.this.getActivity(),ClickedItemActivity.class).putExtra("image",selectedImage));
            }
        });

        return view;




    }
}