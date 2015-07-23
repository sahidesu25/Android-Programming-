package com.example.layout.myapplication;

import android.app.Fragment;
import android.content.pm.ActivityInfo;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.ToggleButton;

/**
 * Created by kevin on 7/6/2014.
 */
public class ExerciseThermometerFragment extends Fragment implements SensorEventListener {

    private ExerciseThermometerView thermometer;
    private SensorManager mSensorManager;
    private float azimut;
    private float[] gravity;
    private float[] geoMagnetic;
    private Sensor accSensor;
    private Sensor magnetSensor;
    private boolean useAsCompass = false;

    public static ExerciseThermometerFragment newInstance(int sectionNumber) {
        ExerciseThermometerFragment fragment = new ExerciseThermometerFragment();
        return fragment;
    }

    public ExerciseThermometerFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        // initialize your android device sensor capabilities
        mSensorManager = (SensorManager) getActivity().getSystemService(getActivity().SENSOR_SERVICE);

        // force it to be in portrait mode, or the sensor reading will be wrong.
        if (useAsCompass)
            getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
    }


    @Override
    public void onResume() {
        accSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        magnetSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);

        mSensorManager.registerListener(this, accSensor, SensorManager.SENSOR_DELAY_UI);
        mSensorManager.registerListener(this, magnetSensor, SensorManager.SENSOR_DELAY_UI);
        super.onResume();
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        /*get gravity value arrays from Accelerometer*/
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER)
            gravity = event.values;
        /*get gravity value arrays from Magnet*/
        if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD)
            geoMagnetic = event.values;
        if (gravity != null && geoMagnetic != null) {
		    /*Rotation matrix and Inclination matrix*/
            float R[] = new float[9];
            float I[] = new float[9];
            /* Compute the inclination matrix I as well as the rotation matrix R transforming
                a vector from the device
	            coordinate system to the world's coordinate system R and I [Length 9]
                gravity vector expressed in the device's coordinate [Length 3]
                geoMagnetic vector expressed in the device's coordinate[Length 3]
            */
            boolean success = SensorManager.getRotationMatrix(R, I, gravity, geoMagnetic);

            if (success) {
		     /* Orientation has azimuth, pitch and roll */
                float orientation[] = new float[3];
                SensorManager.getOrientation(R, orientation);
                azimut = orientation[0];
                if (thermometer != null && useAsCompass)
                    thermometer.setHandTarget(-azimut * 360 / (2 * 3.14159f));
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_exercise_themometer, container, false);

        thermometer = (ExerciseThermometerView)
                        rootView.findViewById(R.id.thermometer);

        SeekBar seekBar = (SeekBar) rootView.findViewById(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if (thermometer != null){
                    thermometer.setHandTarget(i);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        ToggleButton toggleButton = (ToggleButton) rootView.findViewById(R.id.toggleButton);
        toggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean on = ((ToggleButton) view).isChecked();

                if (on) {
                    useAsCompass = true;
                } else {
                    useAsCompass = false;
                }
            }
        });

        return rootView;
    }
}