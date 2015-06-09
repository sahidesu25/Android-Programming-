package com.example.layout.layout;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ImageView;

import android.widget.RelativeLayout;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import android.widget.Toast;


/**
 * A placeholder fragment containing a simple view.
 */
public class MovieLayoutFragment extends Fragment {
    public static final String ARG_OPTION = "argument_option";

    public static MovieLayoutFragment newInstance(int option) {
        MovieLayoutFragment fragment4 = new MovieLayoutFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_OPTION, option);
        fragment4.setArguments(args);


        return fragment4;

    }

    public MovieLayoutFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.movielayout, container, false);

        HorizontalScrollView scrollview = (HorizontalScrollView) view.findViewById(R.id.scrollView);
        RelativeLayout layout=(RelativeLayout)view.findViewById(R.id.layout1);
        ViewGroup.LayoutParams layoutparam=layout.getLayoutParams();



        MovieData mdata=new MovieData();
        List<Map<String, ?>> moviesList=mdata.getMoviesList();
        HashMap[]items=new HashMap[moviesList.size()];
        ImageButton [] imageButtons=new ImageButton[moviesList.size()];
        RelativeLayout.LayoutParams [] lps=new RelativeLayout.LayoutParams[moviesList.size()];


         for(int i=0;i<moviesList.size();i++)
         {
             items[i]=mdata.getItem(i);
             imageButtons[i]=new ImageButton(getActivity());
             imageButtons[i].setId(imageButtons[i].generateViewId());
             int id=(Integer)items[i].get("image");
             imageButtons[i].setImageResource(id);
              lps[i]=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.FILL_PARENT);
            if(i!=0)
            {
                lps[i].addRule(RelativeLayout.RIGHT_OF, imageButtons[i-1].getId());
            }
             imageButtons[i].setLayoutParams(lps[i]);
             imageButtons[i].setAdjustViewBounds(true);
             imageButtons[i].setScaleType(ImageView.ScaleType.FIT_XY);
             imageButtons[i].setPadding(20, 20, 20, 20);
             layout.addView(imageButtons[i]);
             Toast.makeText(getActivity().getApplicationContext(), "Click On any Image to go to the Movie Info Page",
                     Toast.LENGTH_SHORT).show();

             imageButtons[i].setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     getFragmentManager().beginTransaction().replace(R.id.fragment,MovieInfoFile.newInstance(5)).addToBackStack(null).commit();
                 }
             });



         }





        return view;
    }



}

