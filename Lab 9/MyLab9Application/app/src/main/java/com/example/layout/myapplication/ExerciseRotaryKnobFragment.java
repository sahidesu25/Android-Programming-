package com.example.layout.myapplication;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by kevin on 7/6/2014.
 */
public class ExerciseRotaryKnobFragment extends Fragment {

    private Canvas canvas;
    private Bitmap backgroundBitmap;

    public static ExerciseRotaryKnobFragment newInstance(int sectionNumber) {
        ExerciseRotaryKnobFragment fragment = new ExerciseRotaryKnobFragment();
        return fragment;
    }

    public ExerciseRotaryKnobFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_exercise_rotaryknob, container, false);

        final ExerciseRotaryKnobView rotaryKnob1 = (ExerciseRotaryKnobView) rootView.findViewById(R.id.rotaryKnobView);
        final ExerciseRotaryKnobView rotaryKnob2 = (ExerciseRotaryKnobView) rootView.findViewById(R.id.rotaryKnobView2);

        rotaryKnob1.setOnRotateListener(new ExerciseRotaryKnobView.OnRotateListener() {
            @Override
            public void onRotate(int arg) {
                if (rotaryKnob2 != null)
                    rotaryKnob2.setAngle(arg);
            }
        });

        rotaryKnob2.setOnRotateListener(new ExerciseRotaryKnobView.OnRotateListener() {
            @Override
            public void onRotate(int arg) {
                if (rotaryKnob1 != null)
                    rotaryKnob1.setAngle(arg);
            }
        });


        return rootView;
    }

}