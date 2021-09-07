package com.example.crmsystem.admin;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

import com.example.crmsystem.MyService;
import com.example.crmsystem.R;
import com.example.crmsystem.Register;
import com.example.crmsystem.salesstaff.SalesStaffListCreate;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import static com.example.crmsystem.util.Constants.EMAIL;
import static com.example.crmsystem.util.Constants.KEY_GENDER;
import static com.example.crmsystem.util.Constants.KEY_NAME;
import static com.example.crmsystem.util.Constants.KEY_PHONE_NO;
import static com.example.crmsystem.util.Constants.MYPREF;
import static com.example.crmsystem.util.Constants.PASSWORD;
import static com.example.crmsystem.util.Constants.ROLE;
import static com.example.crmsystem.util.Constants.SF_ADDRESS;
import static com.example.crmsystem.util.Constants.SF_CITY;
import static com.example.crmsystem.util.Constants.SF_EMAIL;
import static com.example.crmsystem.util.Constants.SF_EMPID;
import static com.example.crmsystem.util.Constants.SF_GENDER;
import static com.example.crmsystem.util.Constants.SF_NAME;
import static com.example.crmsystem.util.Constants.SF_PHONENO;
import static com.example.crmsystem.util.Constants.SF_PROCATO;
import static com.example.crmsystem.util.Constants.SF_SHIFT;

public class CreateSalsePerson extends AppCompatActivity {

    TextInputEditText createname,createaddress,createemail,createcity,createproductcategory,createshift,createempid,createphone;
    Button createsubmitbtn;
    String gender;
    SharedPreferences sharedPreferences;
    public int employeesize;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_salse_person);

        createname = (TextInputEditText) findViewById(R.id.createname);
        createaddress = (TextInputEditText) findViewById(R.id.createaddress);
        createemail = (TextInputEditText) findViewById(R.id.createemail);
        createcity = (TextInputEditText) findViewById(R.id.createcity);
        createproductcategory = (TextInputEditText) findViewById(R.id.createproductcategory);
        createshift = (TextInputEditText) findViewById(R.id.createshift);
       // createempid = (TextInputEditText) findViewById(R.id.createempid);
        createsubmitbtn = (Button) findViewById(R.id.createsubmitbtn);
        createphone = (TextInputEditText) findViewById(R.id.createphoneno);

        sharedPreferences = getSharedPreferences(MYPREF, Context.MODE_PRIVATE);
        createsubmitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final ArrayList<SalesStaffListCreate> dbvalue = new ArrayList<>();
                final ArrayList<String> empid = new ArrayList<>();
                final ArrayList<String> pass = new ArrayList<>();

                CollectionReference db = FirebaseFirestore.getInstance().collection("salesstafflist");
                db.addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots,
                                        @Nullable FirebaseFirestoreException e) {
                        if (queryDocumentSnapshots != null) {
                            dbvalue.clear();
                            for (DocumentSnapshot snapshot : queryDocumentSnapshots.getDocuments()) {
                                SalesStaffListCreate dbvalueData = snapshot.toObject(SalesStaffListCreate.class);

                                dbvalue.add(dbvalueData);

                            }

                            employeesize = dbvalue.size();
                            Log.d("create", "empsize"+employeesize);
                            int nextsize = employeesize + 1;
                            Log.d("create", "next"+nextsize);
                            sharedPreferences.edit().putString(SF_EMPID,"SF"+String.valueOf(nextsize)).apply();
                        }


                    }
                });

                String role = "Sales Staff";
                sharedPreferences.edit().putString(SF_NAME,createname.getText().toString()).apply();
                sharedPreferences.edit().putString(SF_ADDRESS,createaddress.getText().toString()).apply();
                sharedPreferences.edit().putString(SF_EMAIL,createemail.getText().toString()).apply();
                sharedPreferences.edit().putString(SF_CITY,createcity.getText().toString()).apply();
                sharedPreferences.edit().putString(SF_PROCATO,createproductcategory.getText().toString()).apply();
                sharedPreferences.edit().putString(SF_SHIFT,createshift.getText().toString()).apply();
                sharedPreferences.edit().putString(SF_PHONENO,createphone.getText().toString()).apply();
                sharedPreferences.edit().putString(SF_GENDER,gender.toString()).apply();
                //sharedPreferences.edit().putString(SF_EMPID,createempid.getText().toString()).apply();

                sharedPreferences.edit().putString(ROLE,role.toString()).apply();

                startService(new Intent(CreateSalsePerson.this, MyService.class));
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

}