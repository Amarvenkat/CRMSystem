package com.example.crmsystem.admin;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.crmsystem.R;
import com.example.crmsystem.salesmanager.ListStaffAdapter;
import com.example.crmsystem.salesstaff.SalesStaffListCreate;
import com.example.crmsystem.salesstaff.TrackLeadPojo;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class LeadsGeneratedCampaigns extends AppCompatActivity {

    RecyclerView leadgenrecycler;
    public int employeesize;
     ArrayList<SalesStaffListCreate> dbvalue = new ArrayList<>();
    ArrayList<TrackLeadPojo> dbvalue1 = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leads_generated_campaigns);
        leadgenrecycler = (RecyclerView) findViewById(R.id.leadgenrecycler);




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
                    Log.d("Profit", "lat" + dbvalue.size());
                    employeesize = dbvalue.size();

                    for (int i = 0; i < employeesize; i++) {
                        loaddata(i + 1);

                    }

                }


            }
        });


    }

    private void loaddata(int empsize) {


       // final ArrayList<TrackLeadPojo> dbvalue = new ArrayList<>();
        final ArrayList<String> profit = new ArrayList<>();
        final ArrayList<String> totalstock = new ArrayList<>();
        final ArrayList<String> totalstockleft = new ArrayList<>();
        final ArrayList<String> totalstocksold = new ArrayList<>();

        CollectionReference db = FirebaseFirestore.getInstance().collection("SF" + empsize + "progress");
        db.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots,
                                @Nullable FirebaseFirestoreException e) {
                if (queryDocumentSnapshots != null) {

                    for (DocumentSnapshot snapshot : queryDocumentSnapshots.getDocuments()) {
                        TrackLeadPojo dbvalueData = snapshot.toObject(TrackLeadPojo.class);

                        dbvalue1.add(dbvalueData);


                    }
                    leadgenrecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    leadgenrecycler.setAdapter(new LeadsGenAdapter(dbvalue1));


                }


            }
        });


    }
}