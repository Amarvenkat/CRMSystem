package com.example.crmsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

import static com.example.crmsystem.util.Constants.EMP_ID;
import static com.example.crmsystem.util.Constants.IS_REGISTERED;
import static com.example.crmsystem.util.Constants.KEY_ADDRESS;
import static com.example.crmsystem.util.Constants.KEY_GENDER;
import static com.example.crmsystem.util.Constants.KEY_PHONE_NO;
import static com.example.crmsystem.util.Constants.MYPREF;
import static com.example.crmsystem.util.Constants.KEY_NAME;
import static com.example.crmsystem.util.Constants.EMAIL;
import static com.example.crmsystem.util.Constants.PASSWORD;
import static com.example.crmsystem.util.Constants.ROLE;

public class Register extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    String gender,item;
    Spinner spinner;
    Button registersubmit;
    SharedPreferences sharedPreferences;
    TextInputEditText bdname,bdphoneno,bdgender,bdaddress,email,password,registereployeeid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        spinner = (Spinner) findViewById(R.id.spinner);
        bdname = (TextInputEditText) findViewById(R.id.registername);
        bdphoneno= (TextInputEditText) findViewById(R.id.registerphoneno);
        bdaddress = (TextInputEditText) findViewById(R.id.registeraddress);
        email= (TextInputEditText) findViewById(R.id.registeremail);
        password = (TextInputEditText) findViewById(R.id.registerpassword);
        registersubmit = (Button) findViewById(R.id.registersubmit);
        registereployeeid = (TextInputEditText) findViewById(R.id.registereployeeid);


        spinner.setOnItemSelectedListener(this);
        List<String> categories = new ArrayList<String>();
        categories.add("Admin");
        categories.add("Sales Manager");
        //categories.add("Sales Staff");


        sharedPreferences = getSharedPreferences(MYPREF, Context.MODE_PRIVATE);

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(dataAdapter);

        spinner.getSelectedItem();

        registersubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sharedPreferences.edit().putBoolean(IS_REGISTERED,true);
                sharedPreferences.edit().putString(KEY_NAME,bdname.getText().toString()).apply();
                sharedPreferences.edit().putString(KEY_PHONE_NO,bdphoneno.getText().toString()).apply();
                sharedPreferences.edit().putString(EMAIL,email.getText().toString()).apply();
                sharedPreferences.edit().putString(PASSWORD,password.getText().toString()).apply();
                sharedPreferences.edit().putString(ROLE,item.toString()).apply();
                sharedPreferences.edit().putString(KEY_GENDER,gender.toString()).apply();
                sharedPreferences.edit().putString(KEY_ADDRESS,bdaddress.getText().toString()).apply();
                sharedPreferences.edit().putString(EMP_ID,registereployeeid.getText().toString()).apply();

                startService(new Intent(Register.this, MyService.class));
                Toast.makeText(getApplicationContext(),
                        "Service called ", Toast.LENGTH_SHORT).show();

                finish();
                Intent intent = new Intent(Register.this,MainActivity.class);

                startActivity(intent);
            }
        });

    }


    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radio_male:
                if (checked)
                    gender = "Male";
                break;
            case R.id.radio_female:
                if (checked)
                    gender = "Female";
                break;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {

        item = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}