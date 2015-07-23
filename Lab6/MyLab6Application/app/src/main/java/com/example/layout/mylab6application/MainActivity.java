package com.example.layout.mylab6application;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class MainActivity extends ActionBarActivity implements MainActivityFragment.OnButtonClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState==null)
        {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container,MainActivityFragment.newInstance(1)).addToBackStack(null).commit();


        }
    }
    @Override
    public void OnButtonClick(View v)
    {
int id=v.getId();
        Intent intent=null;
        MovieDataJsonLocal movieData=null;
        switch(id){
            case R.id.aboutmebutton:
                getSupportFragmentManager().beginTransaction().replace(R.id.container,AboutMeFragment.newInstance(1)).
                        addToBackStack(null).commit();
                break;
            case R.id.masterbutton:
                intent=new Intent(this,MasterDetailFlow.class);
               // movieData=new MovieDataJsonLocal();
               // movieData.loadLocalMovieDataJson(getApplicationContext());
               // intent.putExtra("movie",movieData);
                startActivity(intent);
                break;
            case R.id.viewpager:
                intent=new Intent(this,ViewPagerActivity.class);
                startActivity(intent);
                break;

            case R.id.Recycler:
                intent=new Intent(this,RecyclerActivity.class);
                startActivity(intent);
                break;
            default:
                getSupportFragmentManager().beginTransaction().replace(R.id.container,AboutMeFragment.newInstance(1)).
                        addToBackStack(null).commit();

        }

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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
