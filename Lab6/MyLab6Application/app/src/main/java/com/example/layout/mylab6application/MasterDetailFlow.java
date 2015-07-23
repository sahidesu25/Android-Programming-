package com.example.layout.mylab6application;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.HashMap;


public class MasterDetailFlow extends ActionBarActivity
        implements RecyclerViewFragment.OnListItemSelectedListener{
    private HashMap<String,?> movieitem;
    private boolean mTwoPane;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master_detail_flow);
        if(savedInstanceState==null)
        {
            getSupportFragmentManager().beginTransaction().replace(R.id.list_item,RecyclerViewFragment.newInstance(0)).commit();
        }
        //check wether the detail container is present or not

        if(findViewById(R.id.item_detail_Container)!=null)
        {
            mTwoPane=true;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_master_detail_flow, menu);
        return true;
    }
    public void OnListItemSelected(int position, HashMap<String,?> movie)
    { movieitem=movie;
      if(mTwoPane)
      {//loading fragment in to the detail container view
          getSupportFragmentManager()
                  .beginTransaction()
                  .replace(R.id.item_detail_Container,MovieInfoFragment.newInstance(6,movieitem)).addToBackStack(null).commit();

      }
        else
      { //loading fragment in to the normal container view.
          getSupportFragmentManager()
                  .beginTransaction()
                  .replace(R.id.list_item,MovieInfoFragment.newInstance(6,movieitem)).addToBackStack(null).commit();
      }


    }

    public HashMap<String,?>getMovieitem()
    {
        return movieitem;
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
