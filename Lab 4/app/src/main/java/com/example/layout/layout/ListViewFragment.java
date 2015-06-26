package com.example.layout.layout;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;


/**
 * A placeholder fragment containing a simple view.
 */
public class ListViewFragment extends Fragment {
   public static final String ARG_OPTION="argument_option";
    public  MovieDataJsonLocal movieData=new MovieDataJsonLocal();

    public static ListViewFragment newInstance(int option)
    {
        ListViewFragment fragment=new ListViewFragment();
        Bundle args=new Bundle();
        args.putInt(ARG_OPTION, option);
        fragment.setArguments(args);




        return fragment;

    }


    public ListViewFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

       View view=null;
       view=inflater.inflate(R.layout.listfragment, container, false);
        ListView listView=(ListView) view.findViewById(R.id.listview1);
        movieData.loadLocalMovieDataJson(getActivity());
        List<Map<String,?>> moviesList=movieData.getMoviesList();
        final MyBaseAdapter myBaseAdapter=new MyBaseAdapter(getActivity(),moviesList);

        listView.setAdapter(myBaseAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final HashMap<String, ?> item = (HashMap<String, ?>) parent.getItemAtPosition(position);
                final String name = (String) item.get("name");
                Toast.makeText(getActivity(), "Selected" + name, Toast.LENGTH_SHORT).show();

                CheckBox checkBox = (CheckBox) view.findViewById(R.id.checkbox1);
                final HashMap<String, Boolean> checkitem = (HashMap<String, Boolean>) item;
                checkitem.put("selection", !checkBox.isChecked());
                checkBox.setChecked(!checkBox.isChecked());


            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            public boolean onItemLongClick(AdapterView<?> parent,View view,int position,long id)
            {

                myBaseAdapter.duplicateItem(position);
                myBaseAdapter.notifyDataSetChanged();

                HashMap<String,Boolean > selectoritem=(HashMap<String,Boolean>) myBaseAdapter.getItem(position+1);
                selectoritem.put("selection", false);
                myBaseAdapter.notifyDataSetChanged();

                return true;
            }

        });
        Button selectAll=(Button) view.findViewById(R.id.selectall);
        selectAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i=0;i<myBaseAdapter.getCount();i++)
                {
                    if(myBaseAdapter.isEnabled(i))
                    {
                        HashMap<String,Boolean > selectoritem=(HashMap<String,Boolean>) myBaseAdapter.getItem(i);
                        selectoritem.put("selection",true);

                    }


                }
                myBaseAdapter.notifyDataSetChanged();
            }
        });
        Button ClearAll=(Button)view.findViewById(R.id.clearall);
        ClearAll.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                for (int i = 0; i < myBaseAdapter.getCount(); i++)

                {
                    HashMap<String, Boolean> selectoritem = (HashMap<String, Boolean>) myBaseAdapter.getItem(i);

                    if ((Boolean) selectoritem.get("selection")) {
                        selectoritem.put("selection", false);

                    }
                }

                myBaseAdapter.notifyDataSetChanged();
            }


        });
        Button Delete=(Button) view.findViewById(R.id.delete);
        Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Integer> deletionIndexList = new ArrayList<Integer>();
                for (int i = 0; i < myBaseAdapter.getCount(); i++) {
                    HashMap<String, Boolean> selectoritem = (HashMap<String, Boolean>) myBaseAdapter.getItem(i);
                    if ((Boolean) selectoritem.get("selection")) {
                        deletionIndexList.add(i);

                    }

                }
                myBaseAdapter.removeItemsFromList(deletionIndexList);
                myBaseAdapter.notifyDataSetChanged();
            }
        });

        return  view;
    }
}
