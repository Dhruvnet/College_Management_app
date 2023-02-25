package com.example.vesp;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.vesp.slideshow.ce;
import com.example.vesp.slideshow.co;
import com.example.vesp.slideshow.ee;
import com.example.vesp.slideshow.ej;
import com.example.vesp.slideshow.in;
import com.example.vesp.slideshow.me;

public class fragment_adapter extends FragmentPagerAdapter {
    public fragment_adapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: return new co();
            case 1: return new me();
            case 2: return new ee();
            case 3: return new ej();
            case 4: return new ce();
            case 5: return new in();
            default: return new co();
        }
    }

    @Override
    public int getCount() {
        return 6;
    }
}
