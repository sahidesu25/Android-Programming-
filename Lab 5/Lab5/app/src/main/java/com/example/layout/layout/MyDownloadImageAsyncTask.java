package com.example.layout.layout;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.lang.ref.WeakReference;

/**
 * Created by Sahithi on 6/22/2015.
 */
public class MyDownloadImageAsyncTask extends AsyncTask<String,Void,Bitmap> {
    private final WeakReference<ImageView> imageViewWeakReference;
    public MyDownloadImageAsyncTask(ImageView imageView)
    {
        imageViewWeakReference=new WeakReference<ImageView>(imageView);
    }
    protected Bitmap doInBackground(String... urls)
    {
        Bitmap bitmap=null;
        for(String url:urls)
        {
            bitmap=MyUtility.downloadImage(url);
            if(bitmap!=null)
            {
RecyclerViewFragment.imagecache.put(url,bitmap);
            }
        }
        return bitmap;


    }
    protected void onPostExecute(Bitmap bitmap)
    {
     if(imageViewWeakReference!=null && bitmap!=null)
     {
         final ImageView imageView=imageViewWeakReference.get();
         if(imageView!=null)
         {
             imageView.setImageBitmap(bitmap);
         }

     }
    }


}
