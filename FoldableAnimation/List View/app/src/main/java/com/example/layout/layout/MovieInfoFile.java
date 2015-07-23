package com.example.layout.layout;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.HashMap;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.view.View.OnClickListener;




/**
 * A placeholder fragment containing a simple view.
 */
public class MovieInfoFile extends Fragment {
    public static final String ARG_OPTION="argument_option";
    HashMap[]items=new HashMap[30];
    int i;
    public ImageView image;
    TextView name;
    TextView des;
    TextView stars;
    TextView year;
    RatingBar rating;

    public static MovieInfoFile newInstance(int option)
    {
        MovieInfoFile fragment=new MovieInfoFile();
        Bundle args=new Bundle();

        args.putInt(ARG_OPTION,option);
        fragment.setArguments(args);



        return fragment;

    }

    public MovieInfoFile() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.movieinfolayout, container, false);
        Button loadnext=(Button) view.findViewById(R.id.next);
        Button loadprev=(Button) view.findViewById(R.id.prev);
        Button fulllist=(Button) view.findViewById((R.id.fullmovielist));
         image=(ImageView) view.findViewById((R.id.image1));
        name=(TextView) view.findViewById(R.id.nameans);
         des=(TextView) view.findViewById(R.id.desans);
         stars=(TextView) view.findViewById(R.id.starans);
         year=(TextView) view.findViewById(R.id.yearans);
         rating=(RatingBar) view.findViewById(R.id.ratingans);
        MovieData mdata=new MovieData();
        items[0]=mdata.getItem(0);
        image.setImageResource((Integer) items[0].get("image"));
      name.setText((String) items[0].get("name"));
        des.setText((String) items[0].get("description"));
        stars.setText((String)items[0].get("stars"));
        year.setText((String)items[0].get("year"));
        Double rating_=(Double)items[0].get("rating");


        rating.setRating(Float.parseFloat(String.valueOf(rating_)));



        loadnext.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.next) {
                    i++;
                    if (i >= 30) {

                    } else {
                        MovieData mdata1 = new MovieData();
                        items[i] = mdata1.getItem(i);
                        int id = (Integer) items[i].get("image");
                        image.setImageResource(id);
                        name.setText((String) items[i].get("name"));
                        des.setText((String) items[i].get("description"));
                        stars.setText((String) items[i].get("stars"));
                        year.setText((String) items[i].get("year"));
                        Double rating_ = (Double) items[i].get("rating");
                        rating.setRating(Float.parseFloat(String.valueOf(rating_)));


                    }


                }

            }
        });


        loadprev.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.prev) {
                    i=i-1;
                    if(i<0)
                    {

                    }
                    else
                    {
                        MovieData mdata2 = new MovieData();
                        items[i] = mdata2.getItem(i);
                        int id = (Integer) items[i].get("image");
                        image.setImageResource(id);
                        name.setText((String) items[i].get("name"));
                        des.setText((String) items[i].get("description"));
                        stars.setText((String)items[i].get("stars"));
                        year.setText((String)items[i].get("year"));
                        Double rating_=(Double)items[i].get("rating");
                        rating.setRating(Float.parseFloat(String.valueOf(rating_)));


                    }



                }

            }
        });

       fulllist.setOnClickListener(new OnClickListener() {
           @Override
           public void onClick(View v) {
               if(v.getId()==R.id.fullmovielist)
               {
                   getFragmentManager().beginTransaction().replace(R.id.fragment,MovieLayoutFragment.newInstance(5)).addToBackStack(null).commit();
               }
           }
       });




        return view;
    }
}


