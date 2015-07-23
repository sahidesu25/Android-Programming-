package com.example.layout.layout;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.ImageView;


/**
 * A placeholder fragment containing a simple view.
 */
public class Seekbar extends Fragment {
    public static final String ARG_OPTION="argument_option";
    public static Seekbar newInstance(int option)
    {
        Seekbar fragment=new Seekbar();
        Bundle args=new Bundle();
        args.putInt(ARG_OPTION,option);
        fragment.setArguments(args);



        return fragment;

    }

    public Seekbar() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View  view= inflater.inflate(R.layout.seekbarlayout, container, false);
        SeekBarFragment seekBarOperations = new SeekBarFragment((SeekBar) view.findViewById(R.id.actionSeekBar),
                (ImageView)view.findViewById(R.id.imageSeekBar));
        seekBarOperations.attachListenersToSeekBar();
        return view;
    }
}
