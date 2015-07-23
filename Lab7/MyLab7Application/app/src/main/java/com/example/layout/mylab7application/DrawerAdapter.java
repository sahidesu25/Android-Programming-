package com.example.layout.mylab7application;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Sahithi on 7/7/2015.
 */
public class DrawerAdapter extends RecyclerView.Adapter<DrawerAdapter.MyViewHolder> {

    private List<Map<String, ?>> drawerList;
    private Context mContext;
    OnItemClickListener mlistener;
    int mCurrentPosition = -1;

    public DrawerAdapter(Context context, List<Map<String, ?>> drawList) {
        this.drawerList = drawList;
        mContext = context;
    }

    public int getItemCount() {
        return drawerList.size();
    }

    public HashMap getItemAtPosition(int position) {
        if (position >= 0 && position < drawerList.size()) {
            return (HashMap) drawerList.get(position);
        } else return null;
    }
    public interface OnItemClickListener
    {
        public void OnItemClick(View v, int position);
    }

    @Override
    public void onBindViewHolder(DrawerAdapter.MyViewHolder myViewHolder, int position) {

        Map<String, ?> drawerMap = drawerList.get(position);
        myViewHolder.bindDrawerData(drawerMap, position);

    }
    public void setOnItemClickListener(final OnItemClickListener mlistener )
    {
        this.mlistener=mlistener;
    }

    public DrawerAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int ViewType) {
        View v;
        switch (ViewType) {
            case 0:
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.drawerlayout1, parent, false);
                break;
            case 1:
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.drawerlayout2, parent, false);
                break;
            case 2:
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.drawerlayout3, parent, false);
                break;
            case 3:
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.drawerlayout4, parent, false);
                break;
            default:
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.drawerlayout1, parent, false);
                break;


        }
        MyViewHolder viewHolder = new MyViewHolder(v, ViewType);

        return viewHolder;
    }

    @Override
    public int getItemViewType(int position) {
        Map<String, ?> item = drawerList.get(position);
        return (Integer) item.get("type");
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        View v;
        TextView title;
        ImageView image;
        int type;

        MyViewHolder(View view, int type) {
            super(view);
            this.v = view;

            title = (TextView) v.findViewById(R.id.drawerTitle);
            image = (ImageView) v.findViewById(R.id.drawerImage);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mlistener != null) {
                        mlistener.OnItemClick(v, getPosition());
                    }
                    mCurrentPosition=getPosition();
                    notifyDataSetChanged();
                }
            });
        }

        public void bindDrawerData(Map<String, ?> drawerMap, int position) {

            if (position == mCurrentPosition && position != 0 && position != 4) {
                v.setBackgroundColor(Color.GRAY);
            } else {
                v.setBackgroundColor(0x00000000);
            }

            if (drawerMap.get("title") != null && title != null) {
                title.setText((String) drawerMap.get("title"));
            }
            if ((Integer) drawerMap.get("image") != -1 && image != null) {
                image.setImageResource((Integer) drawerMap.get("image"));
            }
        }


    }
}
