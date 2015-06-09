package com.example.layout.layout;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
/**
 * Created by Sahithi on 6/1/2015.
 */




public class SeekBarFragment implements SeekBar.OnSeekBarChangeListener, View.OnLongClickListener, View.OnClickListener {
    SeekBar imageControlSeekBar = null;
    ImageView imageSeekBar = null;

    SeekBarFragment(SeekBar imageControlSeekBar, ImageView imageSeekBar) {
        this.imageControlSeekBar = imageControlSeekBar;
        this.imageSeekBar = imageSeekBar;
    }
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        ViewGroup.LayoutParams params = imageSeekBar.getLayoutParams();
        params.width = progress*5;
        params.height = progress*5;
        imageSeekBar.setLayoutParams(params);
    }
    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
    public void attachListenersToSeekBar(){

        imageControlSeekBar.setOnSeekBarChangeListener(this);
        imageSeekBar.setLongClickable(true);
        //imageSeekBar.setOnClickListener(this);
        imageSeekBar.setOnLongClickListener(this);

    }

    @Override
    public boolean onLongClick(View v) {
        int maxProgress = imageControlSeekBar.getMax();
        int minProgress = 0;
        int centerProgressOfSeekBar = (maxProgress+minProgress)/2;
        imageControlSeekBar.setProgress(centerProgressOfSeekBar);
        onProgressChanged(imageControlSeekBar,centerProgressOfSeekBar,true);

        return true;
    }

    @Override
    public void onClick(View v) {
        int maxProgress = imageControlSeekBar.getMax();
        int minProgress = 0;
        int centerProgressOfSeekBar = (maxProgress+minProgress)/2;
        imageControlSeekBar.setProgress(centerProgressOfSeekBar);
        onProgressChanged(imageControlSeekBar,centerProgressOfSeekBar,true);


    }
}

