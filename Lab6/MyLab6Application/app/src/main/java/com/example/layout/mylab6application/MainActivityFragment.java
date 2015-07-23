package com.example.layout.mylab6application;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {
    public static final String ARG_OPTION="argument_option";
    private OnButtonClickListener buttonListener;
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
        View view= inflater.inflate(R.layout.fragment_main, container, false);
        Button AboutMe=(Button)view.findViewById(R.id.aboutmebutton);
        AboutMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonListener.OnButtonClick(v);
            }
        });

        Button Master=(Button)view.findViewById(R.id.masterbutton);
        Master.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonListener.OnButtonClick(v);
            }
        });

        Button viewPager=(Button)view.findViewById(R.id.viewpager);
        viewPager.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                buttonListener.OnButtonClick(v);
            }
        });

         Button recycler=(Button)view.findViewById(R.id.Recycler);
        recycler.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                buttonListener.OnButtonClick(v);
            }
        });






        return view;
    }



    public interface OnButtonClickListener
    {
        public void OnButtonClick(View v);

    }
    public void onAttach(Activity activity)
    {
        super.onAttach(activity);
        try
        {
            buttonListener=(OnButtonClickListener)activity;
        }catch(ClassCastException e)
        {

        }
    }
}


