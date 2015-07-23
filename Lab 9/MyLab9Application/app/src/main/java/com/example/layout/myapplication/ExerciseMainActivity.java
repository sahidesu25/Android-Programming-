package com.example.layout.myapplication;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;


public class ExerciseMainActivity extends Activity implements ActionBar.OnNavigationListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_exercise);
        getFragmentManager().beginTransaction().replace(R.id.container,Main_ActivityFragment.newInstance(1)).commit();

        // Set up the action bar to show a dropdown list.
        final ActionBar actionBar = getActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);

        // Set up the dropdown list navigation in the action bar.
        actionBar.setListNavigationCallbacks(
                // Specify a SpinnerAdapter to populate the dropdown list.
                new ArrayAdapter<String>(
                        actionBar.getThemedContext(),
                        android.R.layout.simple_list_item_1,
                        android.R.id.text1,
                        new String[] {
                                "Canvas Exercise",
                                "Thermometer Exercise",
                                "Rotary Knob Exercise",
                                "Rotatory Knob2",
                        }),
                this);
    }

    @Override
    public boolean onNavigationItemSelected(int position, long id) {

        switch (position) {
            case 0:
                getFragmentManager().beginTransaction()
                        .replace(R.id.container, ExerciseCanvasFragment.newInstance(0))
                        .commit();
                break;
            case 1:
                getFragmentManager().beginTransaction()
                        .replace(R.id.container, ExerciseThermometerFragment.newInstance(0))
                        .commit();
                break;
            case 2:
                getFragmentManager().beginTransaction()
                        .replace(R.id.container, ExerciseRotaryKnobFragment.newInstance(0))
                        .commit();
                break;
            case 3:
                getFragmentManager().beginTransaction()
                        .replace(R.id.container,RotatoryKnob2Fragment.newInstance(0)).commit();
                break;
            default:
                getFragmentManager().beginTransaction()
                        .replace(R.id.container, ExerciseCanvasFragment.newInstance(0))
                        .commit();
                break;
        }
        return true;
    }
}
