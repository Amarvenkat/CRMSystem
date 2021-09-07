package com.example.crmsystem.admin;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.crmsystem.APIClient;
import com.example.crmsystem.ApiProduct;
import com.example.crmsystem.ElectroicProduct;
import com.example.crmsystem.OrganicResults;
import com.example.crmsystem.R;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InventoryForTheProduct extends AppCompatActivity {

    RecyclerView inventoryrecycler,inventoryrecycler2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory_for_the_product);
        inventoryrecycler = (RecyclerView) findViewById(R.id.inventoryrecycler);
        inventoryrecycler2 = (RecyclerView) findViewById(R.id.inventoryrecycler2);


        loadImagesDynamically();
        ApiProduct apiServicenews = APIClient.getClient().create(ApiProduct.class);
        Call<ElectroicProduct> callnews =apiServicenews.getResourse();
        callnews.enqueue(new Callback<ElectroicProduct>() {
            @Override
            public void onResponse(Response<ElectroicProduct> response) {
                List<OrganicResults> products = response.body().getOrganicResults();

                Toast.makeText(getApplicationContext(),
                        "Size "+products.size(), Toast.LENGTH_SHORT).show();


                InventoryAdapter inventoryAdapter = new InventoryAdapter(getApplicationContext(),products,R.layout.productcard);
                    inventoryrecycler.setAdapter(inventoryAdapter);
                inventoryrecycler.setLayoutManager(new GridLayoutManager(InventoryForTheProduct.this,2,LinearLayoutManager.VERTICAL,false));



            }


            @Override
            public void onFailure(Throwable t) {


                //Toast.makeText(this,"eoorr"+t,Toast.LENGTH_LONG).show();
                Log.e("TAG", t.toString());

            }
        });


    }

    private void loadImagesDynamically() {
        final ArrayList<ProductsPojo> images = new ArrayList<>();
        CollectionReference db = FirebaseFirestore.getInstance().collection("product");
        db.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots,
                                @Nullable FirebaseFirestoreException e) {
                if(queryDocumentSnapshots != null) {
                    images.clear();
                    for(DocumentSnapshot snapshot : queryDocumentSnapshots.getDocuments()) {
                        ProductsPojo imageData = snapshot.toObject(ProductsPojo.class);
                        images.add(imageData);
                    }

                    inventoryrecycler2.setLayoutManager(new GridLayoutManager(InventoryForTheProduct.this,2,LinearLayoutManager.VERTICAL,false));

                    inventoryrecycler2.setAdapter(new InventoryAdapter2(getApplicationContext(),images,R.layout.productcard));
                }

            }
        });


    }
}