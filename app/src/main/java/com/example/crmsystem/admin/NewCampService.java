package com.example.crmsystem.admin;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.crmsystem.salesstaff.SalesStaffList;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import static com.example.crmsystem.util.Constants.EMAIL;
import static com.example.crmsystem.util.Constants.KEY_GENDER;
import static com.example.crmsystem.util.Constants.KEY_NAME;
import static com.example.crmsystem.util.Constants.KEY_PHONE_NO;
import static com.example.crmsystem.util.Constants.MYPREF;
import static com.example.crmsystem.util.Constants.NEW_CAMP_AREA;
import static com.example.crmsystem.util.Constants.NEW_CAMP_ENDDATE;
import static com.example.crmsystem.util.Constants.NEW_CAMP_NAME;
import static com.example.crmsystem.util.Constants.NEW_CAMP_NOSALES;
import static com.example.crmsystem.util.Constants.NEW_CAMP_PRODUCT;
import static com.example.crmsystem.util.Constants.NEW_CAMP_STARTDATE;
import static com.example.crmsystem.util.Constants.PASSWORD;
import static com.example.crmsystem.util.Constants.ROLE;

public class NewCampService extends Service {
    SharedPreferences sharedPreferences;

    public NewCampService() {
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
        String newcampname =sharedPreferences.getString(NEW_CAMP_NAME,"");
        String newcamparea = sharedPreferences.getString(NEW_CAMP_AREA,"");
        String newcampnosales = sharedPreferences.getString(NEW_CAMP_NOSALES, "");
        String newcampproduct = sharedPreferences.getString(NEW_CAMP_PRODUCT,"");
        String newcampstartdate = sharedPreferences.getString(NEW_CAMP_STARTDATE,"");
        String newcampenddate = sharedPreferences.getString(NEW_CAMP_ENDDATE,"");


        CollectionReference db = FirebaseFirestore.getInstance().collection("newcamp");

        NewCampPojo list = new NewCampPojo(newcampname,newcamparea,newcampnosales,newcampproduct,newcampstartdate,newcampenddate);

        db.add(list).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
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
