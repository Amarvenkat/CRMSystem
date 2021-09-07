package com.example.crmsystem.salesmanager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.crmsystem.R;
import com.example.crmsystem.salesstaff.SalesStaffHome;
import com.example.crmsystem.salesstaff.SalesStaffList;
import com.example.crmsystem.salesstaff.SalesStaffListCreate;
import com.example.crmsystem.salesstaff.SalesStaffLogin;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class ListStaff extends AppCompatActivity {

    RecyclerView liststaffrecycler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_staff);

        liststaffrecycler = (RecyclerView) findViewById(R.id.liststaffrecycler);

        loadData();
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
                    liststaffrecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    liststaffrecycler.setAdapter(new ListStaffAdapter(dbvalue));

                }


            }
        });


    }

}
