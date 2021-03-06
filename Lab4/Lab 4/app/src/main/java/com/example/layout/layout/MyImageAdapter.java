package com.example.layout.layout;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RatingBar;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Sahithi on 6/7/2015.
 */
public class MyImageAdapter extends BaseAdapter {
    private Context context;
    private final List<Map<String, ?>> movieList;

    public MyImageAdapter(Context context, List<Map<String, ?>> moviesList) {
        this.context = context;
        this.movieList = moviesList;
    }

    public int getCount() {
        return movieList.size();
    }

    public Object getItem(int position) {
        return movieList.get(position);
    }

    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View view, ViewGroup parent) {
        View rowView = null;
        ViewHolder holder=null;
        if(view==null)
        {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.gridelement, parent, false);
            holder= new ViewHolder();
            holder.image=(ImageView) rowView.findViewById((R.id.GridImage));
            holder.rating=(RatingBar) rowView.findViewById(R.id.GridRatings);
            rowView.setTag(holder);
        }
        else
        {
            rowView=view;
            holder=(ViewHolder)view.getTag();
        }
        if(position%2==0)
        {
            rowView.setBackgroundResource(R.drawable.odd);


        }
        else
        {
            rowView.setBackgroundResource(R.color.dim_foreground_disabled_material_light);
        }
        Map<String,?> item=movieList.get(position);
        holder.image.setImageResource((Integer) item.get("image"));
        Double rating =(Double)item.get("rating");
        holder.rating.setRating((float) (rating / 2));

        return rowView;
    }
}
