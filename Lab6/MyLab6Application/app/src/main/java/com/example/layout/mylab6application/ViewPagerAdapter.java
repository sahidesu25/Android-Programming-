package com.example.layout.mylab6application;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.Locale;

/**
 * Created by Sahithi on 6/29/2015.
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {

int count;
    public ViewPagerAdapter(FragmentManager fm, int size)
    {
        super(fm);
        this.count=size;

    }

    public Fragment getItem(int position)
    {
        return RecyclerViewFragment.newInstance(position);
    }

    public int getCount(){return count;}

    public CharSequence getPageTitle(int position)
    {
        Locale l=Locale.getDefault();
        String name;
        switch(position)
        {
            case 0:
                name="Top Selling Movies";
                break;
            case 1:
                name="New Movie Releases";
                break;
            default:
                name="Top Selling Movies";

        }
        return name.toUpperCase(l);


    }

}
