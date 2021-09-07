package com.example.crmsystem.admin;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Toast;

import com.example.crmsystem.R;
import com.example.crmsystem.salesmanager.SalesManagerHome;
import com.example.crmsystem.salesmanager.SalesManagerLogin;
import com.example.crmsystem.salesstaff.SalesStaffList;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;

import static com.example.crmsystem.util.Constants.EMAIL;
import static com.example.crmsystem.util.Constants.EMP_ID;
import static com.example.crmsystem.util.Constants.MYPREF;
import static com.example.crmsystem.util.Constants.PASSWORD;
import static com.example.crmsystem.util.Constants.ROLE;

public class AdminLogin extends AppCompatActivity {

    Button adminloginsubmin;
    SharedPreferences sharedPreferences;
    String role,email,password;
    TextInputEditText adminloginemail,adminloginpassword;
    ScrollView scrollView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        adminloginsubmin = (Button) findViewById(R.id.adminloginsubmit);
        adminloginemail = (TextInputEditText) findViewById(R.id.adminloginemail);
        adminloginpassword = (TextInputEditText) findViewById(R.id.adminloginpassword);
        scrollView  = (ScrollView) findViewById(R.id.adminimg);
        sharedPreferences = getSharedPreferences(MYPREF, Context.MODE_PRIVATE);

        role = sharedPreferences.getString(ROLE,"");
        email = sharedPreferences.getString(EMAIL,"");
        password = sharedPreferences.getString(PASSWORD,"");





        adminloginsubmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                loadData();
//                    Intent intent = new Intent(AdminLogin.this, AdminHomePage.class);
//                   startActivity(intent);
//                if (role.equals("Admin") && email.equals(adminloginemail.getText().toString()) && password.equals(adminloginpassword.getText().toString())){
//
//                    Intent intent = new Intent(AdminLogin.this, AdminHomePage.class);
//                    startActivity(intent);
//
//
//                }else {
//                    Toast.makeText(AdminLogin.this, "Please Register yourself ", Toast.LENGTH_LONG).show();
//                }
            }
        });
    }



    private void loadData() {
        final ArrayList<SalesStaffList> dbvalue = new ArrayList<>();
        final ArrayList<String> empid = new ArrayList<>();
        final ArrayList<String> pass = new ArrayList<>();

        CollectionReference db = FirebaseFirestore.getInstance().collection("adminlist");
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
                        if (ld.getEmpid().equals(adminloginemail.getText().toString())){
                            passw = ld.getPassword();
                            if (passw.equals(adminloginpassword.getText().toString()))
                            {

                                sharedPreferences.edit().putString(EMP_ID,ld.getEmpid()).apply();

                                Intent intent = new Intent(AdminLogin.this , AdminHomePage.class);
                                startActivity(intent);
                            }else {
                                Toast.makeText(AdminLogin.this, "Invalid Password  ", Toast.LENGTH_LONG).show();
                            }
                        }else {

                            Toast.makeText(AdminLogin.this, "Invalid Employee ID or Password  ", Toast.LENGTH_LONG).show();
                        }


                    }

                }


            }
        });


    }
}