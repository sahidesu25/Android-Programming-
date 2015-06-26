package com.example.layout.layout;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.HashMap;


public class MainActivity extends ActionBarActivity implements RecyclerViewFragment.OnListItemSelectedListener,MovieInfoFragment.OnButtonClickListener {
private HashMap<String,?> movieitem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
if(savedInstanceState==null)
{
    getSupportFragmentManager().beginTransaction()
            .replace(R.id.fragment,MainActivityFragment.newInstance(1)).addToBackStack(null).commit();


}

//setRetainInstance(true);





    }

public void OnListItemSelected(int position, HashMap<String,?> movie)
{

    getSupportFragmentManager()
            .beginTransaction()
                .replace(R.id.fragment,MovieInfoFragment.newInstance(6)).addToBackStack(null).commit();
    movieitem=movie;
}
    public HashMap<String,?>getMovieitem()
    {
        return movieitem;
    }

    @Override
    public void OnButtonClick() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment,RecyclerViewFragment.newInstance(6)).addToBackStack(null).commit();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
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
        else if(id==R.id.listviewitem)
        {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment,ListViewFragment.newInstance(6)).addToBackStack(null).commit();
            return true;
        }
        else if(id==R.id.gridviewitem)
        {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment,GirdViewFragment.newInstance(6)).addToBackStack(null).commit();
            return true;

        }
        else if(id==R.id.recycleViewitem)
        {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment,RecyclerViewFragment.newInstance(6)).addToBackStack(null).commit();
            return true;

        }


        return super.onOptionsItemSelected(item);
    }
}
