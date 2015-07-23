 package com.example.layout.mylab7application;


        import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupMenu;

import java.util.Arrays;
import java.util.List;


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
    GridLayoutManager gridlayoutmanager;
    public int longclickposition;
    Activity FragmentActivity;
    PopupMenu popup;
    private OnListItemSelectedListener mlistener;
    int counter;




    public static RecyclerViewFragment newInstance(int option) {
        RecyclerViewFragment fragment = new RecyclerViewFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_OPTION, option);

        fragment.setArguments(args);
        //_option=option;
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
       // setHasOptionsMenu(true);
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_actionitem, menu);

        SearchView search = null;
        MenuItem menuItem = menu.findItem(R.id.action_search);
        if (menuItem != null)
            search = (SearchView) menuItem.getActionView();
        if (search != null) {
            search.setQueryHint("MovieName");
            search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String MovieName) {
                    //int position = movieData.findFirst(MovieName);
                    for(int i=0;i<movieData.getSize();i++)
                    {
                        HashMap<String,?> MovieMap=movieData.getItem(i);
                        String movie=(String)MovieMap.get("name");
                        if(MovieName.equalsIgnoreCase(movie))
                        {
                            if (i >= 0) {
                                recyclerView.scrollToPosition(i);
                                break;
                            }
                        }
                    }

                    return true;
                }

                @Override
                public boolean onQueryTextChange(String query) {
                    return true;
                }
            });
        }

        super.onCreateOptionsMenu(menu, inflater);
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
        int option=getArguments().getInt(ARG_OPTION);
        FragmentActivity=getActivity();
        if(option==0)
        {
            layoutManager=new LinearLayoutManager(getActivity());
            recyclerView.setLayoutManager(layoutManager);

        }
        else if(option==1)
        {
            gridlayoutmanager=new GridLayoutManager(getActivity(),4);
            recyclerView.setLayoutManager(gridlayoutmanager);
        }
        else
        {
            layoutManager=new LinearLayoutManager(getActivity());
            recyclerView.setLayoutManager(layoutManager);
        }
        recyclerAdapter=new RecyclerAdapter(getActivity(),movieData.getMoviesList());
        recyclerView.setAdapter(recyclerAdapter);
        recyclerAdapter.setOnItemClickListener(new RecyclerAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View v, int position) {
                HashMap<String, ?> movie = movieData.getItem(position);
               // mlistener.OnListItemSelected(position, movie);
            }
            @Override
            public void OnItemLongClick(View v, int position){
                longclickposition=position;
               FragmentActivity.startActionMode(new ActionbarCallback(position, movieData, recyclerAdapter));
            }
            public void OnOverFlowMenuClick(View v, final int position)
            {   longclickposition=position;
                popup=new PopupMenu(getActivity(),v);
                MenuInflater inflater=popup.getMenuInflater();
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        switch(item.getItemId())
                        {
                            case R.id.item_delete:
                                List<Integer> deletionIndexList = new ArrayList<Integer>();
                                deletionIndexList.add(longclickposition);
                                recyclerAdapter.removeItemsFromList(deletionIndexList);
                                recyclerAdapter.notifyItemRemoved(longclickposition);
                                return true;
                            case R.id.item_duplicate:
                                recyclerAdapter.duplicateItem(longclickposition);
                                recyclerAdapter.notifyItemInserted(longclickposition+1);

                                return true;
                            default:
                              return false;

                        }
                    }
                });
                inflater.inflate(R.menu.contextual_or_popupmenu,popup.getMenu());
                popup.show();

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