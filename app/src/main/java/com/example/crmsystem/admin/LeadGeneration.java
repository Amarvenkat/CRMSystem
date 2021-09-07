package com.example.crmsystem.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.crmsystem.R;

public class LeadGeneration extends AppCompatActivity {

    Button leadgencamp,leadgennewcamp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lead_generation);
        leadgencamp = (Button) findViewById(R.id.leadgencamp);
        leadgennewcamp = (Button) findViewById(R.id.leadgennewcamp);

        leadgencamp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(LeadGeneration.this,LeadsGeneratedCampaigns.class);
                startActivity(intent);
            }
        });
        leadgennewcamp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(LeadGeneration.this,NewCampaigns.class);
                startActivity(intent);

            }
        });

    }
}