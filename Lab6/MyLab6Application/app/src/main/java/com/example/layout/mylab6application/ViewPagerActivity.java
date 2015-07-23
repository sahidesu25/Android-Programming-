package com.example.layout.mylab6application;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.HashMap;


public class ViewPagerActivity extends ActionBarActivity  implements RecyclerViewFragment.OnListItemSelectedListener {
MovieDataJsonLocal movieData;
    ViewPager mViewPager;
    ViewPagerAdapter pagerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);
        movieData=new MovieDataJsonLocal();
        //movieData.loadLocalMovieDataJson(getApplicationContext());
        mViewPager=(ViewPager)findViewById(R.id.viewPagerActivity);
        pagerAdapter=new ViewPagerAdapter(getSupportFragmentManager(),2);
        mViewPager.setAdapter(pagerAdapter);
        animations();



    }
public void animations()
{
    mViewPager.setPageTransformer(false, new ViewPager.PageTransformer() {
        public void transformPage(View page, float position) {
            //scaling effect
            final float normalized_position = Math.abs(Math.abs(position) - 1);
            page.setScaleX(normalized_position / 2 + 0.5f);
            page.setScaleY(normalized_position / 2 + 0.5f);
        }


    });

}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view_pager, menu);
        return true;
    }
    public void OnListItemSelected(int position, HashMap<String,?> movie)
    {
        Intent intent=null;
        intent=new Intent(this,DetailMoviePagerActivity.class);
        intent.putExtra("position",position);
        startActivity(intent);



          /*  getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.list_item,MovieInfoFragment.newInstance(6)).addToBackStack(null).commit();*/

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
