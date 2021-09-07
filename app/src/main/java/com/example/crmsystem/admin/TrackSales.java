package com.example.crmsystem.admin;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

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
import java.util.Collections;

public class TrackSales extends AppCompatActivity {

    public int employeesize;
    TextView tracksalesprofit,tracksalestotalstocks,tracksalesstockleft,tracksalesstocksold;
    public ArrayList<Integer> totalprofit = new ArrayList<>();
    public ArrayList<Integer> totalstocksfinal = new ArrayList<>();
    public ArrayList<Integer> totalstocksleftfinal = new ArrayList<>();
    public ArrayList<Integer> totalstockssoldfinal = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_sales);

        tracksalesprofit = (TextView) findViewById(R.id.tracksalesprofit);
        tracksalestotalstocks = (TextView) findViewById(R.id.tracksalestotalstocks);
        tracksalesstockleft = (TextView) findViewById(R.id.tracksalesstockleft);
        tracksalesstocksold = (TextView) findViewById(R.id.tracksalesstocksold);

        final ArrayList<SalesStaffListCreate> dbvalue = new ArrayList<>();


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
                    Log.d("Profit", "lat"+dbvalue.size());
                    employeesize = dbvalue.size();

                    for (int i=0;i<employeesize;i++){
                        loaddata(i+1);

                    }

                }


            }
        });


    }

    private void callanother(int size) {

        Log.d("Profit", "lat"+size);
    }

    public void loaddata(int empsize){


        final ArrayList<TrackLeadPojo> dbvalue = new ArrayList<>();
        final ArrayList<String> profit = new ArrayList<>();
        final ArrayList<String> totalstock = new ArrayList<>();
        final ArrayList<String> totalstockleft = new ArrayList<>();
        final ArrayList<String> totalstocksold = new ArrayList<>();

        CollectionReference db = FirebaseFirestore.getInstance().collection("SF"+empsize+"progress");
        db.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots,
                                @Nullable FirebaseFirestoreException e) {
                if (queryDocumentSnapshots != null) {
                    dbvalue.clear();
                    for (DocumentSnapshot snapshot : queryDocumentSnapshots.getDocuments()) {
                        TrackLeadPojo dbvalueData = snapshot.toObject(TrackLeadPojo.class);

                        dbvalue.add(dbvalueData);


                    }

                    for (int i = 0; i < dbvalue.size(); i++) {
                        TrackLeadPojo ld = dbvalue.get(i);

                        profit.add(ld.getProfit());
                        totalstock.add(ld.getTotalstock());
                        totalstockleft.add(ld.getStockleft());
                        totalstocksold.add(ld.getStocksold());

                    }

/////////////////////////////////////////////////////////////////////////////////////////
                    int totalp =0;
                    int totsto = 0;
                    int totleft = 0;
                    int totsold =0;
                    int small =0;
                    int max = 0;
                    final ArrayList<Integer> tofindmin = new ArrayList<>();
                    final ArrayList<Integer> tofindmax = new ArrayList<>();
                    for (int i = 0; i < profit.size(); i++) {

                       totalp = Integer.parseInt(profit.get(i)) + totalp;
                       totsto = Integer.parseInt(totalstock.get(i));
                       small =  Integer.parseInt(totalstockleft.get(i));
                       max = Integer.parseInt(totalstocksold.get(i));
                       tofindmin.add(Integer.valueOf(totalstockleft.get(i))) ;
                        tofindmax.add(Integer.valueOf(totalstocksold.get(i))) ;

                    }

                    for (int i = 0; i < profit.size(); i++) {

                        if (tofindmin.get(i) < small ){
                            small = tofindmin.get(i);
                        }

                        if (tofindmax.get(i) > max ){
                            max = tofindmax.get(i);
                        }

                    }

                    totleft = small;
                    totsold = max;
                    addtotalstockleft(totleft);
                    addtotalprofit(totalp);
                    addtotalstocks(totsto);
                    addtotalstocksold(totsold);
///////////////////////////////////////////////////////////////////////////////////////////////////


                }


            }
        });


    }

    private void addtotalstocksold(int totsold) {

        totalstockssoldfinal.add(totsold);
        int sumtotal = 0;

        if (totalstockssoldfinal.size() == employeesize){


            for (int i=0; i<employeesize; i++){
                sumtotal = totalstockssoldfinal.get(i) + sumtotal;
            }

        }

        String sttotal = String.valueOf(sumtotal);
        tracksalesstocksold.setText(sttotal);

    }

    private void addtotalstockleft(int totleft) {
        totalstocksleftfinal.add(totleft);
        int sumtotal = 0;

        if (totalstocksleftfinal.size() == employeesize){


            for (int i=0; i<employeesize; i++){
                sumtotal = totalstocksleftfinal.get(i) + sumtotal;
            }

        }

        String sttotal = String.valueOf(sumtotal);
        tracksalesstockleft.setText(sttotal);

    }

    private void addtotalstocks(int totsto) {

        totalstocksfinal.add(totsto);
        int sumtotal = 0;

        if (totalstocksfinal.size() == employeesize){


            for (int i=0; i<employeesize; i++){
                sumtotal = totalstocksfinal.get(i) + sumtotal;
            }

        }

        String sttotal = String.valueOf(sumtotal);
        tracksalestotalstocks.setText(sttotal);

    }

    private void addtotalprofit(int totalp) {

        totalprofit.add(totalp);
        int sumtotal =0;
        if (totalprofit.size() == employeesize){


            for (int i=0; i<employeesize; i++){
                sumtotal = totalprofit.get(i) + sumtotal;
            }

        }

        String sttotal = String.valueOf(sumtotal);
        tracksalesprofit.setText(sttotal);

    }


}
