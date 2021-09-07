package com.example.crmsystem.admin;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.crmsystem.R;
import com.example.crmsystem.salesmanager.SalesManagerLogin;
import com.example.crmsystem.salesstaff.TrackLeadPojo;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class Pay extends AppCompatActivity {


    TextInputEditText payempid,paybasicpay,payincentive;
    TextView totalpaytext;
    Button calcpaybtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);

        payempid = (TextInputEditText) findViewById(R.id.payempid);
        paybasicpay = (TextInputEditText) findViewById(R.id.paybasicpay);
        payincentive = (TextInputEditText) findViewById(R.id.payincentive);
        calcpaybtn = (Button) findViewById(R.id.calcpaybtn);
        totalpaytext = (TextView) findViewById(R.id.totalpay);
        final ArrayList<TrackLeadPojo> dbvalue = new ArrayList<>();
        final ArrayList<String> profit = new ArrayList<>();


        calcpaybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CollectionReference db = FirebaseFirestore.getInstance().collection(payempid.getText().toString()+"progress");
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
                            int totalp =0;
                            for (int i = 0; i < dbvalue.size(); i++) {
                                TrackLeadPojo ld = dbvalue.get(i);

                                profit.add(ld.getProfit());


                            }

                            for (int i = 0; i < profit.size(); i++) {

                                totalp = Integer.parseInt(profit.get(i)) + totalp;


                            }
                            Log.d("Tag", "totalp" + totalp);
                            calculatepay(totalp);

                        }


                    }
                });
            }
        });

    }

    private void calculatepay(int profit) {

        int basicpay =0;
        int incentive = 0;
        basicpay = Integer.parseInt(paybasicpay.getText().toString());

        incentive = Integer.parseInt(payincentive.getText().toString());
        int a =(profit/100) * incentive;
        Log.d("Tag", "profit" + profit);
        Log.d("Tag", "basicpay" + basicpay);
        Log.d("Tag", "incentive" + incentive);
        Log.d("Tag", "a" + a);

        int totalpay = a+basicpay;
        Log.d("Tag", "totalpay" + totalpay);
        String b = String.valueOf(totalpay);
        totalpaytext.setText(b);




    }
}