package com.example.layout.mylab6application;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.HashMap;
import java.util.Locale;

/**
 * Created by Sahithi on 6/30/2015.
 */
public class DetailPagerAdapter extends FragmentPagerAdapter {
    int count;
    MovieDataJsonLocal movieData;
    public DetailPagerAdapter(FragmentManager fm, int size,MovieDataJsonLocal movieData)
    {
        super(fm);
        this.count=size;
        this.movieData=movieData;


    }

    public Fragment getItem(int position)
    {
        return MovieInfoFragment.newInstance(position,movieData.getItem(position));
    }

    public int getCount(){return count;}

    public CharSequence getPageTitle(int position)
    {
        Locale l=Locale.getDefault();
        HashMap<String,?> movie=(HashMap<String,?>)movieData.getItem(position);
        String name=(String)movie.get("name");

        return name.toUpperCase(l);


    }

}
