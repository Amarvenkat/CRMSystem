package com.example.crmsystem.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.crmsystem.MyService;
import com.example.crmsystem.R;
import com.google.android.material.textfield.TextInputEditText;

import static com.example.crmsystem.util.Constants.MYPREF;
import static com.example.crmsystem.util.Constants.NEW_CAMP_AREA;
import static com.example.crmsystem.util.Constants.NEW_CAMP_ENDDATE;
import static com.example.crmsystem.util.Constants.NEW_CAMP_NAME;
import static com.example.crmsystem.util.Constants.NEW_CAMP_NOSALES;
import static com.example.crmsystem.util.Constants.NEW_CAMP_PRODUCT;
import static com.example.crmsystem.util.Constants.NEW_CAMP_STARTDATE;
import static com.example.crmsystem.util.Constants.SF_ADDRESS;
import static com.example.crmsystem.util.Constants.SF_CITY;
import static com.example.crmsystem.util.Constants.SF_EMAIL;
import static com.example.crmsystem.util.Constants.SF_NAME;
import static com.example.crmsystem.util.Constants.SF_PROCATO;
import static com.example.crmsystem.util.Constants.SF_SHIFT;

public class NewCampaigns extends AppCompatActivity {

    TextInputEditText newcampname,newcamparea,newcampnosales,newcampproduct,newcampstartdate,newcampenddate;
    Button newcampsubmit;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_campaigns);

        newcampname = (TextInputEditText) findViewById(R.id.newcampname);
        newcamparea = (TextInputEditText) findViewById(R.id.newcamparea);
        newcampnosales = (TextInputEditText) findViewById(R.id.newcampnosales);
        newcampproduct = (TextInputEditText) findViewById(R.id.newcampproduct);
        newcampstartdate = (TextInputEditText) findViewById(R.id.newcampstartdate);
        newcampenddate = (TextInputEditText) findViewById(R.id.newcampenddate);
        newcampsubmit = (Button) findViewById(R.id.newcampsubmit);
        sharedPreferences = getSharedPreferences(MYPREF, Context.MODE_PRIVATE);

        newcampsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sharedPreferences.edit().putString(NEW_CAMP_NAME,newcampname.getText().toString()).apply();
                sharedPreferences.edit().putString(NEW_CAMP_AREA,newcamparea.getText().toString()).apply();
                sharedPreferences.edit().putString(NEW_CAMP_NOSALES,newcampnosales.getText().toString()).apply();
                sharedPreferences.edit().putString(NEW_CAMP_PRODUCT,newcampproduct.getText().toString()).apply();
                sharedPreferences.edit().putString(NEW_CAMP_STARTDATE,newcampstartdate.getText().toString()).apply();
                sharedPreferences.edit().putString(NEW_CAMP_ENDDATE,newcampenddate.getText().toString()).apply();

                startService(new Intent(NewCampaigns.this, NewCampService.class));

            }
        });



    }
}