package com.example.crmsystem.salesstaff;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.crmsystem.R;
import com.example.crmsystem.salesmanager.AssignLeads;
import com.example.crmsystem.salesmanager.ListStaff;
import com.example.crmsystem.salesmanager.SalesManagerHome;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.example.crmsystem.util.Constants.CURRENT_DATE;
import static com.example.crmsystem.util.Constants.KEY_LATITUDE;
import static com.example.crmsystem.util.Constants.KEY_LONGITUDE;
import static com.example.crmsystem.util.Constants.MYPREF;
import static com.example.crmsystem.util.Constants.TRACKLEAD_NAME;

public class SalesStaffHome extends AppCompatActivity {

    Button salesstaffhometracklead,salesstaffhomedashboard,markattendence;
    SharedPreferences sharedPreferences;
    int PERMISSION_ID = 44;
    FusedLocationProviderClient mFusedLocationClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_staff_home);


        salesstaffhometracklead = (Button) findViewById(R.id.salesstaffhometracklead);
        salesstaffhomedashboard = (Button) findViewById(R.id.salesstaffhomedashboard);
        markattendence = (Button) findViewById(R.id.markattendence);


        salesstaffhometracklead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SalesStaffHome.this, SalesStaffTrackLead.class);
                startActivity(intent);

            }
        });
        salesstaffhomedashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SalesStaffHome.this, SalesStaffDashboard.class);
                startActivity(intent);

            }
        });


        sharedPreferences = getSharedPreferences(MYPREF, Context.MODE_PRIVATE);
        markattendence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                LocalDateTime now = LocalDateTime.now();

                sharedPreferences.edit().putString(CURRENT_DATE,dtf.format(now) ).apply();
                mFusedLocationClient = LocationServices.getFusedLocationProviderClient(SalesStaffHome.this);

                getLastLocation();




            }
        });
    }

    @SuppressLint("MissingPermission")
    private void getLastLocation(){
        if (checkPermissions()) {
            if (isLocationEnabled()) {
                mFusedLocationClient.getLastLocation().addOnCompleteListener(
                        new OnCompleteListener<Location>() {
                            @Override
                            public void onComplete(@NonNull Task<Location> task) {
                                Location location = task.getResult();
                                if (location == null) {
                                    requestNewLocationData();
                                } else {
                                    //   latTextView.setText(location.getLatitude()+"");
                                    // lonTextView.setText(location.getLongitude()+"");

                                    showlocation(location.getLatitude(),location.getLongitude());

                                }
                            }
                        }
                );
            } else {
                Toast.makeText(this, "Turn on location", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
            }
        } else {
            requestPermissions();
        }
    }



    @SuppressLint("MissingPermission")
    private void requestNewLocationData(){

        LocationRequest mLocationRequest = new LocationRequest();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setInterval(0);
        mLocationRequest.setFastestInterval(0);
        mLocationRequest.setNumUpdates(1);

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        mFusedLocationClient.requestLocationUpdates(
                mLocationRequest, mLocationCallback,
                Looper.myLooper()
        );

    }

    private LocationCallback mLocationCallback = new LocationCallback() {
        @Override
        public void onLocationResult(LocationResult locationResult) {
            Location mLastLocation = locationResult.getLastLocation();

            //latTextView.setText(mLastLocation.getLatitude()+"");
            //lonTextView.setText(mLastLocation.getLongitude()+"");
        }
    };

    private boolean checkPermissions() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        return false;
    }

    private void requestPermissions() {
        ActivityCompat.requestPermissions(
                this,
                new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION},
                PERMISSION_ID
        );
    }

    private boolean isLocationEnabled() {
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
                LocationManager.NETWORK_PROVIDER
        );
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_ID) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getLastLocation();
            }
        }
    }

    private void showlocation(double latitude, double longitude) {
        sharedPreferences = getSharedPreferences(MYPREF, Context.MODE_PRIVATE);




            sharedPreferences.edit().putString(KEY_LATITUDE, String.valueOf(latitude)).apply();
            sharedPreferences.edit().putString(KEY_LONGITUDE,String.valueOf(longitude)).apply();

        startService(new Intent(SalesStaffHome.this, AttendenceService.class));




    }

}