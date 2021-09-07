package com.example.crmsystem.admin;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.crmsystem.R;
import com.example.crmsystem.salesmanager.ListStaffAdapter;
import com.example.crmsystem.salesstaff.AttendencePojo;
import com.example.crmsystem.salesstaff.SalesStaffListCreate;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class ManageStaff extends AppCompatActivity {

    RecyclerView attendenceviewrecycler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_staff);

        attendenceviewrecycler = (RecyclerView) findViewById(R.id.attendenceviewrecycler);
        loadData();

    }


    private void loadData() {
        final ArrayList<AttendencePojo> dbvalue = new ArrayList<>();
        final ArrayList<String> empid = new ArrayList<>();
        final ArrayList<String> pass = new ArrayList<>();

        CollectionReference db = FirebaseFirestore.getInstance().collection("attendence");
        db.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots,
                                @Nullable FirebaseFirestoreException e) {
                if (queryDocumentSnapshots != null) {
                    dbvalue.clear();
                    for (DocumentSnapshot snapshot : queryDocumentSnapshots.getDocuments()) {
                        AttendencePojo dbvalueData = snapshot.toObject(AttendencePojo.class);

                        dbvalue.add(dbvalueData);

                    }
                    attendenceviewrecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    attendenceviewrecycler.setAdapter(new AttendenceAdapter(dbvalue));

                }


            }
        });


    }
}