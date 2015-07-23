package com.example.layout.mylab8application;
import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

/**
 * Created by kevin on 6/21/2014.
 */
public class RemoteControlActivity extends Activity implements ActionBar.OnNavigationListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_ACTION_BAR);
        setContentView(R.layout.remote_activity);

        // Set up the action bar to show a dropdown list.
        final ActionBar actionBar = getActionBar();
        //actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);

        // Set up the dropdown list navigation in the action bar.
        actionBar.setListNavigationCallbacks(
                // Specify a SpinnerAdapter to populate the dropdown list.
                new ArrayAdapter<String>(
                        actionBar.getThemedContext(),
                        android.R.layout.simple_list_item_1,
                        android.R.id.text1,
                        new String[] {
                                "Plain Style",
                                "Appealing Style"
                        }),
                this);
    }

    @Override
    public boolean onNavigationItemSelected(int position, long id) {
        getFragmentManager().beginTransaction()
                .replace(R.id.container, RemoteControlFragment.newInstance(position))
                .commit();
        overridePendingTransition(R.animator.slide_in_bottom, R.animator.slide_out_top);
        return true;
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class RemoteControlFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static RemoteControlFragment newInstance(int sectionNumber) {
            RemoteControlFragment fragment = new RemoteControlFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public RemoteControlFragment() {
        }

        private TextView mSelectedTextView;
        private TextView mWorkingTextView;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            final View rootView;
            int choice = getArguments().getInt(ARG_SECTION_NUMBER);

            if (choice ==0)
                rootView = inflater.inflate(R.layout.fragment_remote_control_plain, container, false);
            else
                rootView = inflater.inflate(R.layout.fragment_remote_control_improved, container, false);


            mSelectedTextView = (TextView) rootView.findViewById(R.id.fragment_remote_control_selectedTextView);
            mWorkingTextView = (TextView) rootView.findViewById(R.id.fragment_remote_control_workingTextView);

            View.OnClickListener numberButtonListener = new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    TextView textView = (TextView) view;
                    String working = mWorkingTextView.getText().toString();
                    String text = textView.getText().toString();
                    if (working.equals("0")) {
                        mWorkingTextView.setText(text);
                    } else {
                        mWorkingTextView.setText(working + text);
                    }
                }
            };

            TableLayout tableLayout = (TableLayout) rootView.findViewById(R.id.fragment_remote_control_tableLayout);

            int number =1;
            for (int i=2; i < tableLayout.getChildCount() - 1; i++) {
                TableRow row = (TableRow)tableLayout.getChildAt(i);
                for (int j=0; j < row.getChildCount(); j++){
                    Button button = (Button)row.getChildAt(j);
                    button.setText(""+number);
                    button.setOnClickListener(numberButtonListener);
                    number ++;
                }
            }

            TableRow bottomRow = (TableRow) tableLayout.getChildAt(tableLayout.getChildCount() -1);
            Button deleteButton = (Button)bottomRow.getChildAt(0);
            deleteButton.setText("Delete");
            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mWorkingTextView.setText("0");
                }
            });

            Button zeroButton = (Button) bottomRow.getChildAt(1);
            zeroButton.setText("0");
            zeroButton.setOnClickListener(numberButtonListener);

            Button enterButton = (Button) bottomRow.getChildAt(2);
            enterButton.setText("Enter");
            enterButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    CharSequence working = mWorkingTextView.getText();
                    if (working.length() > 0)
                        mSelectedTextView.setText(working);
                    mWorkingTextView.setText("0");
                }
            });

            return rootView;
        }
    }

}
