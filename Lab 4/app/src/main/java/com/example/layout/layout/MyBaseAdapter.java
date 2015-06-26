package com.example.layout.layout;
import android.graphics.Color;
import android.widget.BaseAdapter;
import android.content.Context;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.support.v7.widget.CardView;

/**
 * Created by Sahithi on 6/5/2015.
 */
public class MyBaseAdapter extends BaseAdapter {
    private final Context context;
    private final List<Map<String,?>> movieList;
    public MyBaseAdapter(Context context,List<Map<String,?>> moviesList)
    {
        this.context=context;
        this.movieList=moviesList;
    }

    public boolean isEnabled(int position)
    {
        if(position==0)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    public int getCount()
    {
        return movieList.size();
    }
    public Object getItem(int position)
    {
        return movieList.get(position);
    }
    public long getItemId(int position)
    {
        return 0;
    }
    public void  removeItemsFromList(List<Integer> selectedList)
    {int count=0;
        if(selectedList.size()>=movieList.size())
        {
            this.movieList.clear();
        }
        else
        {
            for(int position:selectedList)
            {
                if(this.isEnabled(position))
                {
                    this.movieList.remove(position-(count++));



                }
            }
        }
    }
    public void duplicateItem(int position)
    {
        if(position<this.movieList.size())
        {
            final HashMap<String, ?> item = (HashMap<String, ?>) movieList.get(position);

            HashMap movie = new HashMap();
            movie.put("image",item.get("image"));
            movie.put("name", item.get("name"));
            movie.put("description", item.get("description"));
            movie.put("year", item.get("year"));
            movie.put("length",item.get("length"));
            movie.put("rating",item.get("rating"));
            movie.put("director",item.get("director"));
            movie.put("stars",item.get("starts"));
            movie.put("url",item.get("url"));
            movie.put("selection",false);

            movieList.add(position+1,movie);

        }
      }
    public View getView(int position, View view, ViewGroup parent) {
        View rowView;
        ViewHolder holder=null;
        if(view==null)
        {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.recyclerrow, parent, false);
            holder= new ViewHolder();
            //CardView cardLayout = (CardView)rowView.findViewById(R.id.cardview);
            holder.cardView= (CardView)rowView.findViewById(R.id.cardview);
            holder.name=(TextView) rowView.findViewById(R.id.name);
            holder.image=(ImageView) rowView.findViewById((R.id.image));
            holder.description=(TextView) rowView.findViewById((R.id.des));
            holder.rating=(RatingBar) rowView.findViewById(R.id.rating);
            holder.selector=(CheckBox) rowView.findViewById(R.id.checkbox1);
            rowView.setTag(holder);
        }
        else {
            rowView = view;
           // View cardLayout = (CardView)rowView.findViewById(R.id.cardview);
            holder=(ViewHolder)view.getTag();
        }
        if(position%2==0)
        {
           holder.cardView.setCardBackgroundColor(R.drawable.odd);


        }
        else
        {
            holder.cardView.setCardBackgroundColor(Color.TRANSPARENT);
        }
        Map<String,?> item=movieList.get(position);
        holder.image.setImageResource((Integer) item.get("image"));
        holder.name.setText((String) item.get("name"));
        holder.description.setText((String) item.get("description"));
        Double rating =(Double)item.get("rating");
        holder.rating.setRating((float)(rating/ 2));


        holder.selector.setChecked((Boolean)item.get("selection"));

        return rowView;
    }
}



