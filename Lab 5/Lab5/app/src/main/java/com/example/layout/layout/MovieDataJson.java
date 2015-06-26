package com.example.layout.layout;

/**
 * Created by Sahithi on 6/21/2015.
 */

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MovieDataJson {

    public static String FILE_SERVER="http://www.cis.syr.edu/~wedu/Teaching/android/Labs/json/";
    public static String PHP_SERVER="http://www.example.com/movies/";

    List<Map<String,?>> moviesList;

    public List<Map<String, ?>> getMoviesList() {
        return moviesList;
    }
    public int getSize(){
        return moviesList.size();
    }
    public HashMap getItem(int i){
        if (i >=0 && i < moviesList.size()){
            return (HashMap) moviesList.get(i);
        } else
            return null;
    }

    public MovieDataJson() {
        moviesList = new ArrayList<Map<String,?>>();
    }

    public void loadLocalMovieDataJson(Context context) {
        moviesList.clear(); // clear the list

        // movie.json contains an array of movies
        String moviesArray = MyUtility.loadJSONFromAsset(context, "movie.json");
        if (moviesArray == null){
            Log.d("MyDebugMsg", "Having trouble load movie.json");
            return;
        }

        try {
            JSONArray moviesJsonArray = new JSONArray(moviesArray);
            for (int i = 0; i < moviesJsonArray.length(); i++) {
                JSONObject movieJsonObj = (JSONObject) moviesJsonArray.get(i);
                if (movieJsonObj != null) {
                    String name = (String) movieJsonObj.get("name");
                    double rating = Double.parseDouble(movieJsonObj.get("rating").toString());
                    String url = (String) movieJsonObj.get("url");
                    String description = (String) movieJsonObj.get("description");
                    String id = (String) movieJsonObj.get("id");
                    String image = (String) movieJsonObj.get("image");
                    int resID = context.getResources().getIdentifier(image, "drawable", context.getPackageName());
                    moviesList.add(createMovie(name, resID, description, null, null, rating, null, null, url, id));
                }
            }
        } catch (JSONException ex){
            Log.d("MyDebugMsg", "JSONException in loadLocalMovieDataJson()");
            ex.printStackTrace();
        }
    }


    public void downloadMovieDataJson(String json_url) {
        moviesList.clear(); // clear the list

        String moviesArray = MyUtility.downloadJSON(json_url);
        if (moviesArray == null){
            Log.d("MyDebugMsg", "Having trouble loading URL: "+json_url);
            return;
        }

        try {
            JSONArray moviesJsonArray = new JSONArray(moviesArray);
            for (int i = 0; i < moviesJsonArray.length(); i++) {
                JSONObject movieJsonObj = (JSONObject) moviesJsonArray.get(i);
                if (movieJsonObj != null) {
                    String name = (String) movieJsonObj.get("name");
                    double rating = Double.parseDouble(movieJsonObj.get("rating").toString());
                    String url = (String) movieJsonObj.get("url");
                    String description = (String) movieJsonObj.get("description");
                    String id = (String) movieJsonObj.get("id");
                    moviesList.add(createMovie_brief(name, description, rating, url, id));
                }
            }
        } catch (JSONException ex) {
            Log.d("MyDebugMsg", "JSONException in downloadMovieDataJson");
            ex.printStackTrace();
        }
    }
    public HashMap loadDetailMovieDataFromJsonUrl(String path)  {
        HashMap movie = new HashMap();

        try{
            String description = null;
            double rating = 0.0;
            String url = null;
            String name = null;
            String image = null;
            String id = null;
            String stars = null;
            String year = null;
            String length = null;
            String director = null;
            JSONArray moviesJsonArray = null;
            JSONObject movieJsonObj = null;
            String jsonMoviesDetailDataString = MyUtility.downloadJSON(path);
           // movieJsonObj = new JSONObject(jsonMoviesDetailDataString);
           moviesJsonArray = new JSONArray(jsonMoviesDetailDataString);
          movieJsonObj = (JSONObject) moviesJsonArray.get(0);


                // Parse the string to construct JSON array
            moviesJsonArray = new JSONArray(jsonMoviesDetailDataString);
                for (int i = 0; i < moviesJsonArray.length(); i++) {
                    movieJsonObj = (JSONObject) moviesJsonArray.get(i);
                    if (movieJsonObj != null) {
                         name = (String) movieJsonObj.get("name");
                         rating = Double.parseDouble(movieJsonObj.get("rating").toString());
                         url = (String) movieJsonObj.get("url");
                         description = (String) movieJsonObj.get("description");
                         id = (String) movieJsonObj.get("id");
                       if(movieJsonObj.get("director")==null)
                       {
                           continue;
                       }
                         director = (String) movieJsonObj.get("director");
                        if(movieJsonObj.get("stars")!=null)
                         stars = (String) movieJsonObj.get("stars");
                        if(movieJsonObj.get("year")!=null)
                        {
                            Integer _year= (Integer)movieJsonObj.get("year");
                            year = (Integer.toString(_year));
                        }
                        if(movieJsonObj.get("length")!=null)

                        length = (String) movieJsonObj.get("length");

                       movie=createMovie(name, R.mipmap.ic_launcher, description, year, length, rating, director, stars, url, id);

                    }}
            } catch (JSONException ex){
                Log.d("MyDebugMsg", "JSONException in loadLocalMovieDataJson()");
                ex.printStackTrace();
            }
        return movie;
    }




    private static HashMap createMovie(String name, int image, String description, String year,
                                       String length, double rating, String director, String stars, String url, String id) {
        HashMap movie = new HashMap();
        movie.put("image",image);
        movie.put("name", name);
        movie.put("description", description);
        movie.put("year", year);
        movie.put("length",length);
        movie.put("rating",rating);
        movie.put("director",director);
        movie.put("stars",stars);
        movie.put("url",url);
        movie.put("id",id);
        return movie;
    }

    private static HashMap createMovie_brief(String name, String description,
                                             double rating, String url, String id) {
        HashMap movie = new HashMap();

        movie.put("name", name);
        movie.put("description", description);
        movie.put("rating",rating);
        movie.put("url",url);
        movie.put("id",id);
        return movie;
    }

}