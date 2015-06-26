package com.example.layout.layout;
import android.os.AsyncTask;
import android.widget.TextView;
import java.lang.ref.WeakReference;
import java.util.HashMap;

/**
 * Created by Sahithi on 6/24/2015.
 */
public class UploadMovieAsyncTask extends AsyncTask<String,Void,String> {

private HashMap Movie=new HashMap();
    private final WeakReference<TextView> TextViewWeakReference;
    public UploadMovieAsyncTask(HashMap Movie,TextView Upload)
    {
        this.Movie=Movie;
        TextViewWeakReference=new WeakReference<TextView>(Upload);
    }
    protected String doInBackground(String...urls)
    {
      MyUtility.uploadJSON(urls[0],Movie);
        String uploadstatus="UploadComplete";

        return uploadstatus;
    }

    protected void onPostExecute(String UploadStatus)
    {

        TextView UploadText=TextViewWeakReference.get();
        UploadText.setText(UploadStatus);
    }



}
