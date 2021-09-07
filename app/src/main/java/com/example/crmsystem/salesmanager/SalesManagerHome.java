package com.example.crmsystem.salesmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.crmsystem.R;

public class SalesManagerHome extends AppCompatActivity {

    Button salesmanagerhomeliststaff,salesmanagerhomeassignleads,salesmanagerhomediscounts,salesmanagerhomecreationofinvoice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_manager_home);


        salesmanagerhomeliststaff = (Button) findViewById(R.id.salesmanagerhomeliststaff);
        salesmanagerhomeassignleads = (Button) findViewById(R.id.salesmanagerhomeassignleads);
        salesmanagerhomediscounts = (Button) findViewById(R.id.salesmanagerhomediscounts);
        salesmanagerhomecreationofinvoice = (Button) findViewById(R.id.salesmanagerhomecreationofinvoice);

        salesmanagerhomeliststaff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SalesManagerHome.this, ListStaff.class);
                startActivity(intent);

            }
        });
        salesmanagerhomeassignleads.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SalesManagerHome.this, AssigningLeads.class);
                startActivity(intent);

            }
        });

        salesmanagerhomediscounts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SalesManagerHome.this, DiscountsManagement.class);
                startActivity(intent);

            }
        });

        salesmanagerhomecreationofinvoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SalesManagerHome.this, CreationofInvoice.class);
                startActivity(intent);

            }
        });

    }
}