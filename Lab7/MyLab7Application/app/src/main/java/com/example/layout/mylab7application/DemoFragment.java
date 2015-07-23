package com.example.layout.mylab7application;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import java.util.Date;


/**
 * A placeholder fragment containing a simple view.
 */
public class DemoFragment extends Fragment {
    public static final String ARG_OPTION="argument_option";
    public static final int REQUEST_DATE=0;
    public static final int PICK_CONTACT_REQUEST=1;
    TextView resulttext;
    public static DemoFragment newInstance(int option)
    {
        DemoFragment fragment=new DemoFragment();
        Bundle args=new Bundle();
        args.putInt(ARG_OPTION,option);
        fragment.setArguments(args);
        return fragment;

    }

    public DemoFragment() {
    }
    public void onActivityResult(int Requestcode, int ResultCode, Intent data)
    {
        if(ResultCode!= Activity.RESULT_OK){return; }
        if(Requestcode==REQUEST_DATE)
        {
            Date date = (Date) data.getSerializableExtra(DatePickerDialouge.ARGS_DATE);
            //textView.setText(date.toString());
            String email =(String) data.getSerializableExtra(DatePickerDialouge.ARGS_EMAIL);
            //thoughtTextView.setText(thoughtString);
            String nameString = (String) data.getSerializableExtra(DatePickerDialouge.ARGS_NAME);
           // nameTextView.setText(nameString);
            String text=date+" "+nameString+" "+email;
           resulttext.setText(text);
        }
        else if (Requestcode==PICK_CONTACT_REQUEST)
        {
            Uri contacturi=data.getData();
            String [] projection = {
                    ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.NUMBER
            };
            Cursor cursor = getActivity().getContentResolver().query(contacturi,projection,null,null,null);
            cursor.moveToFirst();

            int column=cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
            String number =cursor.getString(column);
            int column1=cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
            String name =cursor.getString(column1);
            resulttext.setText("Phone Number : "+number+"\n Name :"+name);

            resulttext.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.abc_slide_in_bottom));
        }


    }
public void showDatePickerDialogGetResult()
{
    Date date=new Date(System.currentTimeMillis());
    DatePickerDialouge dialouge= DatePickerDialouge.newInstance(date);
    dialouge.setTargetFragment(DemoFragment.this,REQUEST_DATE);
    dialouge.show(getFragmentManager(),"Date Picker Dialouge");
}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.demofragment, container, false);
        final Button DemoFragmentDialog=(Button)v.findViewById(R.id.dialougefragment);
        resulttext=(TextView)v.findViewById(R.id.resulttext);
        DemoFragmentDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               showDatePickerDialogGetResult();
            }
        });
        Button ActivityFragment=(Button)v.findViewById(R.id.ActivityDemo);
        ActivityFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Date date=new Date(System.currentTimeMillis());
                Intent i=new Intent(getActivity(),DatePickerActivity.class);
                i.putExtra(DatePickerDialouge.ARGS_DATE, date);
                ActivityOptions options = ActivityOptions.makeScaleUpAnimation(v, 0,
                        0, v.getWidth(), v.getHeight());
                getActivity().overridePendingTransition(R.anim.abc_shrink_fade_out_from_bottom, R.anim.abc_slide_out_top);

                startActivityForResult(i,REQUEST_DATE);

            }
        });
        Button Contact=(Button) v.findViewById(R.id.contact);
        Contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(Intent.ACTION_PICK, Uri.parse("content://contacts"));
                intent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
                startActivityForResult(intent,PICK_CONTACT_REQUEST);
            }
        });


        return v;
    }
}


