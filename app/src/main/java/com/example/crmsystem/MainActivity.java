package com.example.crmsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.crmsystem.admin.AdminLogin;
import com.example.crmsystem.salesmanager.SalesManagerLogin;
import com.example.crmsystem.salesstaff.SalesStaffLogin;
import com.squareup.picasso.Picasso;

import static com.example.crmsystem.util.Constants.KEY_NAME;
import static com.example.crmsystem.util.Constants.MYPREF;

public class MainActivity extends AppCompatActivity {

    public Button loginadmin,salesmanagerlogin,salesstafflogin;
    public TextView register;
    SharedPreferences sharedPreferences;
    ScrollView linearlayoutmain;
    ImageView mainimg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginadmin = (Button) findViewById(R.id.loginadmin);
        salesmanagerlogin = (Button) findViewById(R.id.salesmanagerlogin);
        salesstafflogin = (Button) findViewById(R.id.salesstafflogin);
        register = (TextView) findViewById(R.id.register);
        linearlayoutmain = (ScrollView) findViewById(R.id.linearlayoutmain);
        mainimg = (ImageView) findViewById(R.id.mainimg);


        Picasso.with(this).load(R.drawable.cmricon).fit().into(mainimg);

//        final ImageView img = new ImageView(this);
//            Picasso.with(img.getContext()).load(R.drawable.background2).into(img, new com.squareup.picasso.Callback() {
//                @Override
//                public void onSuccess() {
//                    linearlayoutmain.setBackgroundDrawable(img.getDrawable());
//                }
//
//                @Override
//                public void onError() {
//                }
//            });


        sharedPreferences = getSharedPreferences(MYPREF, Context.MODE_PRIVATE);

        if (sharedPreferences.contains(KEY_NAME)) {
            Toast.makeText(this, "Welcome " + sharedPreferences.getString(KEY_NAME, "")+"Login to continue ", Toast.LENGTH_LONG).show();

        }else {
            Toast.makeText(this, "Please Register yourself ", Toast.LENGTH_LONG).show();
        }


        loginadmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, AdminLogin.class);
                startActivity(intent);
            }
        });

        salesmanagerlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, SalesManagerLogin.class);
                startActivity(intent);
            }
        });
        salesstafflogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, SalesStaffLogin.class);
                startActivity(intent);
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, Register.class);
                startActivity(intent);
            }
        });


    }
    public int add(int a, int b){
        int c = a+b;
        return c;
    }
}
