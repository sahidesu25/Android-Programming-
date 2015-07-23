package com.example.layout.mylab8application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DrawerData {

    List<Map<String,?>> drawerlist;

    public List<Map<String, ?>> getDrawerlist() {
        return drawerlist;
    }

    public int getSize(){
        return drawerlist.size();
    }

    public HashMap getItem(int i){
        if (i >=0 && i < drawerlist.size()){
            return (HashMap) drawerlist.get(i);
        } else return null;
    }

    public DrawerData() {
		String title;
		int image;
		int type;
		drawerlist=new ArrayList<Map<String,?>>();
		title=null;
		image=R.drawable.drawable_header;
		type=0;
		drawerlist.add(createDrawer(title,image,type));

		title="RemoteControlActivity";
		image=R.drawable.demoicon;
		type=1;
		drawerlist.add(createDrawer(title,image,type));
		title="Recycler View";
		image=R.drawable.listicon;
		type=1;
		drawerlist.add(createDrawer(title,image,type));
		title="Movie_DragAndDrop";
		//image=R.mipmap.icon;
		type=1;
		drawerlist.add(createDrawer(title,image,type));
		title=null;
		image=R.drawable.divider;
		type=2;
		drawerlist.add(createDrawer(title,image,type));
		title="MyselfFragment";
		type=3;
		drawerlist.add(createDrawer(title,-1,type));




	}
	private HashMap createDrawer(String title, int image, int type)
	{
		HashMap drawer=new HashMap();
		drawer.put("title",title);
		drawer.put("image",image);
		drawer.put("type",type);
		return drawer;
	}



}
