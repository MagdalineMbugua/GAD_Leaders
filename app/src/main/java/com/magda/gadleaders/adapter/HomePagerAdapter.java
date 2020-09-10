package com.magda.gadleaders.adapter;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;


public class HomePagerAdapter extends FragmentPagerAdapter {
    private ArrayList <Fragment> addHomeFragments = new ArrayList<>();
    private ArrayList <String> addHomeTabTitles= new ArrayList<>();

    public HomePagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
         return addHomeFragments.get(position);
    }

    @Override
    public int getCount() {
        return addHomeTabTitles.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return addHomeTabTitles.get(position);
    }
    public void addFragment (Fragment fragment, String title){
        addHomeTabTitles.add(title);
        addHomeFragments.add(fragment);
    }
}
