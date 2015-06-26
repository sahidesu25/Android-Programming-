package com.example.layout.layout;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Sahithi on 6/13/2015.
 */
public class RecyclerViewFragment extends Fragment {
    public static final String ARG_OPTION = "argument_option";
    public MovieDataJsonLocal movieData;
    RecyclerAdapter recyclerAdapter;
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    private OnListItemSelectedListener mlistener;
    int counter;



    public static RecyclerViewFragment newInstance(int option) {
        RecyclerViewFragment fragment = new RecyclerViewFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_OPTION, option);
        fragment.setArguments(args);


        return fragment;

    }
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        movieData = new MovieDataJsonLocal();
        //Log.d(TAG, this + ": onCreate()");
        if(savedInstanceState==null)
        {
          counter=0;
        }
        else
        {
            counter=savedInstanceState.getInt("counter");
        }
        setRetainInstance(true);
    }
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState)
    {
        savedInstanceState.putInt("counter", counter);
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            // Restore last state for checked position.
            counter = savedInstanceState.getInt("counter", 0);
        }
    }


    public RecyclerViewFragment() {

    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setRetainInstance(true);
        movieData.loadLocalMovieDataJson(getActivity());
        List<Map<String,?>> moviesList=movieData.getMoviesList();
         View view=inflater.inflate(R.layout.recycleviewfragment,container,false);
         recyclerView=(RecyclerView)view.findViewById(R.id.recycler1);
        recyclerView.setHasFixedSize(true);
         layoutManager=new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

         recyclerAdapter=new RecyclerAdapter(getActivity(),movieData.getMoviesList());
        recyclerView.setAdapter(recyclerAdapter);
        recyclerAdapter.setOnItemClickListener(new RecyclerAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View v, int position) {
                HashMap<String, ?> movie = movieData.getItem(position);
                mlistener.OnListItemSelected(position, movie);
            }
            @Override
            public void OnItemLongClick(View v, int position) {
                recyclerAdapter.duplicateItem(position);
                recyclerAdapter.notifyItemInserted(position);
            }

        });

        Button selectAll=(Button) view.findViewById(R.id.selectall);
        selectAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < recyclerAdapter.getItemCount(); i++) {
                    HashMap<String, Boolean> selectoritem = (HashMap<String, Boolean>) recyclerAdapter.getItemAtPosition(i);
                    selectoritem.put("selection", true);


                }
                recyclerAdapter.notifyDataSetChanged();
            }
        });
        Button ClearAll=(Button)view.findViewById(R.id.clearall);
        ClearAll.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                for (int i = 0; i < recyclerAdapter.getItemCount(); i++)

                {
                    HashMap<String, Boolean> selectoritem = (HashMap<String, Boolean>) recyclerAdapter.getItemAtPosition(i);

                    if ((Boolean) selectoritem.get("selection")) {
                        selectoritem.put("selection", false);

                    }
                }

                recyclerAdapter.notifyDataSetChanged();
            }


        });
        Button Delete=(Button) view.findViewById(R.id.delete);
        Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Integer> deletionIndexList = new ArrayList<Integer>();
                for (int i = 0; i < recyclerAdapter.getItemCount(); i++) {
                    HashMap<String, Boolean> selectoritem = (HashMap<String, Boolean>) recyclerAdapter.getItemAtPosition(i);
                    if ((Boolean) selectoritem.get("selection")) {
                        deletionIndexList.add(i);

                    }

                }
                recyclerAdapter.removeItemsFromList(deletionIndexList);
                if(deletionIndexList.size() > 0){
                    recyclerAdapter.notifyItemRangeRemoved(deletionIndexList.get(0),deletionIndexList.size());
                }
            }
        });







        return view;
    }
public interface OnListItemSelectedListener
{
    public void OnListItemSelected( int position,HashMap<String,?> movie);


}
    @Override
public void onAttach(Activity activity)
{
    super.onAttach(activity);
    try
    {
mlistener=(OnListItemSelectedListener)activity;
    }catch(ClassCastException e)
    {

    }
}

}