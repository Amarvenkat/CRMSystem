package com.example.crmsystem.admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.crmsystem.R;

public class AdminHomePage extends AppCompatActivity {

    CardView createsalespersonbtn,managestaffbtn,tracksalesbtn,paybtn,createproductbtn,leadgenerationbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home_page);

        createsalespersonbtn = (CardView) findViewById(R.id.createsalespersonbtn);
        managestaffbtn = (CardView) findViewById(R.id.managestaffbtn);
        tracksalesbtn = (CardView) findViewById(R.id.tracksalesbtn);
        paybtn = (CardView) findViewById(R.id.paybtn);
        createproductbtn = (CardView) findViewById(R.id.createproductbtn);
        leadgenerationbtn = (CardView) findViewById(R.id.leadgenerationbtn);

        createsalespersonbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(AdminHomePage.this , CreateSalsePerson.class);
                startActivity(intent);
            }
        });

        managestaffbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent =new Intent(AdminHomePage.this, ManageStaff.class);
                startActivity(intent);
            }
        });

        tracksalesbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent =new Intent(AdminHomePage.this, TrackSales.class);
                startActivity(intent);
            }
        });

        paybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(AdminHomePage.this, Pay.class);
                startActivity(intent);
            }
        });

        createproductbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent =new Intent(AdminHomePage.this, CreateProduct.class);
                startActivity(intent);
            }
        });

        leadgenerationbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(AdminHomePage.this, LeadGeneration.class);
                startActivity(intent);
            }
        });
    }
}