package com.example.crmsystem.salesmanager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import static com.example.crmsystem.util.Constants.EMAIL;
import static com.example.crmsystem.util.Constants.EMP_ID;
import static com.example.crmsystem.util.Constants.MYPREF;
import static com.example.crmsystem.util.Constants.PASSWORD;
import static com.example.crmsystem.util.Constants.ROLE;

import com.example.crmsystem.R;
import com.example.crmsystem.admin.AdminHomePage;
import com.example.crmsystem.admin.AdminLogin;
import com.example.crmsystem.salesstaff.SalesStaffHome;
import com.example.crmsystem.salesstaff.SalesStaffList;
import com.example.crmsystem.salesstaff.SalesStaffLogin;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class SalesManagerLogin extends AppCompatActivity {

    Button salesmanagerloginsubmit;
    SharedPreferences sharedPreferences;
    String role,email,password;
    TextInputEditText salesmanageremail,salesmanagerpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_manager_login);

        salesmanagerloginsubmit = (Button) findViewById(R.id.salesmanagerloginsubmit);
        salesmanageremail = (TextInputEditText) findViewById(R.id.salesmanageremail);
        salesmanagerpassword = (TextInputEditText) findViewById(R.id.salesmanagerpassword);

        sharedPreferences = getSharedPreferences(MYPREF, Context.MODE_PRIVATE);

        role = sharedPreferences.getString(ROLE,"");
        email = sharedPreferences.getString(EMAIL,"");
        password = sharedPreferences.getString(PASSWORD,"");




        salesmanagerloginsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                loadData();
//                if (role.equals("Sales Manager") && email.equals(salesmanageremail.getText().toString()) && password.equals(salesmanagerpassword.getText().toString())){
//
//                    Intent intent = new Intent(SalesManagerLogin.this , SalesManagerHome.class);
//                    startActivity(intent);
//
//                }else {
//                    Toast.makeText(SalesManagerLogin.this, "Please Register yourself ", Toast.LENGTH_LONG).show();
//                }

            }
        });
    }


    private void loadData() {
        final ArrayList<SalesStaffList> dbvalue = new ArrayList<>();
        final ArrayList<String> empid = new ArrayList<>();
        final ArrayList<String> pass = new ArrayList<>();

        CollectionReference db = FirebaseFirestore.getInstance().collection("salesmanagerlist");
        db.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots,
                                @Nullable FirebaseFirestoreException e) {
                if (queryDocumentSnapshots != null) {
                    dbvalue.clear();
                    for (DocumentSnapshot snapshot : queryDocumentSnapshots.getDocuments()) {
                        SalesStaffList dbvalueData = snapshot.toObject(SalesStaffList.class);

                        dbvalue.add(dbvalueData);

                    }
                    String passw;
                    for (int i = 0; i < dbvalue.size(); i++) {


                        SalesStaffList ld = dbvalue.get(i);
                        if (ld.getEmpid().equals(salesmanageremail.getText().toString())){
                            passw = ld.getPassword();
                            if (passw.equals(salesmanagerpassword.getText().toString()))
                            {

                                sharedPreferences.edit().putString(EMP_ID,ld.getEmpid()).apply();

                                Intent intent = new Intent(SalesManagerLogin.this , SalesManagerHome.class);
                                startActivity(intent);
                            }else {
                                Toast.makeText(SalesManagerLogin.this, "Invalid Password  ", Toast.LENGTH_LONG).show();
                            }
                        }else {

                            Toast.makeText(SalesManagerLogin.this, "Invalid Employee ID or Password  ", Toast.LENGTH_LONG).show();
                        }


                    }

                }


            }
        });


    }
}