package com.example.layout.layout;

import android.app.Activity;
import android.graphics.Bitmap;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.LruCache;
import android.view.LayoutInflater;
//import android.support.v7.view.Menu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.support.v7.widget.SearchView;


import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Sahithi on 6/13/2015.
 */
public class RecyclerViewFragment extends Fragment {
    public static final String ARG_OPTION = "argument_option";
    public  static MovieDataJson movieData;
    public static LruCache<String,Bitmap> imagecache;

   public  static RecyclerAdapter recyclerAdapter;
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    public OnListItemSelectedListener mlistener;

    int counter;
    View view;
    Parcelable state;



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
        setHasOptionsMenu(true);

        //Log.d(TAG, this + ": onCreate()");
        if(savedInstanceState==null)
        {
          counter=0;
           // MainActivity activity = (MainActivity) getActivity();
            movieData = new MovieDataJson();
            recyclerAdapter=new RecyclerAdapter(getActivity(),movieData.getMoviesList());
            String url=MovieDataJson.PHP_SERVER;
            MyDownloadJsonAsynTask mydownload=new MyDownloadJsonAsynTask(recyclerAdapter,movieData);
            mydownload.execute(new String[]{url});



            //create a cache to hold the images
            if(imagecache==null)
            {
                final int maxMemory=(int) (Runtime.getRuntime().maxMemory()/1024);
                //use 1/8th of the memory
                final int cachesize=maxMemory/8;
                imagecache=new LruCache<String,Bitmap>(cachesize) {
                    protected int sizeOf(String key, Bitmap bitmap) {
                      return bitmap.getByteCount()/1024;
                    }
                };
            }
            //movieData=activity.getData();
           // movieData.loadLocalMovieDataJson(getActivity());
        }
        else
        {
            counter=savedInstanceState.getInt("counter");
        }

    }



    public RecyclerViewFragment() {

    }


    public  RecyclerAdapter getAdapter()
    {
return recyclerAdapter;
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {





view=inflater.inflate(R.layout.recycleviewfragment,container,false);



    List<Map<String,?>> moviesList=movieData.getMoviesList();


         recyclerView=(RecyclerView)view.findViewById(R.id.recycler1);
        recyclerView.setHasFixedSize(true);
         layoutManager=new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);




        recyclerView.setAdapter(recyclerAdapter);

        recyclerAdapter.setOnItemClickListener(new RecyclerAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View v, int position) {
                HashMap<String, ?> movie = movieData.getItem(position);
                String id=(String)movie.get("id");
                String url=MovieDataJson.PHP_SERVER+"id/"+id;
                if(url!=null)
                {
                    DownloadMovieDetailAsyncTask downloaddetail=new DownloadMovieDetailAsyncTask(mlistener,position);
                    downloaddetail.execute(new String[] {url});

                }


               // mlistener.OnListItemSelected(position, movie);
            }

            @Override
            public void OnItemLongClick(View v, int position) {
                recyclerAdapter.duplicateItem(position);
                recyclerAdapter.notifyItemInserted(position);

            }

        });


        MainActivity activity = (MainActivity) getActivity();

        //activity.setData(movieData);
        recyclerAdapter.notifyDataSetChanged();
        return view;
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_actionitem, menu);

        SearchView search = null;
        MenuItem menuItem = menu.findItem(R.id.action_search);
        if (menuItem != null)
            search = (SearchView) menuItem.getActionView();
        if (search != null) {
            search.setQueryHint("Rating");
            search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String rating) {
                   // ...... // You need to put your own code here
                    String url=MovieDataJson.PHP_SERVER+"rating/"+rating;
                    MyDownloadJsonAsynTask mydownload=new MyDownloadJsonAsynTask(recyclerAdapter,movieData);
                    mydownload.execute(new String[]{url});

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


public interface OnListItemSelectedListener
{
    public void OnListItemSelected( Map movie, int position);


}

    public void onPause() {

        super.onPause();
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