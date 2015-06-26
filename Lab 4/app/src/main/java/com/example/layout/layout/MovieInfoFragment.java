package com.example.layout.layout;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
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
    private OnButtonClickListener buttonListener;
    public static MovieInfoFragment newInstance(int option)
    {
        MovieInfoFragment fragment=new MovieInfoFragment();
        Bundle args=new Bundle();
        args.putInt(ARG_OPTION,option);
        fragment.setArguments(args);
        return fragment;

    }

    public MovieInfoFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.movieinfo, container, false);

        MainActivity activity = (MainActivity) getActivity();
        HashMap<String,?> movieItem=activity.getMovieitem();
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
        Button backtomovieslist=(Button)view.findViewById(R.id.movielistbutton);
        backtomovieslist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonListener.OnButtonClick();
            }
        });


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


