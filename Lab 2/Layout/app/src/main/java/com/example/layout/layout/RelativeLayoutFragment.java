package com.example.layout.layout;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A placeholder fragment containing a simple view.
 */
public class RelativeLayoutFragment extends Fragment {
    public static final String ARG_OPTION="argument_option";
    public static RelativeLayoutFragment newInstance(int option)
    {
        RelativeLayoutFragment fragment3=new RelativeLayoutFragment();
        Bundle args=new Bundle();
        args.putInt(ARG_OPTION,option);
        fragment3.setArguments(args);


        return fragment3;

    }

    public RelativeLayoutFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.relativelayoutfragment, container, false);
    }
}
