package com.example.layout.mylab7application;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


/**
 * A placeholder fragment containing a simple view.
 */
public class DatePickerActivityFragment extends Fragment {
    public static final String ARG_OPTION="argument_option";
    private  Date mdate;
    TextView email;
    TextView name;
    Button okay;
    public static final String ARGS_DATE = "argument_date";
    public static final String ARGS_EMAIL = "argument_email";
    public static final String ARGS_NAME = "argument_name";
    public static DatePickerActivityFragment newInstance(Date date)
    {
        DatePickerActivityFragment fragment=new DatePickerActivityFragment();
        Bundle args=new Bundle();

       args.putSerializable(ARG_OPTION,date);
        fragment.setArguments(args);
        return fragment;

    }

    public DatePickerActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_date_picker, container, false);
        mdate=(Date)getArguments().getSerializable(ARG_OPTION);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(mdate);

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePicker datePicker = (DatePicker) view.findViewById(R.id.datePickerActivity);

        email = (TextView) view.findViewById(R.id.EmailActivity);
        name = (TextView) view.findViewById(R.id.nameActivity);
        okay = (Button) view.findViewById(R.id.Done);
        datePicker.init(year, month, day, new DatePicker.OnDateChangedListener() {
            public void onDateChanged(DatePicker view, int year, int month, int day) {
                mdate = new GregorianCalendar(year, month, day).getTime();
                getArguments().putSerializable(ARGS_DATE, mdate);
            }
        });
        okay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.putExtra(ARGS_DATE,mdate);
                intent.putExtra(ARGS_NAME,name.getText().toString());
                intent.putExtra(ARGS_EMAIL,email.getText().toString());
                getActivity().setResult(Activity.RESULT_OK,intent);
                getActivity().finish();

            }
        });


        return view;
    }
}


