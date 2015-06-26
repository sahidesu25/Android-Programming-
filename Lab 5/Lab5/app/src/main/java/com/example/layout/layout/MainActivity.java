package com.example.layout.layout;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.HashMap;
import java.util.Map;


public class MainActivity extends ActionBarActivity implements RecyclerViewFragment.OnListItemSelectedListener,
        MovieInfoFragment.OnButtonClickListener {
private HashMap movieitem;
    Bundle savedInatanceSate;
    public  MovieDataJson movieData=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.savedInatanceSate=savedInstanceState;
        super.onCreate(savedInstanceState);
        movieData=new MovieDataJson();
        setContentView(R.layout.activity_main);
if(savedInstanceState==null)
{
    getSupportFragmentManager().beginTransaction()
            .replace(R.id.fragment,MainActivityFragment.newInstance(1)).commit();
}
    }



public void OnListItemSelected(Map movie,int position)
{

    getSupportFragmentManager()
            .beginTransaction()
                .replace(R.id.fragment, MovieInfoFragment.newInstance(7)).addToBackStack("null").commit();
    movieitem=(HashMap)movie;
}
    public HashMap getMovieitem()
    {
        return movieitem;
    }

    @Override
    public void OnButtonClick() {

       // fm.popBackStack();
        getSupportFragmentManager().popBackStack();
               // .beginTransaction()
                //.replace(R.id.fragment, RecyclerViewFragment.newInstance(6), "recycletag").addToBackStack("recycletag").commit();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

//Save the fragment's instance
        getSupportFragmentManager().putFragment(outState, "recycletag", RecyclerViewFragment.newInstance(6));


    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.myself) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment,AboutMeFragment.newInstance(1)).addToBackStack(null).commit();
            return true;
        }
        else if(id==R.id.recycleViewitem)
        {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment, RecyclerViewFragment.newInstance(6), "recycletag").addToBackStack("recycletag").commit();
            return true;

        }
        else if(id==R.id.addnewmovie)
        {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment, EnterNewMovie.newInstance(8), "addMovietag").addToBackStack("addMovietag").commit();
            return true;

        }


        return super.onOptionsItemSelected(item);
    }
}
