package com.example.crmsystem.salesmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.crmsystem.R;

public class AssignLeads extends AppCompatActivity {

    Button assigningLeads,reassigningleads;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assign_leads);

        assigningLeads = (Button) findViewById(R.id.assigningLeads);
        //reassigningleads = (Button) findViewById(R.id.reassigningleads);

        assigningLeads.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AssignLeads.this, AssigningLeads.class);
                startActivity(intent);
            }
        });

//        reassigningleads.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Intent intent = new Intent(AssignLeads.this, ReassigningLeads.class);
//                startActivity(intent);
//            }
//        });

    }
}