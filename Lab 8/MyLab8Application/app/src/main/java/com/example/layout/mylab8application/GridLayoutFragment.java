package com.example.layout.mylab8application;

/**
 * Created by Sahithi on 7/15/2015.
 */

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.ClipData;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.DragEvent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;



import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * A placeholder fragment containing a simple view.
 */
public class GridLayoutFragment extends Fragment {
    public static final String ARG_OPTION="argument_option";
    GridLayout gridLayout;
    List<Map<String,?>> movielist;
    public static GridLayoutFragment newInstance(int option)
    {
        GridLayoutFragment fragment=new GridLayoutFragment();
        Bundle args=new Bundle();
        args.putInt(ARG_OPTION,option);
        fragment.setArguments(args);
        return fragment;

    }

    public GridLayoutFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.grid_layout_image, container, false);
        gridLayout=(GridLayout)view.findViewById(R.id.mygrid);
        addMoviestoList();
        setGridLayout(gridLayout);



        return view;
    }
    private void setGridLayout(GridLayout gl) {

       for(int i=0;i<movielist.size();i++)
      {
          Map<String,?> movie=movielist.get(i);
          gl.addView(getImageView(((Integer)movie.get("image")),i));

      }

        }

    private ImageView getImageView(int resource,int i) {
        ImageView iv = new ImageView(getActivity());
        iv.setImageResource(resource);
        iv.setId(i);

        GridLayout.LayoutParams param =new GridLayout.LayoutParams();
        param.height = 200;
        param.width = 200;
        param.rightMargin = 5;
        param.topMargin = 5;
        param.setGravity(Gravity.VERTICAL_GRAVITY_MASK);
       // param.columnSpec = GridLayout.spec(c);
       // param.rowSpec = GridLayout.spec(r);
        iv.setLayoutParams(param);
        iv.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
                v.startDrag(data, shadowBuilder, v, 0);
                return true;
            }
        });
        iv.setOnDragListener(new MyDragListener());
        return iv;

    }
    public  final class MyDragListener implements View.OnDragListener{
       int icon;
        public boolean onDrag(View view, DragEvent event)
        {
            int id=view.getId();
            Map<String,?>movie=movielist.get(id);
             icon=(Integer)movie.get("image");
          int action=event.getAction();
            switch(action) {
                case DragEvent.ACTION_DRAG_STARTED:
                    break;
                case DragEvent.ACTION_DRAG_ENTERED:
                    Log.d("Ondrag", "Drag Entered");
                    Drawable layers[] = new Drawable[2];
                   layers[0] = ContextCompat.getDrawable(getActivity(), icon);
                    layers[1] = ContextCompat.getDrawable(getActivity(), R.drawable.drag_marker);
                    final LayerDrawable layerDrawable = new LayerDrawable(layers);
                    layerDrawable.setLayerInset(1, 0,0,0,0);
                    if (view instanceof ImageView) {
                        ImageView imageView = (ImageView) view;
                        imageView.setImageDrawable(layerDrawable);
                    }
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    Log.d("Ondrag", "Drag Excited");
                    if (view instanceof ImageView) {
                        ImageView imageView = (ImageView) view;
                        GridLayout.LayoutParams param =new GridLayout.LayoutParams();
                        param.height = 200;
                        param.width = 200;
                        param.rightMargin = 5;
                        param.topMargin = 5;
                        param.setGravity(Gravity.FILL_VERTICAL);
                        // param.columnSpec = GridLayout.spec(c);
                        // param.rowSpec = GridLayout.spec(r);
                        imageView.setLayoutParams(param);
                        //imageView.
                        imageView.setImageResource(icon);

                    }
                    break;
                case DragEvent.ACTION_DROP:
                    Log.d("OnDrag", "DragDrop");
                    String s = Integer.toString(view.getId());
                    if (view instanceof ImageView) {
                        ImageView imageView = (ImageView) view;
                        imageView.setImageResource(icon);
                    }

                    View localview = (View) event.getLocalState();
                    ViewGroup owner = (ViewGroup) localview.getParent();
                    int indexFrom = owner.indexOfChild(localview);
                    int indexTo = owner.indexOfChild(view);

                    owner.removeView(localview);
                    owner.addView(localview, indexTo);
                    owner.removeView(view);
                    owner.addView(view, indexFrom);
                    ObjectAnimator animator4 = ObjectAnimator.ofFloat(localview, "translationX", -200f);
                    animator4.setRepeatCount(0);
                    animator4.setDuration(1000);

                    ObjectAnimator animator5 = ObjectAnimator.ofFloat(localview, "translationX", 100f);
                    animator5.setRepeatCount(0);
                    animator5.setDuration(1000);

                    ObjectAnimator animator6 = ObjectAnimator.ofFloat(localview, "translationX", 0f);
                    animator6.setRepeatCount(0);
                    animator6.setDuration(1000);
                    ObjectAnimator rotation = ObjectAnimator.ofFloat(localview, "rotation", 360);
                    rotation.setDuration(2000);


                    AnimatorSet set1 = new AnimatorSet();
                    set1.play(animator4).before(animator5);
                    set1.play(rotation).before(animator6);
                    set1.play(animator5).before(animator6);

                    set1.start();
                    break;
                case DragEvent.ACTION_DRAG_ENDED:
                    Log.d("OnDrag", "DragEnded");
                    break;
                default:
                    break;
            }

            return true;
        }


    }

    private  void addMoviestoList()
    {
        movielist=new ArrayList<Map<String,?>>();
        movielist.add(createMovie(0,R.drawable.titanic));
        movielist.add(createMovie(1,R.drawable.avatar));
        movielist.add(createMovie(2,R.drawable.avengers));
        movielist.add(createMovie(3,R.drawable.transformers));
        movielist.add(createMovie(4,R.drawable.transformers2));
        movielist.add(createMovie(5,R.drawable.spiderman));
        movielist.add(createMovie(6,R.drawable.forrest_gump));
        movielist.add(createMovie(7,R.drawable.spiderman2));
        movielist.add(createMovie(8,R.drawable.shrek2));
        movielist.add(createMovie(9,R.drawable.dark_knight_rises));

    }
    private HashMap createMovie(int id, int image)
    {
        HashMap movie=new HashMap();
        movie.put("id", id);
        movie.put("image",image);

        return movie;
    }

}


