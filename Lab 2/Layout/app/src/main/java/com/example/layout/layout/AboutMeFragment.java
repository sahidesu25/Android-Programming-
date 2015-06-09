package com.example.layout.layout;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A placeholder fragment containing a simple view.
 */
public class AboutMeFragment extends Fragment {
    public static final String ARG_OPTION="argument_option";
    public static AboutMeFragment newInstance(int option)
    {
        AboutMeFragment fragment2=new AboutMeFragment();
        Bundle args=new Bundle();
        args.putInt(ARG_OPTION,option);
        fragment2.setArguments(args);



        return fragment2;

    }

    public AboutMeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.myselffragment, container, false);
    }
}


