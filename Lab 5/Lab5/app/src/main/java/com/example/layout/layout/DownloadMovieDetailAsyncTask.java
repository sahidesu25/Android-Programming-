package com.example.layout.layout;

import android.os.AsyncTask;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Sahithi on 6/23/2015.
 */
public class DownloadMovieDetailAsyncTask extends AsyncTask<String, Void, HashMap> {


    private final WeakReference<RecyclerViewFragment.OnListItemSelectedListener> weakListener;
    int position;
    public  DownloadMovieDetailAsyncTask(RecyclerViewFragment.OnListItemSelectedListener weakListener,int position)
    {
        this.weakListener=new WeakReference<RecyclerViewFragment.OnListItemSelectedListener>(weakListener);
        this.position=position;

    }
    protected HashMap doInBackground(String...urls){

        HashMap movieMap = new HashMap();
        MovieDataJson threadMovieDataJson = new MovieDataJson();
        for(String url : urls){
         //   Log.i("Url passed for MyDownloadMoviesDetailTask is :",url);
            movieMap = threadMovieDataJson.loadDetailMovieDataFromJsonUrl(url);
        }
        return movieMap;
    }

    protected void onPostExecute(HashMap threadmovie)
    {

        RecyclerViewFragment.OnListItemSelectedListener mlistener=weakListener.get();
        mlistener.OnListItemSelected((Map)threadmovie, position);

    }


}
