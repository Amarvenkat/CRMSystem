package com.example.crmsystem.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.crmsystem.R;

public class CreateProduct extends AppCompatActivity {


    Button createnewproductbtn,inventoryforproductbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_product);

        createnewproductbtn = (Button) findViewById(R.id.createnewproductbtn);
        inventoryforproductbtn = (Button) findViewById(R.id.inventoryforproductbtn);

        createnewproductbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(CreateProduct.this,CreateNewProduct.class);
                startActivity(intent);

            }
        });

        inventoryforproductbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(CreateProduct.this,InventoryForTheProduct.class);
                startActivity(intent);
            }
        });
    }
}