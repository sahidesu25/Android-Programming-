package com.example.layout.layout;

import android.os.AsyncTask;

import java.lang.ref.WeakReference;

/**
 * Created by Sahithi on 6/21/2015.
 */
public class MyDownloadJsonAsynTask extends AsyncTask<String,Void,MovieDataJson> {
    private final WeakReference<RecyclerAdapter> adapterReference;
    MovieDataJson movieData;
   // RecyclerAdapter adapter;
    public MyDownloadJsonAsynTask(RecyclerAdapter adapter,MovieDataJson movieData)
    {         adapterReference=new WeakReference<RecyclerAdapter>(adapter);
        this.movieData=movieData;

    }

    @Override
    protected MovieDataJson doInBackground(String...urls){
        MovieDataJson threadmovie=new MovieDataJson();
        for(String url:urls)
        {
          threadmovie.downloadMovieDataJson(url);
        }

        return threadmovie;
    }
    @Override
    protected void onPostExecute(MovieDataJson threadmovie)
    {
        movieData.moviesList.clear();
        for(int i=0;i<threadmovie.getSize();i++)
        {
            movieData.moviesList.add(threadmovie.moviesList.get(i));
        }
        if(adapterReference!=null)
        {

             RecyclerAdapter adapter=adapterReference.get();
            //adapter=RecyclerViewFragment.recyclerAdapter;
          if(adapter!=null)
            {
               adapter.notifyDataSetChanged();
            }
        }
    }
}
