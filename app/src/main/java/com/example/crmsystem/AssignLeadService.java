package com.example.crmsystem;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.crmsystem.salesmanager.LeadUploadPojo;
import com.example.crmsystem.salesstaff.SalesStaffList;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import static com.example.crmsystem.util.Constants.EMP_ID;
import static com.example.crmsystem.util.Constants.LEAD_EMPID;
import static com.example.crmsystem.util.Constants.LEAD_ENDDATE;
import static com.example.crmsystem.util.Constants.LEAD_LEAD_ADDRESS;
import static com.example.crmsystem.util.Constants.LEAD_LEAD_CONTACT;
import static com.example.crmsystem.util.Constants.LEAD_LEAD_NAME;
import static com.example.crmsystem.util.Constants.LEAD_NAME;
import static com.example.crmsystem.util.Constants.LEAD_PLACE;
import static com.example.crmsystem.util.Constants.LEAD_SHIFT;
import static com.example.crmsystem.util.Constants.LEAD_STARTDATE;
import static com.example.crmsystem.util.Constants.LEAD_TARGET;
import static com.example.crmsystem.util.Constants.MYPREF;

public class AssignLeadService extends Service {
    SharedPreferences sharedPreferences;
    public AssignLeadService() {
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

        String leadname = sharedPreferences.getString(LEAD_NAME,"");
        String leadempid = sharedPreferences.getString(LEAD_EMPID,"");
        String leadtarget = sharedPreferences.getString(LEAD_TARGET,"");
        String leadstartdate = sharedPreferences.getString(LEAD_STARTDATE,"");
        String leadenddate = sharedPreferences.getString(LEAD_ENDDATE,"");
        String leadshift = sharedPreferences.getString(LEAD_SHIFT,"");
        String leadplace = sharedPreferences.getString(LEAD_PLACE,"");
        String leadleadname = sharedPreferences.getString(LEAD_LEAD_NAME,"");
        String leadleadaddress = sharedPreferences.getString(LEAD_LEAD_ADDRESS,"");
        String leadleadcontact = sharedPreferences.getString(LEAD_LEAD_CONTACT,"");


        CollectionReference db = FirebaseFirestore.getInstance().collection(leadempid+"lead");

        LeadUploadPojo leadUploadPojo = new LeadUploadPojo(leadname,leadempid,leadtarget,leadstartdate,leadenddate,leadshift,leadplace,leadleadname,leadleadaddress,leadleadcontact);


        db.add(leadUploadPojo).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
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
        Toast.makeText(this,"service destroied",Toast.LENGTH_SHORT).show();
    }
}
