package com.example.layout.myapplication;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by kevin on 7/6/2014.
 */
public class RotatoryKnob2Fragment extends Fragment {

    private Canvas canvas;
    private Bitmap backgroundBitmap;
    TextView angleDisplay;

    public static RotatoryKnob2Fragment newInstance(int sectionNumber) {
        RotatoryKnob2Fragment fragment = new RotatoryKnob2Fragment();
        return fragment;
    }

    public RotatoryKnob2Fragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_rotatoryknob, container, false);

        final RotatoryKnobView2 rotaryKnob1 = (RotatoryKnobView2) rootView.findViewById(R.id.rotaryKnobView);
        final RotatoryKnobView2 rotaryKnob2 = (RotatoryKnobView2) rootView.findViewById(R.id.rotaryKnobView2);
        final RotatoryKnobView2 rotatoryKnob3=(RotatoryKnobView2)rootView.findViewById(R.id.rotaryKnobView3);
        angleDisplay=(TextView)rootView.findViewById(R.id.angle);

        rotaryKnob1.setOnRotateListener(new RotatoryKnobView2.OnRotateListener() {
            @Override
            public void onRotate(int arg) {
                if (rotaryKnob2 != null)
                    rotaryKnob2.setAngle(arg);
                if (rotatoryKnob3 != null)
                    rotatoryKnob3.setAngle(arg);

                if(arg<55 )
                {
                    angleDisplay.setText(" ");

                }
               else if(arg>287)
                {

                    angleDisplay.setText(" ");
                }

                else
                {

                    arg=-(arg-285);
                    angleDisplay.setText(Integer.toString(arg));
                }


            }
        });

        rotaryKnob2.setOnRotateListener(new RotatoryKnobView2.OnRotateListener() {
            @Override
            public void onRotate(int arg) {
                if (rotaryKnob1 != null)
                    rotaryKnob1.setAngle(arg);
                if (rotatoryKnob3 != null)
                    rotatoryKnob3.setAngle(arg);
                if(arg<55 )
                {
                    angleDisplay.setText(" ");

                }
                else if(arg>287)
                {

                    angleDisplay.setText(" ");
                }

                else
                {

                    arg=-(arg-285);
                    angleDisplay.setText(Integer.toString(arg));
                }
            }
        });
        rotatoryKnob3.setOnRotateListener(new RotatoryKnobView2.OnRotateListener() {
            @Override
            public void onRotate(int arg) {
                if (rotaryKnob1 != null)
                    rotaryKnob1.setAngle(arg);
                if (rotaryKnob2 != null)
                    rotaryKnob2.setAngle(arg);
                if(arg<55 )
                {
                    angleDisplay.setText(" ");

                }
                else if(arg>287)
                {

                    angleDisplay.setText(" ");
                }

                else
                {

                    arg=-(arg-285);
                    angleDisplay.setText(Integer.toString(arg));
                }
            }
        });


        return rootView;
    }

}