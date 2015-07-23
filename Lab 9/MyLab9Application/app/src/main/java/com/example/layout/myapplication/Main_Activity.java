package com.example.layout.myapplication;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class Main_Activity extends ActionBarActivity implements Main_ActivityFragment.OnButtonClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_);
        if(savedInstanceState==null)
        getFragmentManager().beginTransaction().replace(R.id.container,Main_ActivityFragment.newInstance(1)).addToBackStack(null).commit();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_, menu);
        return true;
    }
    public void OnButtonClick(View view)
    {
        int id=view.getId();
        if(id==R.id.button)
        {
            getFragmentManager().beginTransaction()
                    .replace(R.id.container, ExerciseCanvasFragment.newInstance(0)).addToBackStack(null)
                    .commit();
        }
        else if(id==R.id.button6)
        {
            getFragmentManager().beginTransaction()
                    .replace(R.id.container, ExerciseThermometerFragment.newInstance(0)).addToBackStack(null)
                    .commit();
        }
       else  if(id==R.id.button7)
        {
            getFragmentManager().beginTransaction()
                    .replace(R.id.container, ExerciseRotaryKnobFragment.newInstance(0)).addToBackStack(null)
                    .commit();
        }
        else if(id==R.id.button8)
        {
            getFragmentManager().beginTransaction()
                    .replace(R.id.container,RotatoryKnob2Fragment.newInstance(0)).addToBackStack(null).commit();

        }
        else if(id==R.id.button9)
        {
            getFragmentManager().beginTransaction()
                    .replace(R.id.container,AboutMeFragment.newInstance(0)).addToBackStack(null).commit();

        }
    }
    public void onBackPressed()
    {
        getFragmentManager().beginTransaction().replace(R.id.container,Main_ActivityFragment.newInstance(1)).addToBackStack(null).commit();
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
