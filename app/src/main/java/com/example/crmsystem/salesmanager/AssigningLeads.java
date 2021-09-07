package com.example.crmsystem.salesmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.crmsystem.AssignLeadService;
import com.example.crmsystem.MyService;
import com.example.crmsystem.R;
import com.example.crmsystem.Register;
import com.google.android.material.textfield.TextInputEditText;

import static com.example.crmsystem.util.Constants.EMAIL;
import static com.example.crmsystem.util.Constants.IS_REGISTERED;
import static com.example.crmsystem.util.Constants.KEY_GENDER;
import static com.example.crmsystem.util.Constants.KEY_NAME;
import static com.example.crmsystem.util.Constants.KEY_PHONE_NO;
import static com.example.crmsystem.util.Constants.LEAD_EMPID;
import static com.example.crmsystem.util.Constants.LEAD_ENDDATE;
import static com.example.crmsystem.util.Constants.LEAD_LEAD_ADDRESS;
import static com.example.crmsystem.util.Constants.LEAD_LEAD_CONTACT;
import static com.example.crmsystem.util.Constants.LEAD_LEAD_NAME;
import static com.example.crmsystem.util.Constants.LEAD_NAME;
import static com.example.crmsystem.util.Constants.LEAD_PLACE;
import static com.example.crmsystem.util.Constants.LEAD_SHIFT;
import static com.example.crmsystem.util.Constants.LEAD_STARTDATE;
import static com.example.crmsystem.util.Constants.LEAD_TARGET;
import static com.example.crmsystem.util.Constants.MYPREF;
import static com.example.crmsystem.util.Constants.PASSWORD;
import static com.example.crmsystem.util.Constants.ROLE;

public class AssigningLeads extends AppCompatActivity {

    TextInputEditText assigningLeadname,assigningLeademployeeid,assigningLeadtarget,assigningLeadstartdate,assigningLeadenddate,assigningLeadshift,assigningLeadplace,assigningLeadleadname,assigningLeadleadaddress,assigningLeadleadcontact;
    SharedPreferences sharedPreferences;
    Button assigningLeadsubmitbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assigning_leads);
        assigningLeadname = (TextInputEditText) findViewById(R.id.assigningLeadname);
        assigningLeademployeeid = (TextInputEditText) findViewById(R.id.assigningLeademployeeid);
        assigningLeadtarget = (TextInputEditText) findViewById(R.id.assigningLeadtarget);
        assigningLeadstartdate = (TextInputEditText) findViewById(R.id.assigningLeadstartdate);
        assigningLeadenddate = (TextInputEditText) findViewById(R.id.assigningLeadenddate);
        assigningLeadshift = (TextInputEditText) findViewById(R.id.assigningLeadshift);
        assigningLeadplace = (TextInputEditText) findViewById(R.id.assigningLeadplace);
        assigningLeadleadname = (TextInputEditText) findViewById(R.id.assigningLeadleadname);
        assigningLeadleadaddress = (TextInputEditText) findViewById(R.id.assigningLeadleadaddress);
        assigningLeadleadcontact = (TextInputEditText) findViewById(R.id.assigningLeadleadcontact);
        assigningLeadsubmitbtn = (Button) findViewById(R.id.assigningLeadsubmitbtn);

        sharedPreferences = getSharedPreferences(MYPREF, Context.MODE_PRIVATE);

        assigningLeadsubmitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                sharedPreferences.edit().putString(LEAD_NAME,assigningLeadname.getText().toString()).apply();
                sharedPreferences.edit().putString(LEAD_EMPID,assigningLeademployeeid.getText().toString()).apply();
                sharedPreferences.edit().putString(LEAD_TARGET,assigningLeadtarget.getText().toString()).apply();
                sharedPreferences.edit().putString(LEAD_STARTDATE,assigningLeadstartdate.getText().toString()).apply();
                sharedPreferences.edit().putString(LEAD_ENDDATE,assigningLeadenddate.getText().toString()).apply();
                sharedPreferences.edit().putString(LEAD_SHIFT,assigningLeadshift.getText().toString()).apply();

                sharedPreferences.edit().putString(LEAD_LEAD_NAME,assigningLeadleadname.getText().toString()).apply();
                sharedPreferences.edit().putString(LEAD_LEAD_ADDRESS,assigningLeadleadaddress.getText().toString()).apply();
                sharedPreferences.edit().putString(LEAD_LEAD_CONTACT,assigningLeadleadcontact.getText().toString()).apply();


                sharedPreferences.edit().putString(LEAD_PLACE,assigningLeadplace.getText().toString()).apply();
                String leadempid = sharedPreferences.getString(LEAD_EMPID,"");
                Toast.makeText(getApplicationContext(),
                        "Something went wrong: " +leadempid,
                        Toast.LENGTH_SHORT).show();
                startService(new Intent(AssigningLeads.this, AssignLeadService.class));
            }
        });





    }
}