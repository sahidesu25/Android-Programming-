package com.example.layout.layout;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.alexvasilkov.foldablelayout.UnfoldableView;

import java.util.HashMap;
import java.util.Map;


public class UnfoldableActivity extends ActionBarActivity {
    private ListView mListView;
    public MovieData movieData = new MovieData();
    private View mListTouchInterceptor;
    private View mDetailsLayout;
    private UnfoldableView mUnfoldableView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.unfoldable);
        mListView=(ListView)findViewById(R.id.list_view);
        final MyImageAdapter myImageAdapter=new MyImageAdapter(this,movieData.getMoviesList());
        mListView.setAdapter(myImageAdapter);
        mListTouchInterceptor = findViewById(R.id.touch_interceptor_view);
        mDetailsLayout = findViewById(R.id.details_layout);
        mDetailsLayout.setVisibility(View.INVISIBLE);
        mUnfoldableView = (UnfoldableView)findViewById( R.id.unfoldable_view);

        mListTouchInterceptor.setClickable(false);
       /* mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final HashMap<String, ?> item = movieData.getItem(position);
                openDetails(view, item);


            }
        });*/
        mUnfoldableView.setOnFoldingListener(new UnfoldableView.SimpleFoldingListener() {
            @Override
            public void onUnfolding(UnfoldableView unfoldableView) {
                mListTouchInterceptor.setClickable(true);
                mDetailsLayout.setVisibility(View.VISIBLE);
            }

            @Override
            public void onUnfolded(UnfoldableView unfoldableView) {
                mListTouchInterceptor.setClickable(false);
            }

            @Override
            public void onFoldingBack(UnfoldableView unfoldableView) {
                mListTouchInterceptor.setClickable(true);
            }

            @Override
            public void onFoldedBack(UnfoldableView unfoldableView) {
                mListTouchInterceptor.setClickable(false);
                mDetailsLayout.setVisibility(View.INVISIBLE);
            }
        });
    }


    @Override
    public void onBackPressed() {
        if (mUnfoldableView != null && (mUnfoldableView.isUnfolded() || mUnfoldableView.isUnfolding())) {
            mUnfoldableView.foldBack();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_unfoldable, menu);
        return true;
    }
    public void openDetails(View view,Map<String,?> movie)
    {
        ImageView _image = (ImageView)findViewById(R.id.details_image);
        TextView title =(TextView)findViewById(R.id.details_title);
        TextView description =(TextView)findViewById(R.id.details_text);
        _image.setImageResource((Integer) movie.get("image"));
        title.setText((String) movie.get("name"));
        description.setText((String) movie.get("description"));
       // mUnfoldableView.changeCoverView(view);

        mUnfoldableView.unfold(view, mDetailsLayout);



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
