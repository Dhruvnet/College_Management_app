package com.example.vesp.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.vesp.R;
import com.example.vesp.fragment_adapter;

public class about_fragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_about_fragment, container, false);
        ViewPager vpn = view.findViewById(R.id.slideshow);
        vpn.setAdapter(new fragment_adapter(getActivity().getSupportFragmentManager()));
        TextView email = view.findViewById(R.id.email);
        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto","vesp.anyhelp@gmail.com",null
                ));
                startActivity(intent);
            }
        });
        return view;
    }
}