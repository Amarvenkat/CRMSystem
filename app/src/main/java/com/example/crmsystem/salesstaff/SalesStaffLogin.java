package com.example.crmsystem.salesstaff;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.crmsystem.R;
import com.example.crmsystem.salesmanager.SalesManagerHome;
import com.example.crmsystem.salesmanager.SalesManagerLogin;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import static com.example.crmsystem.util.Constants.EMAIL;
import static com.example.crmsystem.util.Constants.EMP_ID;
import static com.example.crmsystem.util.Constants.MYPREF;
import static com.example.crmsystem.util.Constants.PASSWORD;
import static com.example.crmsystem.util.Constants.ROLE;

public class SalesStaffLogin extends AppCompatActivity {

    Button salesstaffloginsubmit;
    SharedPreferences sharedPreferences;
    String role,email,password;
    TextInputEditText salesstaffemail,salesstaffpassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_staff_login);
        salesstaffloginsubmit = (Button) findViewById(R.id.salesstaffloginsubmit);

        salesstaffemail = (TextInputEditText) findViewById(R.id.salesstaffloginemail);
        salesstaffpassword = (TextInputEditText) findViewById(R.id.salesstaffloginpassword);


              sharedPreferences = getSharedPreferences(MYPREF, Context.MODE_PRIVATE);

        role = sharedPreferences.getString(ROLE,"");
        email = sharedPreferences.getString(EMAIL,"");
        password = sharedPreferences.getString(PASSWORD,"");



        salesstaffloginsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                loadData();
//
//                if (role.equals("Sales Staff") && email.equals(salesstaffemail.getText().toString()) && password.equals(salesstaffpassword.getText().toString())){
//
//                    Intent intent = new Intent(SalesStaffLogin.this , SalesStaffHome.class);
//                    startActivity(intent);
//                }else {
//                    Toast.makeText(SalesStaffLogin.this, "Please Register yourself ", Toast.LENGTH_LONG).show();
//                }

            }
        });
    }



    private void loadData() {
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
                   String passw;
                    for (int i = 0; i < dbvalue.size(); i++) {


                        SalesStaffListCreate ld = dbvalue.get(i);
                        if (ld.getEmpid().equals(salesstaffemail.getText().toString())){
                            passw = ld.getMail();
                            if (passw.equals(salesstaffpassword.getText().toString()))
                            {
                                sharedPreferences.edit().putString(EMP_ID,ld.getEmpid()).apply();

                                Intent intent = new Intent(SalesStaffLogin.this , SalesStaffHome.class);
                                startActivity(intent);
                            }else {
                                //Toast.makeText(SalesStaffLogin.this, "Invalid Password  ", Toast.LENGTH_LONG).show();
                            }
                        }else {

                           // Toast.makeText(SalesStaffLogin.this, "Invalid Employee ID or Password  ", Toast.LENGTH_LONG).show();
                        }


                    }

                }


            }
        });


    }
}