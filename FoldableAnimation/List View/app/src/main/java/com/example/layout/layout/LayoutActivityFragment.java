package com.example.layout.layout;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A placeholder fragment containing a simple view.
 */
public class LayoutActivityFragment extends Fragment {
    public static final String ARG_OPTION="argument_option";
    public static LayoutActivityFragment newInstance(int option)
    {
        LayoutActivityFragment fragment1=new LayoutActivityFragment();
        Bundle args=new Bundle();
        args.putInt(ARG_OPTION,option);
        fragment1.setArguments(args);


        return fragment1;

    }

    public LayoutActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.linearlayoutfragment, container, false);
    }
}
