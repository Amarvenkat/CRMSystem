package com.example.crmsystem;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.crmsystem.salesstaff.SalesStaffList;
import com.example.crmsystem.salesstaff.SalesStaffListCreate;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import static com.example.crmsystem.util.Constants.EMAIL;
import static com.example.crmsystem.util.Constants.EMP_ID;
import static com.example.crmsystem.util.Constants.IS_REGISTERED;
import static com.example.crmsystem.util.Constants.KEY_ADDRESS;
import static com.example.crmsystem.util.Constants.KEY_GENDER;
import static com.example.crmsystem.util.Constants.KEY_NAME;
import static com.example.crmsystem.util.Constants.KEY_PHONE_NO;
import static com.example.crmsystem.util.Constants.MYPREF;
import static com.example.crmsystem.util.Constants.PASSWORD;
import static com.example.crmsystem.util.Constants.ROLE;
import static com.example.crmsystem.util.Constants.SF_ADDRESS;
import static com.example.crmsystem.util.Constants.SF_CITY;
import static com.example.crmsystem.util.Constants.SF_EMAIL;
import static com.example.crmsystem.util.Constants.SF_EMPID;
import static com.example.crmsystem.util.Constants.SF_GENDER;
import static com.example.crmsystem.util.Constants.SF_NAME;
import static com.example.crmsystem.util.Constants.SF_PHONENO;
import static com.example.crmsystem.util.Constants.SF_PROCATO;
import static com.example.crmsystem.util.Constants.SF_SHIFT;

public class MyService extends Service {
    SharedPreferences sharedPreferences;

    public MyService() {
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
        String name =sharedPreferences.getString(KEY_NAME,"");
        String phone = sharedPreferences.getString(KEY_PHONE_NO,"");
        String email = sharedPreferences.getString(EMAIL, "");
        String password = sharedPreferences.getString(PASSWORD,"");
        String role = sharedPreferences.getString(ROLE,"");
        String gender = sharedPreferences.getString(KEY_GENDER,"");
        String address = sharedPreferences.getString(KEY_ADDRESS,"");
        String empid = sharedPreferences.getString(EMP_ID,"");


        String sfname = sharedPreferences.getString(SF_NAME,"");
        String sfaddress = sharedPreferences.getString(SF_ADDRESS,"");
        String sfmail = sharedPreferences.getString(SF_EMAIL,"");
        String sfcity= sharedPreferences.getString(SF_CITY,"");
        String sfprocate = sharedPreferences.getString(SF_PROCATO,"");
        String sfshift = sharedPreferences.getString(SF_SHIFT,"");
        String sfempid = sharedPreferences.getString(SF_EMPID,"");
        String sfphone = sharedPreferences.getString(SF_PHONENO,"");
        String sfgender = sharedPreferences.getString(SF_GENDER,"");






        if (role.equals("Admin")){

            CollectionReference db = FirebaseFirestore.getInstance().collection("adminlist");

            SalesStaffList list = new SalesStaffList(name,phone,email,password,role,gender,address,empid);

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
        }if (role.equals("Sales Manager")){


            CollectionReference db = FirebaseFirestore.getInstance().collection("salesmanagerlist");

            SalesStaffList list = new SalesStaffList(name,phone,email,password,role,gender,address,empid);

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
        }if (role.equals("Sales Staff")){


            CollectionReference db = FirebaseFirestore.getInstance().collection("salesstafflist");

            SalesStaffListCreate list = new SalesStaffListCreate(sfname,sfaddress,sfmail,sfcity,sfprocate,sfshift,role,sfempid,sfgender,sfphone);
            //SalesStaffList list = new SalesStaffList(name,phone,email,password,role,gender,address,empid);

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




        }



        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this,"service destroied",Toast.LENGTH_SHORT).show();
    }
}
