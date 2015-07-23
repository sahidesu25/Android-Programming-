package com.example.layout.layout;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.GridView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Sahithi on 6/7/2015.
 */
public class GirdViewFragment extends Fragment {
    public static final String ARG_OPTION = "argument_option";
    public MovieData movieData = new MovieData();
    public List<Integer> selectedmoviesList;

    public static GirdViewFragment newInstance(int option) {
        GirdViewFragment fragment = new GirdViewFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_OPTION, option);
        fragment.setArguments(args);


        return fragment;

    }


    public GirdViewFragment() {
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=null;
        view=inflater.inflate(R.layout.gridviewlayout, container, false);
        GridView gridview=(GridView)view.findViewById(R.id.gridview);
        final MyImageAdapter myImageAdapter=new MyImageAdapter(getActivity(),movieData.getMoviesList());
        gridview.setAdapter(myImageAdapter);

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final HashMap<String, ?> item = (HashMap<String, ?>) parent.getItemAtPosition(position);
                final String name = (String) item.get("name");
                Toast.makeText(getActivity(), "Selected" + name, Toast.LENGTH_SHORT).show();



            }
        });



        return  view;
    }
    }

