package com.example.layout.myapplication;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A placeholder fragment containing a simple view.
 */
public class Main_ActivityFragment extends Fragment {
    public static final String ARG_OPTION="argument_option";
    private OnButtonClickListener buttonListener;

    public static Main_ActivityFragment newInstance(int option)
    {
        Main_ActivityFragment fragment=new Main_ActivityFragment();
        Bundle args=new Bundle();
        args.putInt(ARG_OPTION,option);
        fragment.setArguments(args);
        return fragment;

    }


    public Main_ActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_main_, container, false);
        Button button=(Button)view.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonListener.OnButtonClick(v);



            }
        });
        Button button6=(Button)view.findViewById(R.id.button6);
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonListener.OnButtonClick(v);


            }
        });
        Button button7=(Button)view.findViewById(R.id.button7);
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonListener.OnButtonClick(v);


            }
        });
        Button button8=(Button)view.findViewById(R.id.button8);
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonListener.OnButtonClick(v);

            }
        });
        Button button9=(Button)view.findViewById(R.id.button9);
        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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



