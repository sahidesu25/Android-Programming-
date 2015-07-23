package com.example.layout.mylab7application;

import android.content.res.Configuration;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class MainActivity extends ActionBarActivity {
 Toolbar mtoolbar;
    DrawerAdapter mdraweradapter;
    private DrawerLayout mDrawerLayout;
    RecyclerView mdrawerList;
    DrawerData drawerData;
    ActionBarDrawerToggle mDrawerToggle;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //setting toolbar
        mtoolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mtoolbar);
        android.support.v7.app.ActionBar actionbar=getSupportActionBar();
        actionbar.setDisplayShowHomeEnabled(false);
        drawerData = new DrawerData();
        //setting up Recycler Adapter

        mdrawerList = (RecyclerView) findViewById(R.id.left_drawer);
        mdrawerList.setLayoutManager(new LinearLayoutManager(this));
        mdraweradapter = new DrawerAdapter(this, drawerData.getDrawerlist());
        mdraweradapter.setOnItemClickListener(new DrawerAdapter.OnItemClickListener(){
            public void OnItemClick(View v,int position)
            {
                selectItem(position);
            }
        });
        mdrawerList.setAdapter(mdraweradapter);
        //setting up drawer layout\
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mtoolbar, R.string.drawer_open, R.string.drawer_close) {


            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);

            }
            public void onDrawerOpened(View view)
            {
                super.onDrawerOpened(view);
            }


        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                   .add(R.id.container,MainActivityFragment.newInstance(1))
                   .commit();
        }
    }
private void selectItem(int position)
{
    switch(position)
    {
        case 0:
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container,MainActivityFragment.newInstance(1))
                    .commit();
            break;
        case 1:
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container,DemoFragment.newInstance(0))
                    .commit();
            break;
        case 2:
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, RecyclerViewFragment.newInstance(0)).commit();
            break;
        case 3:
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, RecyclerViewFragment.newInstance(1)).commit();
            break;
        case 5:
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, AboutMeFragment.newInstance(1)).commit();
            break;
        case 6:
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, AvatarFragment.newInstance(1)).commit();
            break;
        case 7:
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container,TitanicFragment.newInstance(1)).commit();
            break;
        default:
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container,MainActivityFragment.newInstance(1))
                    .commit();
    }
    mDrawerLayout.closeDrawer(mdrawerList);
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
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls
        mDrawerToggle.onConfigurationChanged(newConfig);
    }
}
