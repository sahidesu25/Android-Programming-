package com.example.layout.mylab6application;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class DetailMoviePagerActivity extends ActionBarActivity {
DetailPagerAdapter pagerAdapter;
    MovieDataJsonLocal movieData;
    ViewPager mViewPager;
    Toolbar mToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie_pager);
       mToolbar=(Toolbar )findViewById(R.id.toolbar);
       setSupportActionBar(mToolbar);
      android.support.v7.app.ActionBar actionbar=getSupportActionBar();
        actionbar.setDisplayShowHomeEnabled(false);
        Intent intent = getIntent();
         movieData=new MovieDataJsonLocal();
       // movieData=intent.getSerializableExtra("movie");
        movieData.loadLocalMovieDataJson(getApplicationContext());
        pagerAdapter=new DetailPagerAdapter(getSupportFragmentManager(),movieData.getSize(),movieData);
        mViewPager=(ViewPager)findViewById(R.id.detailviewpager);
        mViewPager.setAdapter(pagerAdapter);

     int start_position=intent.getIntExtra("position",0);
        mViewPager.setCurrentItem(start_position);
        animation();



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_detail_movie_pager, menu);

        return true;
    }
    public void animation()
    {
        mViewPager.setPageTransformer(false, new ViewPager.PageTransformer() {
            public void transformPage(View page, float position) {
                //rotation effect
               page.setRotationY(position*-30);
            }


        });

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
