package com.example.crmsystem.salesstaff;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import static com.example.crmsystem.util.Constants.EMP_ID;
import static com.example.crmsystem.util.Constants.MYPREF;
import static com.example.crmsystem.util.Constants.TRACKLEAD_ADDRESS;
import static com.example.crmsystem.util.Constants.TRACKLEAD_CONTACT;
import static com.example.crmsystem.util.Constants.TRACKLEAD_NAME;
import static com.example.crmsystem.util.Constants.TRACKLEAD_PROFIT;
import static com.example.crmsystem.util.Constants.TRACKLEAD_STOCKLEFT;
import static com.example.crmsystem.util.Constants.TRACKLEAD_STOCKSOLD;
import static com.example.crmsystem.util.Constants.TRACKLEAD_TOTALSTOCK;

public class TrackLeadService extends Service {

    SharedPreferences sharedPreferences;
    public TrackLeadService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        FirebaseDatabase database = FirebaseDatabase.getInstance();

        sharedPreferences = getSharedPreferences(MYPREF, Context.MODE_PRIVATE);
        String trackleadname = sharedPreferences.getString(TRACKLEAD_NAME,"");
        String trackleadcontact = sharedPreferences.getString(TRACKLEAD_CONTACT,"");
        String trackleadaddress = sharedPreferences.getString(TRACKLEAD_ADDRESS,"");
        String trackleadtotalstock = sharedPreferences.getString(TRACKLEAD_TOTALSTOCK,"");
        String trackleadstocksold = sharedPreferences.getString(TRACKLEAD_STOCKSOLD,"");
        String trackleadstockleft = sharedPreferences.getString(TRACKLEAD_STOCKLEFT,"");
        String trackleadprofit = sharedPreferences.getString(TRACKLEAD_PROFIT,"");
        String empid = sharedPreferences.getString(EMP_ID,"");

        CollectionReference db = FirebaseFirestore.getInstance().collection(empid+"progress");
        TrackLeadPojo trackLeadPojo = new TrackLeadPojo(trackleadname,trackleadcontact,trackleadaddress,trackleadtotalstock,trackleadstocksold,trackleadstockleft,trackleadprofit);

        db.add(trackLeadPojo).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(),
                            "Successfully uploaded ", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(getApplicationContext(),
                            "Something went wrong: " + task.getException().toString(),
                            Toast.LENGTH_SHORT).show();
                }
            }
        });



        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
