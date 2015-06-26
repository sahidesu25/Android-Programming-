package com.example.layout.layout;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {
    public static final String ARG_OPTION="argument_option";
    public static MainActivityFragment newInstance(int option)
    {
        MainActivityFragment fragment=new MainActivityFragment();
        Bundle args=new Bundle();
        args.putInt(ARG_OPTION,option);
        fragment.setArguments(args);
        return fragment;

    }

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }
}


