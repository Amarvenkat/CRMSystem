package com.example.crmsystem.salesstaff;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.crmsystem.MyService;
import com.example.crmsystem.R;
import com.example.crmsystem.Register;
import com.google.android.material.textfield.TextInputEditText;

import static com.example.crmsystem.util.Constants.EMAIL;
import static com.example.crmsystem.util.Constants.KEY_ADDRESS;
import static com.example.crmsystem.util.Constants.KEY_GENDER;
import static com.example.crmsystem.util.Constants.KEY_NAME;
import static com.example.crmsystem.util.Constants.KEY_PHONE_NO;
import static com.example.crmsystem.util.Constants.LEAD_LEAD_ADDRESS;
import static com.example.crmsystem.util.Constants.LEAD_LEAD_CONTACT;
import static com.example.crmsystem.util.Constants.LEAD_LEAD_NAME;
import static com.example.crmsystem.util.Constants.MYPREF;
import static com.example.crmsystem.util.Constants.PASSWORD;
import static com.example.crmsystem.util.Constants.ROLE;
import static com.example.crmsystem.util.Constants.TRACKLEAD_ADDRESS;
import static com.example.crmsystem.util.Constants.TRACKLEAD_CONTACT;
import static com.example.crmsystem.util.Constants.TRACKLEAD_NAME;
import static com.example.crmsystem.util.Constants.TRACKLEAD_PROFIT;
import static com.example.crmsystem.util.Constants.TRACKLEAD_STOCKLEFT;
import static com.example.crmsystem.util.Constants.TRACKLEAD_STOCKSOLD;
import static com.example.crmsystem.util.Constants.TRACKLEAD_TOTALSTOCK;

public class TrackLead extends AppCompatActivity {

    Intent intent;
    TextView tractleadname,tractleadcontact,tractleadaddress;
    TextInputEditText stocktotal,stocksold,stockleft,stockprofit;
    Button trackleadsubmit;
    SharedPreferences sharedPreferences;
    private static final String NAME = "NAME" ;
    private static final String ADDRESS = "ADDRESS";
    private static final String CONTACT = "CONTACT" ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_lead);

        tractleadname = (TextView) findViewById(R.id.tractleadname);
        tractleadcontact = (TextView) findViewById(R.id.tractleadcontact);
        tractleadaddress = (TextView) findViewById(R.id.tractleadaddress);
        stocktotal = (TextInputEditText) findViewById(R.id.stocktotal);
        stocksold = (TextInputEditText) findViewById(R.id.stocksold);
        stockleft = (TextInputEditText) findViewById(R.id.stockleft);
        stockprofit = (TextInputEditText) findViewById(R.id.stockprofit);
        trackleadsubmit = (Button) findViewById(R.id.trackleadsubmit);




        Bundle bundle = getIntent().getExtras();
        String leadname = bundle.getString(NAME);
        String leadcontact = bundle.getString(CONTACT);
        String leadaddress = bundle.getString(ADDRESS);
        tractleadname.setText(leadname.toString());
        tractleadcontact.setText(leadcontact.toString());
        tractleadaddress.setText(leadaddress.toString());

        sharedPreferences = getSharedPreferences(MYPREF, Context.MODE_PRIVATE);

        trackleadsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sharedPreferences.edit().putString(TRACKLEAD_NAME, leadname).apply();
                sharedPreferences.edit().putString(TRACKLEAD_CONTACT,leadcontact.toString()).apply();
                sharedPreferences.edit().putString(TRACKLEAD_ADDRESS,leadaddress.toString()).apply();
                sharedPreferences.edit().putString(TRACKLEAD_TOTALSTOCK,stocktotal.getText().toString()).apply();
                sharedPreferences.edit().putString(TRACKLEAD_STOCKSOLD,stocksold.getText().toString()).apply();
                sharedPreferences.edit().putString(TRACKLEAD_STOCKLEFT,stockleft.getText().toString()).apply();
                sharedPreferences.edit().putString(TRACKLEAD_PROFIT,stockprofit.getText().toString()).apply();
                Toast.makeText(getApplicationContext(),
                        "Successfully uploaded "+leadname, Toast.LENGTH_SHORT).show();

                startService(new Intent(TrackLead.this, TrackLeadService.class));



            }
        });


    }
}