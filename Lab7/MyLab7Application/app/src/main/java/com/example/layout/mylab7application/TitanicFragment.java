package com.example.layout.mylab7application;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A placeholder fragment containing a simple view.
 */
public class TitanicFragment extends Fragment {
    public static final String ARG_OPTION="argument_option";
    public static TitanicFragment newInstance(int option)
    {
        TitanicFragment fragment=new TitanicFragment();
        Bundle args=new Bundle();
        args.putInt(ARG_OPTION,option);
        fragment.setArguments(args);
        return fragment;

    }

    public TitanicFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.titanic, container, false);
    }
}


