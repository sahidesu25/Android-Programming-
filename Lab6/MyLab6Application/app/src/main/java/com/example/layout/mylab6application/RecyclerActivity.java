package com.example.layout.mylab6application;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.HashMap;


public class RecyclerActivity extends ActionBarActivity implements RecyclerViewFragment.OnListItemSelectedListener{
 public HashMap<String,?> movieitem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);
        if(savedInstanceState==null)
        {
            getSupportFragmentManager().beginTransaction().
                    replace(R.id.RecyclerContainer, RecyclerViewFragment.newInstance(0)).commit();
        }
    }
    public void OnListItemSelected(int position, HashMap<String,?> movie)
    {   movieitem=movie;
        getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.RecyclerContainer, MovieInfoFragment.newInstance(6, movieitem)).addToBackStack(null).commit();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_recycler, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
