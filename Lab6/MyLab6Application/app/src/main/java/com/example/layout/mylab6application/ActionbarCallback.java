package com.example.layout.mylab6application;


import android.util.Log;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;
import java.util.Map;

/**
 * Created by Sahithi on 7/1/2015.
 */
public class ActionbarCallback implements ActionMode.Callback {

    int position;
    MovieDataJsonLocal movieData;
    RecyclerAdapter myRecyclerAdapter;

    public  ActionbarCallback(int position, MovieDataJsonLocal movieData, RecyclerAdapter myRecyclerAdapter) {
        this.position = position;
        this.movieData = movieData;
        this.myRecyclerAdapter = myRecyclerAdapter;
    }

    @Override
    public boolean onCreateActionMode(ActionMode mode, Menu menu) {
        mode.getMenuInflater().inflate(R.menu.contextual_or_popupmenu,menu);
        return true;
    }
    @Override
    public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
        Map<String,?> movieMap = movieData.getItem(position);
        mode.setTitle((String) movieMap.get("name"));
        return false;
    }
    @Override
    public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
        int id = item.getItemId();
        List<Map<String, ?>> movieList = movieData.getMoviesList();
        switch (id) {
            case R.id.item_delete:
                movieList.remove(position);
                myRecyclerAdapter.notifyItemRemoved(position);
                mode.finish();
                break;
            case R.id.item_duplicate:
                myRecyclerAdapter.duplicateItem(position);
                myRecyclerAdapter.notifyItemInserted(position);
                mode.finish();
                break;
            default:
                break;
        }
        return false;
    }
    @Override
    public void onDestroyActionMode(ActionMode mode) {

    }



}
