package com.example.crmsystem.salesstaff;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.crmsystem.R;
import com.example.crmsystem.salesmanager.LeadUploadPojo;
import com.example.crmsystem.salesmanager.ListStaffAdapter;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import static com.example.crmsystem.util.Constants.EMP_ID;
import static com.example.crmsystem.util.Constants.MYPREF;

public class SalesStaffTrackLead extends AppCompatActivity {

    RecyclerView salesstaffrecycler;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_staff_track_lead);

        salesstaffrecycler = (RecyclerView) findViewById(R.id.salesstaffrecycler);
        sharedPreferences = getSharedPreferences(MYPREF, Context.MODE_PRIVATE);
        loadData();


    }



    private void loadData() {
        final ArrayList<LeadUploadPojo> dbvalue = new ArrayList<>();
        final ArrayList<String> empid = new ArrayList<>();
        final ArrayList<String> pass = new ArrayList<>();

        String employeeid = sharedPreferences.getString(EMP_ID,"");
        CollectionReference db = FirebaseFirestore.getInstance().collection(employeeid+"lead");
        db.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots,
                                @Nullable FirebaseFirestoreException e) {
                if (queryDocumentSnapshots != null) {
                    dbvalue.clear();
                    for (DocumentSnapshot snapshot : queryDocumentSnapshots.getDocuments()) {
                        LeadUploadPojo dbvalueData = snapshot.toObject(LeadUploadPojo.class);

                        dbvalue.add(dbvalueData);

                    }
                    salesstaffrecycler.setLayoutManager(new GridLayoutManager(SalesStaffTrackLead.this,2, LinearLayoutManager.VERTICAL,false));
                    salesstaffrecycler.setAdapter(new SalesStaffLeadAdapter(dbvalue,getApplicationContext()));

                }


            }
        });


    }

}