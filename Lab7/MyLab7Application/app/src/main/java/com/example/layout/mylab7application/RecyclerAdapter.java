package com.example.layout.mylab7application;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.view.View;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Sahithi on 6/13/2015.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

private List<Map<String,?>> mDataSet;
private Context mContext;
    OnItemClickListener mlistener;

    public RecyclerAdapter(Context context,List<Map<String,?>> mDataSet)
    {
        this.mDataSet=mDataSet;
       mContext=context;
    }
    public int getItemCount(){
        return mDataSet.size();
    }
    public HashMap getItemAtPosition(int position){
        if (position >=0 && position < mDataSet.size()){
            return (HashMap) mDataSet.get(position);
        } else return null;
    }
    public RecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,int ViewType)
    {
        View v;
        if(ViewType==0)
        {
            v= LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.recyclerrow,parent,false);
        }
        else
        {
            v= LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.recyclerow2,parent,false);
        }

        ViewHolder vh=new ViewHolder(v);
        return vh;
    }

    public void onBindViewHolder(ViewHolder holder,int position)
    {
        Map<String,?> movie=mDataSet.get(position);
        holder.bindMovieData(movie);
    }

    public int getItemViewType(int position){
        Map<String,?> map=mDataSet.get(position);
        Double _rating=(Double)map.get("rating");
       if( _rating>=8.0)
        return 0;
        else
       return 1;


    }

    public interface OnItemClickListener
    {
        public void OnItemClick(View v, int position);
        public void OnItemLongClick(View v, int position);
        public void OnOverFlowMenuClick(View v, int position);
    }
    public void setOnItemClickListener(final OnItemClickListener mlistener )
    {
        this.mlistener=mlistener;
    }
    public void  removeItemsFromList(List<Integer> selectedList)
    {int count=0;
        if(selectedList.size()>=mDataSet.size())
        {
           mDataSet.clear();
        }
        else
        {
            for(int position:selectedList)
            {
                mDataSet.remove(position - (count++));
            }
        }
    }

    public void duplicateItem(int position)
    {
        if(position<mDataSet.size())
        {
            final HashMap<String, ?> item = (HashMap<String, ?>) mDataSet.get(position);

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

            mDataSet.add(position + 1, movie);

        }
    }
    public class ViewHolder extends RecyclerView.ViewHolder{


        TextView name;
        ImageView image;
        TextView description;
        RatingBar rating;
        ImageView overflow;
        CardView cardView;
        public ViewHolder(View v)
        {
            super(v);

            cardView= (CardView)v.findViewById(R.id.cardview);
            name=(TextView) v.findViewById(R.id.name);
            image=(ImageView) v.findViewById((R.id.image));
            description=(TextView) v.findViewById((R.id.des));
            rating=(RatingBar) v.findViewById(R.id.rating);
            overflow=(ImageView) v.findViewById(R.id.overflow);
            cardView.setCardBackgroundColor(Color.TRANSPARENT);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mlistener != null) {
                        mlistener.OnItemClick(v, getPosition());
                    }
                }
            });
            v.setOnLongClickListener(new View.OnLongClickListener(){
                public boolean onLongClick(View v)
                {
                    if (mlistener != null) {
                        mlistener.OnItemLongClick(v, getPosition());
                    }
                    return true;
                }
            });
            if(overflow!=null)
            {
                overflow.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if(mlistener!=null){
                            mlistener.OnOverFlowMenuClick(v,getPosition());
                        }
                    }
                });
            }



        }

public void bindMovieData(Map<String,?> item)
{
    image.setImageResource((Integer) item.get("image"));
    name.setText((String) item.get("name"));
    description.setText((String) item.get("description"));
    Double rating_ =(Double)item.get("rating");
    rating.setRating((float) (rating_ / 2));

   //final  Map<String, Boolean> checkitem = (Map<String, Boolean>) item;

}


    }
}
