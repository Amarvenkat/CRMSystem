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

import static com.example.crmsystem.util.Constants.CURRENT_DATE;
import static com.example.crmsystem.util.Constants.EMP_ID;
import static com.example.crmsystem.util.Constants.KEY_LATITUDE;
import static com.example.crmsystem.util.Constants.KEY_LONGITUDE;
import static com.example.crmsystem.util.Constants.MYPREF;

public class AttendenceService extends Service {
    SharedPreferences sharedPreferences;
    public AttendenceService() {
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
        String empid = sharedPreferences.getString(EMP_ID,"");
        String date = sharedPreferences.getString(CURRENT_DATE,"");
        String lat = sharedPreferences.getString(KEY_LATITUDE,"");
        String lon = sharedPreferences.getString(KEY_LONGITUDE,"");
        CollectionReference db = FirebaseFirestore.getInstance().collection("attendence");

        AttendencePojo attendencePojo = new AttendencePojo(empid,date,lat,lon);

        db.add(attendencePojo).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
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
