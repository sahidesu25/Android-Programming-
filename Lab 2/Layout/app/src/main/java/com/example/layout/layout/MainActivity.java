package com.example.layout.layout;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment,MainActivityFragment.newInstance(1)).addToBackStack(null).commit();
        Button loadnext=(Button) findViewById(R.id.next);

        /*View.OnClickListener aButtonChangeImageListener=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.getId()==R.id.next)
                {
                   ImageView image=(ImageView) findViewById((R.id.image1));
                    int id=(Integer)items[1].get("image");
                    image.setImageResource(id);
                }



            }
        };*/



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
        else if(id==R.id.linear){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment,LayoutActivityFragment.newInstance(2)).addToBackStack(null).commit();
            return true;
        }
        else if(id==R.id.relative){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment,RelativeLayoutFragment.newInstance(3)).addToBackStack(null).commit();
            return true;
        }
        else if(id==R.id.movielayout)
        {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment,MovieInfoFile.newInstance(4)).addToBackStack(null).commit();
            return true;
        }
        else if(id==R.id.seekbarlayout)
        {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment,Seekbar.newInstance(6)).addToBackStack(null).commit();
            return true;
        }


        return super.onOptionsItemSelected(item);
    }
}
