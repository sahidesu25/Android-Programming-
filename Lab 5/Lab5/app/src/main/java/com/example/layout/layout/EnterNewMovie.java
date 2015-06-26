package com.example.layout.layout;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;

import java.util.HashMap;

import javax.security.auth.Subject;


/**
 * A placeholder fragment containing a simple view.
 */
public class EnterNewMovie extends Fragment {
    public static final String ARG_OPTION="argument_option";
    public String MovieName;
    public String Description;
    public Double Rating;
    public String Url;
    public String Director;
    public String Cast;
    TextView Upload;
    public HashMap Movie;
    EditText moviename,description,url,rating,director,cast;


    public static EnterNewMovie newInstance(int option)
    {
        EnterNewMovie fragment=new EnterNewMovie();
        Bundle args=new Bundle();
        args.putInt(ARG_OPTION,option);
        fragment.setArguments(args);
        return fragment;

    }
    private HashMap createMovie(String name,  String description,
                                 double rating, String director, String stars, String url) {
        HashMap movie = new HashMap();

        movie.put("name", name);
        movie.put("description", description);
        movie.put("rating",rating);
        movie.put("director",director);
        movie.put("stars",stars);
        movie.put("url", url);

        return movie;
    }

    public EnterNewMovie() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.newmovielayout, container, false);
        moviename=(EditText)view.findViewById(R.id.MovieName);
        description=(EditText)view.findViewById(R.id.description);
        url=(EditText)view.findViewById(R.id.url);
        rating=(EditText)view.findViewById(R.id.rating);
        String rating_=rating.getText().toString();
       // Rating=Double.parseDouble(rating_);
       director=(EditText)view.findViewById(R.id.director);

        cast=(EditText)view.findViewById(R.id.cast);

        Upload=(TextView) view.findViewById(R.id.Upload);
        Button Sumbit=(Button)view.findViewById(R.id.Submit);
        Sumbit.setOnClickListener(new View.OnClickListener() {
           @Override
            public void onClick(View v) {

               MovieName=moviename.getText().toString();
               Description=description.getText().toString();
               Url=url.getText().toString();
               Director=director.getText().toString();
               Cast=cast.getText().toString();
               Movie=new HashMap();
               Movie=createMovie(MovieName, Description, 0.0, Director, Cast,Url);
               String url_ = MovieDataJson.PHP_SERVER + "post/";
                UploadMovieAsyncTask uploadMovieAsyncTask = new UploadMovieAsyncTask(Movie, Upload);
               uploadMovieAsyncTask.execute(new String[]{url_});

            }
        });
        return view;

    }
}


