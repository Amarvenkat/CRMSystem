package com.example.crmsystem.admin;

import android.app.Service;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class ProductUploadService extends Service {


    String stringproductname;
    String stringstockavailability;
    String stringprice;
    String stringproductdescription;
    String minetype;
    String imageuri;

    private static final String SELLPRODUCTNAME ="sellproductname" ;
    private static final String SELLSTOCKAVAILABILITY ="sellstockavailability" ;
    private static final String SELLPRICE = "sellprice";
    private static final String SELLPRODUCTDESCRIPTION = "sellproductdescription";
    public  static final String  IMGURL = "imgurl";
    private static final String MINTYPE ="mintype" ;

    public ProductUploadService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Bundle bundle =intent.getExtras();


        if (bundle.containsKey(SELLPRODUCTNAME)){
            stringproductname =  bundle.getString(SELLPRODUCTNAME);

        }
        if (bundle.containsKey(SELLSTOCKAVAILABILITY)){
            stringstockavailability = bundle.getString(SELLSTOCKAVAILABILITY);

        }
        if (bundle.containsKey(SELLPRICE)){
            stringprice = bundle.getString(SELLPRICE);

        }
        if(bundle.containsKey(SELLPRODUCTDESCRIPTION)){
            stringproductdescription = bundle.getString(SELLPRODUCTDESCRIPTION);

        }
        if (bundle.containsKey(MINTYPE)){
            minetype = bundle.getString(MINTYPE);
        }
        if (bundle.containsKey(IMGURL)){
            imageuri = bundle.getString(IMGURL);
        }


        uploadFile(stringproductname,stringstockavailability,stringprice,stringproductdescription,minetype);



        return super.onStartCommand(intent, flags, startId);
    }


    private void uploadFile(final String stringproductname, final String stringstockavailability, final String stringprice, final String stringproductdescription, String mimeType) {
        final StorageReference ref = FirebaseStorage.getInstance()
                .getReference(stringproductname + "." + mimeType);
        ref.putFile(Uri.parse(imageuri))
                .addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                        if (task.isSuccessful()) {
                            ref.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                                @Override
                                public void onComplete(@NonNull Task<Uri> downloadTask) {
                                    if (downloadTask.isSuccessful()) {
                                        Uri uri = downloadTask.getResult();
                                        uploadImageDataToFirestore(stringproductname,stringstockavailability,stringprice,stringproductdescription,uri.toString());
                                    } else {
                                        Toast.makeText(getApplicationContext(), "Something went wrong: "
                                                + downloadTask.getException().toString(), Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                        } else {
                            Toast.makeText(getApplicationContext(), "Something went wrong: "
                                    + task.getException().toString(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
    private void uploadImageDataToFirestore( String stringproductname, String stringstockavailability, String stringprice, String stringproductdescription, String url) {
        CollectionReference db = FirebaseFirestore.getInstance().collection("product");
        ProductsPojo imageData = new ProductsPojo(stringproductname,stringstockavailability,stringprice,stringproductdescription,url);
        db.add(imageData).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(),
                            "Successfully uploaded image", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(getApplicationContext(),
                            "Something went wrong: " + task.getException().toString(),
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
