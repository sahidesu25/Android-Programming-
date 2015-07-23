package com.example.layout.mylab6application;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Button;
import java.util.HashMap;


/**
 * A placeholder fragment containing a simple view.
 */
public class MovieInfoFragment extends Fragment {
    public static final String ARG_OPTION="argument_option";
    public static final String movie="movie_map";
    private OnButtonClickListener buttonListener;
    Toolbar mToolbar;
    private HashMap<String,?> movieMap = null;
    public static MovieInfoFragment newInstance(int option,HashMap<String,?> Moviemap)
    {
        MovieInfoFragment fragment=new MovieInfoFragment();
        Bundle args=new Bundle();
        args.putInt(ARG_OPTION,option);
        args.putSerializable(movie,Moviemap);
        fragment.setArguments(args);
        return fragment;

    }

    public MovieInfoFragment() {
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
       setHasOptionsMenu(true);


        if(getArguments() != null){
            movieMap = (HashMap<String,?>) getArguments().getSerializable(movie);
        }


    }
public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
{
    inflater.inflate(R.menu.menu_detail_movie_pager,menu);
    MenuItem shareitem=menu.findItem(R.id.action_share);
    ShareActionProvider mShareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(shareitem);
    Intent intentShare=new Intent(Intent.ACTION_SEND);
    intentShare.setType("text/plain");
    intentShare.putExtra(Intent.EXTRA_TEXT,(String)movieMap.get("name"));
    mShareActionProvider.setShareIntent(intentShare);
    super.onCreateOptionsMenu(menu,inflater);

}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.movieinfo, container, false);
        mToolbar=(Toolbar)view.findViewById(R.id.toolbar);
        //setSupportActionBar(mToolbar);
        //  android.support.v7.app.ActionBar actionbar=getSupportActionBar();
        //actionbar.setDisplayShowHomeEnabled(false);

      //  MasterDetailFlow activity = (MasterDetailFlow) getActivity();
        //HashMap<String,?> movieItem=activity.getMovieitem();
        HashMap<String,?> movieItem=movieMap;
        ImageView imageView=(ImageView) view.findViewById(R.id.image1);
        imageView.setImageResource((Integer) movieItem.get("image"));
        TextView movieName=(TextView)view.findViewById(R.id.name);
        movieName.setText((String) movieItem.get("name"));
        TextView year=(TextView)view.findViewById(R.id.yearans);
        String movieyear=(String)movieItem.get("year");
        String year_="("+movieyear+")";
        year.setText(year_);
        TextView duration=(TextView)view.findViewById(R.id.duration);
        duration.setText((String)movieItem.get("length"));
        TextView Director=(TextView)view.findViewById(R.id.directorans);
        Director.setText((String) movieItem.get("director"));
        TextView Cast=(TextView)view.findViewById(R.id.castans);
        Cast.setText((String) movieItem.get("stars"));
        RatingBar rating=(RatingBar)view.findViewById(R.id.rating);
        Double _rating =(Double)movieItem.get("rating");
        rating.setRating((float) (_rating / 2));
        TextView description=(TextView)view.findViewById(R.id.desans);
        description.setText((String) movieItem.get("description"));
        /*Button backtomovieslist=(Button)view.findViewById(R.id.movielistbutton);
        backtomovieslist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonListener.OnButtonClick();
            }
        });*/


        return view;
    }
    public interface OnButtonClickListener
    {
        public void OnButtonClick();

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


